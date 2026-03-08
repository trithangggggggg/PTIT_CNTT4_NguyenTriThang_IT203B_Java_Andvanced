package session01.bai6;

import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tên người dùng: ");
        String name = sc.nextLine();

        User user = new User(name);

        if (user.getName() != null) {
            System.out.println("Xin chào: " + user.getName());
        }

        System.out.print("Nhập tuổi: ");
        int age = sc.nextInt();

        try {
            user.setAge(age);
            System.out.print("Nhập số nhóm: ");
            int group = sc.nextInt();

            int result = 100 / group;
            System.out.println("Mỗi nhóm có: " + result);

            FileService.saveToFile(user);
        }
        catch (InvalidAgeException e) {
            Logger.logError(e.getMessage());
        }
        catch (ArithmeticException e) {
            Logger.logError("Không thể chia cho 0");
        }
        catch (IOException e) {
            Logger.logError("Lỗi ghi file: " + e.getMessage());
        }
        System.out.println("Chương trình kết thúc an toàn.");
    }
}
