package session12.bai5;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/rhms_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            while (true) {
                System.out.println("\n========= HỆ THỐNG QUẢN LÝ NỘI TRÚ RHMS =========");
                System.out.println("1. Danh sách bệnh nhân");
                System.out.println("2. Tiếp nhận bệnh nhân mới");
                System.out.println("3. Cập nhật bệnh án");
                System.out.println("4. Xuất viện & Tính phí");
                System.out.println("5. Thoát");
                System.out.print("Chọn chức năng: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        listPatients(conn);
                        break;
                    case 2:
                        addPatient(conn, sc);
                        break;
                    case 3:
                        updateMedicalRecord(conn, sc);
                        break;
                    case 4:
                        dischargePatient(conn, sc);
                        break;
                    case 5:
                        System.out.println("Thoát chương trình.");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
        }
    }

    // 1. Danh sách bệnh nhân
    public static void listPatients(Connection conn) throws SQLException {
        String sql = "SELECT id, name, age, department FROM Patients";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- DANH SÁCH BỆNH NHÂN -----");
            while (rs.next()) {
                System.out.println("Mã BN: " + rs.getInt("id"));
                System.out.println("Tên: " + rs.getString("name"));
                System.out.println("Tuổi: " + rs.getInt("age"));
                System.out.println("Khoa: " + rs.getString("department"));
                System.out.println("--------------------------------");
            }
        }
    }

    // 2. Tiếp nhận bệnh nhân mới
    public static void addPatient(Connection conn, Scanner sc) throws SQLException {
        String sql = "INSERT INTO Patients(name, age, department, disease, admission_days, status) VALUES (?, ?, ?, ?, ?, ?)";

        System.out.print("Nhập tên bệnh nhân: ");
        String name = sc.nextLine();

        System.out.print("Nhập tuổi: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập khoa điều trị: ");
        String department = sc.nextLine();

        System.out.print("Nhập bệnh lý: ");
        String disease = sc.nextLine();

        System.out.print("Nhập số ngày nhập viện: ");
        int admissionDays = Integer.parseInt(sc.nextLine());

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, department);
            ps.setString(4, disease);
            ps.setInt(5, admissionDays);
            ps.setString(6, "IN_TREATMENT");

            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("Tiếp nhận bệnh nhân mới thành công.");
            }
        }
    }

    // 3. Cập nhật bệnh án
    public static void updateMedicalRecord(Connection conn, Scanner sc) throws SQLException {
        String sql = "UPDATE Patients SET disease = ? WHERE id = ?";

        System.out.print("Nhập mã bệnh nhân cần cập nhật: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập bệnh lý mới: ");
        String disease = sc.nextLine();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, disease);
            ps.setInt(2, id);

            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("Cập nhật bệnh án thành công.");
            } else {
                System.out.println("Không tìm thấy bệnh nhân.");
            }
        }
    }

    // 4. Xuất viện và tính phí
    public static void dischargePatient(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Nhập mã bệnh nhân xuất viện: ");
        int patientId = Integer.parseInt(sc.nextLine());

        double totalFee = calculateDischargeFee(conn, patientId);

        String updateSql = "UPDATE Patients SET status = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
            ps.setString(1, "DISCHARGED");
            ps.setInt(2, patientId);

            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("Xuất viện thành công.");
                System.out.println("Tổng viện phí: " + totalFee);
            } else {
                System.out.println("Không tìm thấy bệnh nhân.");
            }
        }
    }

    /*
    Stored Procedure CALCULATE_DISCHARGE_FEE có:
    - Tham số vào: patient_id
    - Tham số ra: total_fee

    JDBC bắt buộc phải registerOutParameter() trước execute()
    để biết kiểu dữ liệu OUT.
    */
    public static double calculateDischargeFee(Connection conn, int patientId) throws SQLException {
        String sql = "{call CALCULATE_DISCHARGE_FEE(?, ?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, patientId);
            cs.registerOutParameter(2, Types.DOUBLE);
            cs.execute();

            return cs.getDouble(2);
        }
    }
}