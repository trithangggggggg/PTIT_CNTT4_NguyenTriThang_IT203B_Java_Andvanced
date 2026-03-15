package session06.demo;

public class Demo2 {
    static void main(String[] args) {
            String studentName[] = {
                    "Vũ Việt Tiến",
                    "Đào Quang Huy",
                    "Nguyễn Trí Thắng",
                    "Nguyễn Quốc Hưng",
                    "Phạm Quốc Lộc",
                    "Nguyễn Tuấn Minh",
                    "Trần Quang Duy",
                    "Đào Xuân Khánh"
            };

            String neighborHood[] = {
                    "Phường Phúc Xá",
                    "Phường Trúc Bạch",
                    "Phường Vĩnh Phúc",
                    "Phường Cống Vị",
                    "Phường Liễu Giai",
                    "Phường Nguyễn Trung Trực",
                    "Phường Quán Thánh",
                    "Phường Điện Biên"
            };

            for (int i = 0; i < studentName.length; i++) {
                System.out.println("Bạn được chọn: " + studentName[i]);
            }
    }
}
