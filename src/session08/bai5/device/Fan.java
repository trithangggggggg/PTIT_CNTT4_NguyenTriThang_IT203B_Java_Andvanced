package session08.bai5.device;

import session08.bai5.observer.Observer;

public class Fan implements Observer {

    public void lowSpeed() {
        System.out.println("Quạt: Chạy tốc độ thấp");
    }

    public void highSpeed() {
        System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
    }

    @Override
    public void update(int temperature) {

        if (temperature > 30) {
            highSpeed();
        }
    }
}