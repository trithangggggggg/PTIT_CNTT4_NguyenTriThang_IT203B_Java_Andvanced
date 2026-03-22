package session11.bai5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDAO {

    public void displayAllDoctors() {
        String sql = "SELECT doctor_id, full_name, specialty FROM Doctors";

        try (
                Connection conn = DBContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            boolean isEmpty = true;
            System.out.println("\n===== DANH SÁCH BÁC SĨ =====");
            System.out.printf("%-10s %-25s %-20s%n", "Mã số", "Họ tên", "Chuyên khoa");

            while (rs.next()) {
                isEmpty = false;
                System.out.printf("%-10d %-25s %-20s%n",
                        rs.getInt("doctor_id"),
                        rs.getString("full_name"),
                        rs.getString("specialty"));
            }

            if (isEmpty) {
                System.out.println("Chưa có bác sĩ nào trong danh sách.");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách bác sĩ: " + e.getMessage());
        }
    }

    public boolean insertDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctors (doctor_id, full_name, specialty) VALUES (?, ?, ?)";

        try (
                Connection conn = DBContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, doctor.getDoctorId());
            ps.setString(2, doctor.getFullName());
            ps.setString(3, doctor.getSpecialty());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm bác sĩ: " + e.getMessage());
            return false;
        }
    }

    public void countDoctorsBySpecialty() {
        String sql = "SELECT specialty, COUNT(*) AS total FROM Doctors GROUP BY specialty";

        try (
                Connection conn = DBContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            boolean isEmpty = true;
            System.out.println("\n===== THỐNG KÊ CHUYÊN KHOA =====");
            System.out.printf("%-20s %-10s%n", "Chuyên khoa", "Số lượng");

            while (rs.next()) {
                isEmpty = false;
                System.out.printf("%-20s %-10d%n",
                        rs.getString("specialty"),
                        rs.getInt("total"));
            }

            if (isEmpty) {
                System.out.println("Không có dữ liệu để thống kê.");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi thống kê chuyên khoa: " + e.getMessage());
        }
    }
}