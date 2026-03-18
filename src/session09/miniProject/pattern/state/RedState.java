package session09.miniProject.pattern.state;

public class RedState implements TrafficLightState {

    @Override
    public void handle() {

        System.out.println("🔴 Đèn đỏ - xe phải dừng");

    }

    @Override
    public String getName() {
        return "RED";
    }
}