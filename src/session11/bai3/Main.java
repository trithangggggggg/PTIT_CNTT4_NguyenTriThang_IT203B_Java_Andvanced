package session11.bai3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    /*
     * ===== PHẦN 1: PHÂN TÍCH =====
     *
     * Việc sử dụng if(rs.next()) là không phù hợp với yêu cầu "in danh sách".
     *
     * Vì:
     * - ResultSet hoạt động theo cơ chế con trỏ (cursor)
     * - Ban đầu con trỏ nằm trước dòng đầu tiên
     * - Mỗi lần gọi next():
     *      + di chuyển xuống 1 dòng
     *      + trả về true nếu còn dữ liệu
     *
     * Nếu dùng if(rs.next()):
     * - chỉ xử lý duy nhất dòng đầu tiên
     * - bỏ qua các dòng còn lại
     *
     * => Không in được toàn bộ danh sách thuốc
     *
     * Cách đúng: dùng while(rs.next()) để duyệt toàn bộ dữ liệu
     */

    public static void main(String[] args) {

        // ===== PHẦN 2: THỰC THI =====

        String url = "jdbc:mysql://localhost:3306/Hospital_DB";
        String user = "root";
        String password = "123456";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối database
            conn = DriverManager.getConnection(url, user, password);

            // Tạo câu lệnh SQL
            String sql = "SELECT medicine_name, stock FROM Pharmacy";
            stmt = conn.prepareStatement(sql);

            // Thực thi truy vấn
            rs = stmt.executeQuery();

            System.out.println("===== DANH MỤC THUỐC =====");

            boolean isEmpty = true;

            // Duyệt toàn bộ ResultSet
            while (rs.next()) {
                isEmpty = false;

                String name = rs.getString("medicine_name");
                int stock = rs.getInt("stock");

                System.out.println("Thuốc: " + name + " | Tồn kho: " + stock);
            }

            // Kiểm tra bảng rỗng
            if (isEmpty) {
                System.out.println("Danh mục thuốc trống!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi kết nối hoặc truy vấn!");
            e.printStackTrace();

        } finally {
            // Đóng tài nguyên
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