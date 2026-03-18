package session09.miniProject.entity;

/*
 Xe ưu tiên (ambulance) - priority cao
 - Nếu đèn ĐỎ hoặc VÀNG: vượt đèn (in thông báo vượt đèn đỏ)
 - Nếu đèn XANH: đi qua bình thường (không cần vượt)
 */

public class PriorityVehicle extends Vehicle {

    private final TrafficLight trafficLight;

    public PriorityVehicle(String id, int speed, TrafficLight trafficLight) {
        super(id, speed, 10); // priority cao nhất
        this.trafficLight = trafficLight;
    }

    @Override
    public void run() {
        System.out.println("🚑 Ambulance " + id + " đang tiến tới giao lộ");
        crossIntersection();
    }

    @Override
    public void crossIntersection() {
        if (trafficLight.isGreen()) {
            // đèn xanh → xe ưu tiên đi qua bình thường
            System.out.println("🚑 Ambulance " + id + " đèn xanh - đi qua bình thường");
        } else {
            // đèn đỏ hoặc vàng → xe ưu tiên mới cần vượt đèn
            System.out.println("🚑 Ambulance " + id + " vượt đèn đỏ/vàng và đi qua! (khẩn cấp)");
        }
    }
}