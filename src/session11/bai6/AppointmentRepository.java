package session11.bai6;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentRepository {

    public boolean addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (patient_name, appointment_date, doctor_name, status) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, appointment.getPatientName());
            ps.setDate(2, appointment.getAppointmentDate());
            ps.setString(3, appointment.getDoctorName());
            ps.setString(4, appointment.getStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm lịch khám: " + e.getMessage());
            return false;
        }
    }

    public boolean updateAppointment(Appointment appointment) {
        String sql = "UPDATE appointments SET patient_name = ?, appointment_date = ?, doctor_name = ?, status = ? WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, appointment.getPatientName());
            ps.setDate(2, appointment.getAppointmentDate());
            ps.setString(3, appointment.getDoctorName());
            ps.setString(4, appointment.getStatus());
            ps.setInt(5, appointment.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật lịch khám: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa lịch khám: " + e.getMessage());
            return false;
        }
    }

    public Appointment getAppointmentById(int id) {
        String sql = "SELECT * FROM appointments WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm lịch khám theo ID: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                );
                list.add(appointment);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách lịch khám: " + e.getMessage());
        }

        return list;
    }
}