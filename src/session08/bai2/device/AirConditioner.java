package session08.bai2.device;

public class AirConditioner {

    public void turnOff() {
        System.out.println("FACADE: Tắt điều hòa");
    }

    public void setTemperature(int temp) {
        System.out.println("FACADE: Điều hòa set " + temp + "°C");
    }
}