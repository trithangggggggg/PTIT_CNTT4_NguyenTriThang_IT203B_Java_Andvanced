package session11.bai5;

public class Doctor {
    private int doctorId;
    private String fullName;
    private String specialty;

    public Doctor() {
    }

    public Doctor(int doctorId, String fullName, String specialty) {
        this.doctorId = doctorId;
        this.fullName = fullName;
        this.specialty = specialty;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}