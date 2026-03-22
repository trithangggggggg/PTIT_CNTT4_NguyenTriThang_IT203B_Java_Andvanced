package session11.bai2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    // Việc sử dụng if(rs.next()) là không phù hợp với yêu cầu “in danh sách”, vì:
    //
    //ResultSet hoạt động theo cơ chế cursor (con trỏ).
    //Ban đầu con trỏ nằm trước dòng đầu tiên.
    //Mỗi lần gọi next():
    //con trỏ di chuyển xuống 1 dòng
    //trả về true nếu còn dữ liệu
    //
    // Khi dùng if(rs.next()), chương trình chỉ:
    //
    //kiểm tra và xử lý duy nhất 1 dòng đầu tiên
    //bỏ qua toàn bộ các dòng còn lại
    //
    // Do đó, không thể đáp ứng yêu cầu in toàn bộ danh mục thuốc.
    static void main(String[] args) {

//        PreparedStatement stmt;
//        ResultSet rs = stmt.executeQuery("SELECT medicine_name, stock FROM Pharmacy");
//
//        while (rs.next()) {
//            System.out.println(
//                    "Thuốc: " + rs.getString("medicine_name") +
//                            " | Tồn kho: " + rs.getInt("stock")
//            );
//        }
    }
}
