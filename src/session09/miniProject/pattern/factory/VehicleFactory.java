package session09.miniProject.pattern.factory;

/*
 Factory tạo ngẫu nhiên phương tiện
 Nhận TrafficLight để truyền vào constructor của từng loại xe
 */

import session09.miniProject.entity.PriorityVehicle;
import session09.miniProject.entity.StandardVehicle;
import session09.miniProject.entity.TrafficLight;
import session09.miniProject.entity.Vehicle;

import java.util.Random;

public class VehicleFactory {

    private static final Random random = new Random();

    public static Vehicle createVehicle(int id, TrafficLight trafficLight) {

        int type = random.nextInt(3);

        switch (type) {

            case 0:
                return new StandardVehicle("CAR-" + id, 60, trafficLight);

            case 1:
                return new StandardVehicle("BIKE-" + id, 40, trafficLight);

            case 2:
                return new PriorityVehicle("AMB-" + id, 80, trafficLight);

            default:
                return new StandardVehicle("CAR-" + id, 50, trafficLight);

        }

    }

}
