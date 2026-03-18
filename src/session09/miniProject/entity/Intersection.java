package session09.miniProject.entity;

/*
 Intersection là vùng critical section
 chỉ cho phép 1 số xe đi qua cùng lúc
 */

import java.util.concurrent.locks.ReentrantLock;

public class Intersection {

    private final ReentrantLock lock = new ReentrantLock();

    public void enter(Vehicle vehicle) {

        try {

            // lock giao lộ
            lock.lock();

            System.out.println(vehicle.getId() + " đang đi qua giao lộ");

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            // unlock
            lock.unlock();

        }

    }

}
