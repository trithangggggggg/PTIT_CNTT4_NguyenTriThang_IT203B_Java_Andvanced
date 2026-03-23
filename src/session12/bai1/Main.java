package session12.bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    /*
    PreparedStatement được coi là “tấm khiên” chống SQL Injection vì
    nó tách biệt hoàn toàn câu lệnh SQL và dữ liệu đầu vào.
     Cơ chế "Pre-compiled" (biên dịch trước) giups ngăn chặn việc chèn mã độc vào câu lệnh SQL,
      vì Không nối chuỗi SQL từ dữ liệu người dùng
        Cố định cú pháp truy vấn trước
        Ràng buộc input thành tham số thuần
        Ngăn dữ liệu bị diễn giải thành lệnh SQL
    * */
    public boolean loginDoctor(Connection conn, String doctorCode, String password) throws SQLException {
        String sql = "SELECT 1 FROM Doctors WHERE code = ? AND pass = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doctorCode);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
