package session09.miniProject.pattern.state;

public class YellowState implements TrafficLightState {

    @Override
    public void handle() {

        System.out.println("🟡 Đèn vàng - chuẩn bị dừng");

    }

    @Override
    public String getName() {
        return "YELLOW";
    }
}