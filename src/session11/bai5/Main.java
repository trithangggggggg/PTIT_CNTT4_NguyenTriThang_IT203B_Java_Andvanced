package session11.bai5;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoctorService doctorService = new DoctorService();

        while (true) {
            System.out.println("\n========== RIKKEI-CARE ==========");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ mới");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát chương trình");
            System.out.print("Chọn chức năng: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số từ 1 đến 4.");
                continue;
            }

            switch (choice) {
                case 1:
                    doctorService.showDoctors();
                    break;

                case 2:
                    try {
                        System.out.print("Nhập mã bác sĩ: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Nhập họ tên bác sĩ: ");
                        String name = scanner.nextLine().trim();

                        System.out.print("Nhập chuyên khoa: ");
                        String specialty = scanner.nextLine().trim();

                        Doctor doctor = new Doctor(id, name, specialty);
                        doctorService.addDoctor(doctor);

                    } catch (NumberFormatException e) {
                        System.out.println("Mã bác sĩ phải là số nguyên.");
                    }
                    break;

                case 3:
                    doctorService.statisticBySpecialty();
                    break;

                case 4:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
