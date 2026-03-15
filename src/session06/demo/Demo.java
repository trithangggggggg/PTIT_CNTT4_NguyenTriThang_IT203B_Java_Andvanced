package session06.demo;

import java.util.Random;

public class Demo {
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

        Random random = new Random();

        int n = 0;

        do {
            int index = random.nextInt(studentName.length);
            System.out.println("Bạn được chọn: " + studentName[index]);
            n++;
        }while (n < 10);

    }
}
