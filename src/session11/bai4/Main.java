package session11.bai4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    /*
     * PHẦN 1: PHÂN TÍCH
     *
     * Nếu nối chuỗi trực tiếp:
     * SELECT * FROM Patients WHERE full_name = '' OR '1'='1'
     *
     * Vì '1'='1' luôn đúng nên mệnh đề WHERE luôn đúng.
     * Kết quả là truy vấn trả về toàn bộ dữ liệu bệnh nhân.
     *
     * Đây là lỗi SQL Injection.
     * Cách khắc phục đúng là dùng PreparedStatement thay vì nối chuỗi.
     */

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Hospital_DB";
        String user = "root";
        String password = "123456";

        String patientName = "Nguyen Van A";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT patient_id, full_name, diagnosis FROM Patients WHERE full_name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientName);

            rs = stmt.executeQuery();

            boolean isEmpty = true;
            System.out.println("===== KẾT QUẢ TRA CỨU BỆNH NHÂN =====");

            while (rs.next()) {
                isEmpty = false;

                int id = rs.getInt("patient_id");
                String name = rs.getString("full_name");
                String diagnosis = rs.getString("diagnosis");

                System.out.println("Mã BN: " + id + " | Họ tên: " + name + " | Chẩn đoán: " + diagnosis);
            }

            if (isEmpty) {
                System.out.println("Không tìm thấy bệnh nhân.");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi kết nối hoặc truy vấn dữ liệu!");
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
