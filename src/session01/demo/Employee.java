package session01.demo;

import java.util.Date;
import java.util.Scanner;

public class Employee {
    private String empId;
    private String fullName;
    private boolean gender;
    private Date birthday;
    private String address;
    private Float yearInWorks;
    private double salary;

    public Employee() {
    }

    public Employee(String empId, String fullName, boolean gender, Date birthday, String address, Float yearInWorks, double salary) {
        this.empId = empId;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.yearInWorks = yearInWorks;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getYearInWorks() {
        return yearInWorks;
    }

    public void setYearInWorks(Float yearInWorks) {
        this.yearInWorks = yearInWorks;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã nhân viên: ");
        empId = sc.nextLine();
        System.out.println("Nhập họ tên nhân viên: ");
        fullName = sc.nextLine();
        System.out.println("Nhập giới tính nhân viên (true = nam, false = nu): ");
    }

    public void display() {

    }
}
