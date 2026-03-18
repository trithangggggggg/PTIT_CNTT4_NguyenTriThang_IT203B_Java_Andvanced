package session08.bai1.device;

public class AirConditioner implements Device {

    @Override
    public void turnOn() {
        System.out.println("Điều hòa: Bắt đầu làm lạnh.");
    }

    @Override
    public void turnOff() {
        System.out.println("Điều hòa: Tắt.");
    }
}