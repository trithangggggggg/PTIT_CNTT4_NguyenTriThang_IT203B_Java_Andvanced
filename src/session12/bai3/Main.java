package session12.bai3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Main {

    /*
    JDBC yêu cầu phải gọi registerOutParameter() trước khi execute()
    vì cần biết kiểu dữ liệu của tham số OUT để ánh xạ đúng giữa SQL và Java.

    Nếu không đăng ký:
    - JDBC không biết kiểu dữ liệu trả về
    - Gây lỗi "column index is out of range" hoặc không lấy được giá trị

    Với kiểu DECIMAL trong SQL:
    - Java phải dùng Types.DECIMAL (hoặc Types.NUMERIC)

    => registerOutParameter giúp JDBC chuẩn bị bộ nhớ và kiểu dữ liệu cho giá trị OUT
    */

    public double getSurgeryCost(Connection conn, int surgeryId) throws SQLException {
        String sql = "{call GET_SURGERY_FEE(?, ?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {

            // Tham số IN
            cs.setInt(1, surgeryId);

            // Đăng ký tham số OUT (DECIMAL)
            cs.registerOutParameter(2, Types.DECIMAL);

            // Thực thi
            cs.execute();

            // Lấy kết quả OUT
            return cs.getDouble(2);
        }
    }
}