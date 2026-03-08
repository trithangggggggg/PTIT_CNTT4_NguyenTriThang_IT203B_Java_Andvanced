package session01.bai2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tổng số người dùng: ");
        int tongNguoi = sc.nextInt();

        System.out.print("Nhập số nhóm muốn chia: ");
        int soNhom = sc.nextInt();

        try {
            int ketQua = tongNguoi / soNhom;

            System.out.println("Mỗi nhóm có: " + ketQua + " người");

        } catch (ArithmeticException e) {

            System.out.println("Không thể chia cho 0!");
        }

        System.out.println("Chương trình vẫn tiếp tục chạy...");
    }
}
