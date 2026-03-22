package session11.bai5;

public class DoctorService {
    private final DoctorDAO doctorDAO = new DoctorDAO();

    public void showDoctors() {
        doctorDAO.displayAllDoctors();
    }

    public void addDoctor(Doctor doctor) {
        if (doctor.getFullName() == null || doctor.getFullName().trim().isEmpty()) {
            System.out.println("Họ tên không được để trống.");
            return;
        }

        if (doctor.getSpecialty() == null || doctor.getSpecialty().trim().isEmpty()) {
            System.out.println("Chuyên khoa không được để trống.");
            return;
        }

        boolean result = doctorDAO.insertDoctor(doctor);
        if (result) {
            System.out.println("Thêm bác sĩ thành công.");
        } else {
            System.out.println("Thêm bác sĩ thất bại.");
        }
    }

    public void statisticBySpecialty() {
        doctorDAO.countDoctorsBySpecialty();
    }
}