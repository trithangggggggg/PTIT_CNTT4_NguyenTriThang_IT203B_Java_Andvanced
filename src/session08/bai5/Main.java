package session08.bai5;


import session08.bai5.command.SleepModeCommand;
import session08.bai5.device.AirConditioner;
import session08.bai5.device.Fan;
import session08.bai5.device.Light;
import session08.bai5.sensor.TemperatureSensor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        TemperatureSensor sensor = new TemperatureSensor();

        sensor.attach(fan);
        sensor.attach(ac);

        SleepModeCommand sleepMode = new SleepModeCommand(light, fan, ac);

        while (true) {

            System.out.println("\n===== SMART SLEEP SYSTEM =====");
            System.out.println("1. Kích hoạt chế độ ngủ");
            System.out.println("2. Thay đổi nhiệt độ");
            System.out.println("3. Thoát");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    sleepMode.execute();
                    break;

                case 2:
                    System.out.println("Nhập nhiệt độ:");
                    int temp = scanner.nextInt();
                    sensor.setTemperature(temp);
                    break;

                case 3:
                    return;
            }
        }
    }
}