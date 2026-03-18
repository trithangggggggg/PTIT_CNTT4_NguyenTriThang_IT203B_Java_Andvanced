package session08.bai1;

import session08.bai1.device.Device;
import session08.bai1.factory.AirConditionerFactory;
import session08.bai1.factory.DeviceFactory;
import session08.bai1.factory.FanFactory;
import session08.bai1.factory.LightFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static List<Device> devices = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== SMART HOME MENU =====");
            System.out.println("1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị mới");
            System.out.println("3. Bật thiết bị");
            System.out.println("4. Tắt thiết bị");
            System.out.println("5. Thoát");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    connectHardware();
                    break;

                case 2:
                    createDevice();
                    break;

                case 3:
                    turnOnDevice();
                    break;

                case 4:
                    turnOffDevice();
                    break;

                case 5:
                    System.out.println("Thoát chương trình.");
                    return;
            }
        }
    }

    static void connectHardware() {
        HardwareConnection connection = HardwareConnection.getInstance();
        connection.connect();
    }

    static void createDevice() {

        System.out.println("Chọn loại thiết bị:");
        System.out.println("1. Đèn");
        System.out.println("2. Quạt");
        System.out.println("3. Điều hòa");

        int type = scanner.nextInt();

        DeviceFactory factory = null;

        switch (type) {

            case 1:
                factory = new LightFactory();
                break;

            case 2:
                factory = new FanFactory();
                break;

            case 3:
                factory = new AirConditionerFactory();
                break;
        }

        if (factory != null) {
            Device device = factory.createDevice();
            devices.add(device);
        }
    }

    static void turnOnDevice() {

        if (devices.isEmpty()) {
            System.out.println("Chưa có thiết bị.");
            return;
        }

        System.out.println("Chọn thiết bị:");

        for (int i = 0; i < devices.size(); i++) {
            System.out.println((i + 1) + ". Device " + (i + 1));
        }

        int index = scanner.nextInt() - 1;

        devices.get(index).turnOn();
    }

    static void turnOffDevice() {

        if (devices.isEmpty()) {
            System.out.println("Chưa có thiết bị.");
            return;
        }

        System.out.println("Chọn thiết bị:");

        for (int i = 0; i < devices.size(); i++) {
            System.out.println((i + 1) + ". Device " + (i + 1));
        }

        int index = scanner.nextInt() - 1;

        devices.get(index).turnOff();
    }
}
