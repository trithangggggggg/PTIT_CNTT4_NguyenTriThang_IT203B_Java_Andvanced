package session08.bai2;

import session08.bai2.facade.SmartHomeFacade;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SmartHomeFacade facade = new SmartHomeFacade();

        while (true) {

            System.out.println("\n===== SMART HOME MENU =====");
            System.out.println("1. Xem nhiệt độ hiện tại");
            System.out.println("2. Chế độ rời nhà");
            System.out.println("3. Chế độ ngủ");
            System.out.println("4. Thoát");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    facade.getCurrentTemperature();
                    break;

                case 2:
                    facade.leaveHome();
                    break;

                case 3:
                    facade.sleepMode();
                    break;

                case 4:
                    System.out.println("Thoát chương trình.");
                    return;
            }
        }
    }
}