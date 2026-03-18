package session09.miniProject.engine;

/*
 Engine quản lý toàn bộ mô phỏng
 - Tạo 1 TrafficLight duy nhất và chia sẻ với tất cả các xe
 - Chờ xe hoàn thành, rồi dừng đèn
 */

import session09.miniProject.entity.TrafficLight;
import session09.miniProject.entity.Vehicle;
import session09.miniProject.pattern.factory.VehicleFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {

    private static final int VEHICLE_COUNT = 20;

    // thread pool cho các phương tiện
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    // đèn giao thông dùng chung cho toàn bộ mô phỏng
    private final TrafficLight trafficLight;

    // thread chạy đèn
    private final Thread lightThread;

    public SimulationEngine(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
        this.lightThread = new Thread(trafficLight);
        this.lightThread.setDaemon(true);
    }

    public void startSimulation() {

        // khởi động đèn giao thông
        lightThread.start();
        System.out.println("=== Bắt đầu mô phỏng giao thông ===\n");

        // tạo và submit các xe vào thread pool
        for (int i = 1; i <= VEHICLE_COUNT; i++) {
            Vehicle v = VehicleFactory.createVehicle(i, trafficLight);
            executor.submit(v);
        }

        // dừng nhận thêm task mới và chờ tất cả xe hoàn thành
        executor.shutdown();

        try {
            boolean finished = executor.awaitTermination(60, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("\n=== Mô phỏng hoàn tất - tất cả xe đã qua ngã tư ===");
            } else {
                System.out.println("\n=== Timeout - một số xe chưa hoàn thành ===");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // dừng đèn
        lightThread.interrupt();

    }

}