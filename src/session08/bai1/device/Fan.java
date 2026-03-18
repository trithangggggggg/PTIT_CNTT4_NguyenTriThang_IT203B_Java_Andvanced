package session08.bai1.device;

public class Fan implements Device {

    @Override
    public void turnOn() {
        System.out.println("Quạt: Bắt đầu quay.");
    }

    @Override
    public void turnOff() {
        System.out.println("Quạt: Dừng.");
    }
}