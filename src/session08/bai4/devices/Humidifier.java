package session08.bai4.devices;

import session08.bai4.observer.Observer;

public class Humidifier implements Observer {

    public void update(int temperature) {

        System.out.println("Máy tạo ẩm: Điều chỉnh độ ẩm cho nhiệt độ " + temperature);
    }
}