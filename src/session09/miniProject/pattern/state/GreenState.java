package session09.miniProject.pattern.state;

/*
 trạng thái đèn xanh
 */

public class GreenState implements TrafficLightState {

    @Override
    public void handle() {

        System.out.println("🟢 Đèn xanh - xe được phép đi");

    }

    @Override
    public String getName() {
        return "GREEN";
    }
}