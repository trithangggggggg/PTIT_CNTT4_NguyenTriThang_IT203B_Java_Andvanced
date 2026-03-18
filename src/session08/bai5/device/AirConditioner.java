package session08.bai5.device;

import session08.bai5.observer.Observer;

public class AirConditioner implements Observer {

    private int temperature = 28;

    public void setTemperature(int temp) {

        temperature = temp;

        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    @Override
    public void update(int temperature) {

        if (temperature > 30) {

            System.out.println("Điều hòa: Nhiệt độ = 28 (vẫn giữ)");
        }
    }
}