package session08.bai3;

import session08.bai3.command.ACSetTemperatureCommand;
import session08.bai3.command.LightOffCommand;
import session08.bai3.command.LightOnCommand;
import session08.bai3.device.AirConditioner;
import session08.bai3.device.Fan;
import session08.bai3.device.Light;
import session08.bai3.remote.RemoteControl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        RemoteControl remote = new RemoteControl();

        while (true) {

            System.out.println("\n===== REMOTE MENU =====");
            System.out.println("1. Gán nút 1: Bật đèn");
            System.out.println("2. Gán nút 2: Tắt đèn");
            System.out.println("3. Gán nút 3: Set điều hòa 26");
            System.out.println("4. Nhấn nút");
            System.out.println("5. Undo");
            System.out.println("6. Thoát");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    remote.setCommand(1, new LightOnCommand(light));
                    System.out.println("Đã gán LightOnCommand cho nút 1");
                    break;

                case 2:
                    remote.setCommand(2, new LightOffCommand(light));
                    System.out.println("Đã gán LightOffCommand cho nút 2");
                    break;

                case 3:
                    remote.setCommand(3, new ACSetTemperatureCommand(ac, 26));
                    System.out.println("Đã gán ACSetTemperatureCommand(26) cho nút 3");
                    break;

                case 4:
                    System.out.println("Nhấn nút số:");
                    int slot = scanner.nextInt();
                    remote.pressButton(slot);
                    break;

                case 5:
                    remote.undo();
                    break;

                case 6:
                    return;
            }
        }
    }
}