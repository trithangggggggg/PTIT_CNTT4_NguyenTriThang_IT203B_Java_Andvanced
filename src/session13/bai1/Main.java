package session13.bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    /*
     * Phần 1:
     * Code cũ bị lỗi vì JDBC mặc định autoCommit = true.
     * Sau khi trừ thuốc bằng executeUpdate(), dữ liệu có thể đã được commit ngay.
     * Nếu xảy ra lỗi trước khi ghi lịch sử (ví dụ 10/0), chương trình dừng lại,
     * dẫn đến kho bị trừ nhưng không có bản ghi lịch sử.
     * Điều này vi phạm tính nguyên tử (Atomicity) của transaction.
     */

    public void capPhatThuoc(int medicineId, int patientId) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            conn = DatabaseManager.getConnection();

            // tắt auto-commit để gom 2 thao tác thành 1 transaction
            conn.setAutoCommit(false);

            String sql1 = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
            ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, medicineId);
            ps1.executeUpdate();

            String sql2 = "INSERT INTO Prescription_History(patient_id, medicine_id, date) VALUES (?, ?, GETDATE())";
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, patientId);
            ps2.setInt(2, medicineId);
            ps2.executeUpdate();

            // chỉ commit khi cả 2 đều thành công
            conn.commit();

        } catch (Exception e) {
            // nếu có lỗi thì rollback toàn bộ
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (ps2 != null) ps2.close();
                if (ps1 != null) ps1.close();
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