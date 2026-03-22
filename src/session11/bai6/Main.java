package session11.bai6;

import java.sql.Date;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AppointmentRepository repository = new AppointmentRepository();

        // 1. Thêm lịch khám
        Appointment newAppointment = new Appointment(
                "Nguyen Van A",
                Date.valueOf("2026-03-25"),
                "Dr. Tran Van B",
                "Pending"
        );

        boolean addResult = repository.addAppointment(newAppointment);
        System.out.println(addResult ? "Thêm lịch khám thành công." : "Thêm lịch khám thất bại.");

        // 2. Hiển thị tất cả lịch khám
        System.out.println("\n===== DANH SÁCH LỊCH KHÁM =====");
        printAppointments(repository.getAllAppointments());

        // 3. Lấy lịch khám theo ID
        System.out.println("\n===== TÌM LỊCH KHÁM THEO ID = 1 =====");
        Appointment found = repository.getAppointmentById(1);
        if (found != null) {
            printAppointment(found);
        } else {
            System.out.println("Không tìm thấy lịch khám.");
        }

        // 4. Cập nhật lịch khám
        Appointment updateAppointment = new Appointment(
                1,
                "Nguyen Van A",
                Date.valueOf("2026-03-26"),
                "Dr. Le Thi C",
                "Confirmed"
        );

        boolean updateResult = repository.updateAppointment(updateAppointment);
        System.out.println(updateResult ? "\nCập nhật lịch khám thành công." : "\nCập nhật lịch khám thất bại.");

        // 5. Hiển thị lại danh sách sau cập nhật
        System.out.println("\n===== DANH SÁCH SAU KHI CẬP NHẬT =====");
        printAppointments(repository.getAllAppointments());

        // 6. Xóa lịch khám theo ID
        boolean deleteResult = repository.deleteAppointment(1);
        System.out.println(deleteResult ? "\nXóa lịch khám thành công." : "\nXóa lịch khám thất bại.");

        // 7. Hiển thị lại danh sách sau xóa
        System.out.println("\n===== DANH SÁCH SAU KHI XÓA =====");
        printAppointments(repository.getAllAppointments());
    }

    public static void printAppointment(Appointment appointment) {
        System.out.println("ID: " + appointment.getId());
        System.out.println("Tên bệnh nhân: " + appointment.getPatientName());
        System.out.println("Ngày khám: " + appointment.getAppointmentDate());
        System.out.println("Bác sĩ: " + appointment.getDoctorName());
        System.out.println("Trạng thái: " + appointment.getStatus());
    }

    public static void printAppointments(ArrayList<Appointment> list) {
        if (list.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }

        for (Appointment a : list) {
            System.out.println(
                    "ID: " + a.getId()
                            + " | Patient: " + a.getPatientName()
                            + " | Date: " + a.getAppointmentDate()
                            + " | Doctor: " + a.getDoctorName()
                            + " | Status: " + a.getStatus()
            );
        }
    }
}