package session09.miniProject.entity;

/*
 Xe thường như xe máy, ô tô
 priority = 1
 - Đèn xanh: đi qua
 - Đèn vàng: giảm tốc, chờ
 - Đèn đỏ: dừng lại và chờ đến khi đèn xanh
 */

import session09.miniProject.pattern.observer.TrafficObserver;

public class StandardVehicle extends Vehicle implements TrafficObserver {

    private final TrafficLight trafficLight;

    // lock để chờ đèn xanh
    private final Object waitLock = new Object();

    public StandardVehicle(String id, int speed, TrafficLight trafficLight) {
        super(id, speed, 1);
        this.trafficLight = trafficLight;
        trafficLight.registerObserver(this);
    }

    @Override
    public void onLightChanged(String newState) {
        // khi đèn chuyển sang xanh → đánh thức xe đang chờ
        if ("GREEN".equals(newState)) {
            synchronized (waitLock) {
                waitLock.notifyAll();
            }
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Vehicle " + id + " đang tiến tới ngã tư");

            Thread.sleep(500); // mô phỏng di chuyển tới giao lộ

            // chờ đèn xanh nếu đèn không phải xanh
            waitForGreen();

            crossIntersection();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // hủy đăng ký sau khi xe đã đi qua
            trafficLight.removeObserver(this);
        }
    }

    private void waitForGreen() throws InterruptedException {
        synchronized (waitLock) {
            while (!trafficLight.isGreen()) {
                System.out.println("Vehicle " + id + " dừng chờ đèn đỏ/vàng...");
                waitLock.wait(); // chờ đến khi có notify từ onLightChanged
            }
        }
    }

    @Override
    public void crossIntersection() {
        System.out.println("✅ Vehicle " + id + " đèn xanh - đi qua ngã tư");
    }
}