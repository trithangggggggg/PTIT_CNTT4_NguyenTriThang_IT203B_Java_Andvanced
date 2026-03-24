package session13.bai3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {
        Connection conn = null;
        PreparedStatement psSelect = null;
        PreparedStatement psUpdateBalance = null;
        PreparedStatement psUpdateBed = null;
        PreparedStatement psUpdatePatient = null;
        ResultSet rs = null;

        try {
            conn = DatabaseManager.getConnection();
            conn.setAutoCommit(false);

            String sqlSelect = "SELECT advance_balance, bed_id FROM Patients WHERE patient_id = ?";
            psSelect = conn.prepareStatement(sqlSelect);
            psSelect.setInt(1, maBenhNhan);
            rs = psSelect.executeQuery();

            if (!rs.next()) {
                throw new Exception("Không tìm thấy bệnh nhân");
            }

            double soDuTamUng = rs.getDouble("advance_balance");
            int bedId = rs.getInt("bed_id");

            // Bẫy 1: không cho phép số dư âm, nếu không đủ tiền thì phải dừng và rollback
            if (soDuTamUng < tienVienPhi) {
                throw new Exception("Số dư tạm ứng không đủ để thanh toán viện phí");
            }

            String sqlUpdateBalance =
                    "UPDATE Patients SET advance_balance = advance_balance - ? WHERE patient_id = ?";
            psUpdateBalance = conn.prepareStatement(sqlUpdateBalance);
            psUpdateBalance.setDouble(1, tienVienPhi);
            psUpdateBalance.setInt(2, maBenhNhan);
            int rowBalance = psUpdateBalance.executeUpdate();

            // Bẫy 2: nếu update trả về 0 thì dữ liệu không hợp lệ, phải chủ động ném lỗi
            if (rowBalance == 0) {
                throw new Exception("Không thể cập nhật số dư của bệnh nhân");
            }

            String sqlUpdateBed =
                    "UPDATE Beds SET status = 'TRONG' WHERE bed_id = ?";
            psUpdateBed = conn.prepareStatement(sqlUpdateBed);
            psUpdateBed.setInt(1, bedId);
            int rowBed = psUpdateBed.executeUpdate();

            // Bẫy 2: nếu không có dòng nào bị ảnh hưởng thì không được commit
            if (rowBed == 0) {
                throw new Exception("Không thể giải phóng giường bệnh");
            }

            String sqlUpdatePatient =
                    "UPDATE Patients SET status = 'DA_XUAT_VIEN' WHERE patient_id = ?";
            psUpdatePatient = conn.prepareStatement(sqlUpdatePatient);
            psUpdatePatient.setInt(1, maBenhNhan);
            int rowPatient = psUpdatePatient.executeUpdate();

            // Bẫy 2: nếu update thất bại ngầm thì phải rollback
            if (rowPatient == 0) {
                throw new Exception("Không thể cập nhật trạng thái xuất viện của bệnh nhân");
            }

            conn.commit();
            System.out.println("Xuất viện và thanh toán thành công");

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Thao tác thất bại: " + e.getMessage());

        } finally {
            try {
                if (rs != null) rs.close();
                if (psUpdatePatient != null) psUpdatePatient.close();
                if (psUpdateBed != null) psUpdateBed.close();
                if (psUpdateBalance != null) psUpdateBalance.close();
                if (psSelect != null) psSelect.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}