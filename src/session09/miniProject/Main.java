package session09.miniProject;

import session09.miniProject.engine.SimulationEngine;
import session09.miniProject.entity.TrafficLight;

public class Main {

    public static void main(String[] args) {

        // tạo 1 đèn giao thông dùng chung cho toàn mô phỏng
        TrafficLight trafficLight = new TrafficLight();

        // khởi chạy mô phỏng, đèn sẽ được start bên trong engine
        SimulationEngine engine = new SimulationEngine(trafficLight);

        engine.startSimulation();

    }
}