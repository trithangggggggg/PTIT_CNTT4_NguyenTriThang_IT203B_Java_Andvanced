package session11.bai1;

public class Main {
    static void main(String[] args) {
//        Việc khởi tạo kết nối liên tục mà không đóng hoặc không quản lý tập trung là rất nguy hiểm vì:
//
//Rò rỉ tài nguyên
//Mỗi lần getConnection() tạo kết nối mới, nếu không close() sẽ làm đầy tài nguyên → hệ thống chậm hoặc treo.
//Gây lỗi Communications link failure
//Kết nối lâu không kiểm tra có thể bị timeout hoặc mất mạng → khi dùng lại sẽ lỗi.
//Ảnh hưởng hệ thống y tế
//Có thể gây gián đoạn tra cứu bệnh án, chậm xử lý và ảnh hưởng đến bệnh nhân.
//Khó bảo trì (hard-code)
//Thông tin DB viết cứng → khó thay đổi, kém bảo mật.
//Không quản lý tập trung
//Dễ trùng code, khó kiểm soát và sửa lỗi toàn hệ thống.
        PatientDAO dao = new PatientDAO();
        dao.getAllPatients();
    }
}
