package session08.bai4;

import session08.bai4.devices.Fan;
import session08.bai4.devices.Humidifier;
import session08.bai4.sensor.TemperatureSensor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TemperatureSensor sensor = new TemperatureSensor();

        Fan fan = new Fan();
        Humidifier humidifier = new Humidifier();

        while (true) {

            System.out.println("\n===== SMART SENSOR MENU =====");
            System.out.println("1. Đăng ký Quạt");
            System.out.println("2. Đăng ký Máy tạo ẩm");
            System.out.println("3. Thay đổi nhiệt độ");
            System.out.println("4. Thoát");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    sensor.attach(fan);
                    System.out.println("Quạt: Đã đăng ký nhận thông báo");
                    break;

                case 2:
                    sensor.attach(humidifier);
                    System.out.println("Máy tạo ẩm: Đã đăng ký");
                    break;

                case 3:
                    System.out.println("Nhập nhiệt độ:");
                    int temp = scanner.nextInt();
                    sensor.setTemperature(temp);
                    break;

                case 4:
                    return;
            }
        }
    }
}