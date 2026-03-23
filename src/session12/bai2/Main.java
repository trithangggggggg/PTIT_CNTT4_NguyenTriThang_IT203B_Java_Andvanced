package session12.bai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    /*
    PreparedStatement giúp xử lý đúng kiểu dữ liệu (type handling) như double, int
    mà không phụ thuộc vào định dạng vùng miền (locale).

    Khi dùng setDouble(), setInt():
    - Giá trị được truyền dưới dạng dữ liệu thuần, không phải chuỗi
    - Không cần quan tâm dấu chấm (.) hay dấu phẩy (,)
    - Tránh lỗi cú pháp SQL do sai định dạng số
    - Không nối chuỗi SQL nên an toàn và chính xác hơn

    => Đảm bảo dữ liệu luôn đúng định dạng mà DB yêu cầu
    */

    public void updateVitals(Connection conn, double temperature, int heartRate, int patientId) throws SQLException {
        String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, temperature);
            ps.setInt(2, heartRate);
            ps.setInt(3, patientId);

            ps.executeUpdate();
        }
    }
}