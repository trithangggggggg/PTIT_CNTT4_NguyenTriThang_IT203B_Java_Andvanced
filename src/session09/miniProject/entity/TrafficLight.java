package session09.miniProject.entity;

/*
 TrafficLight sử dụng State Pattern + Observer Pattern
 - State Pattern: quản lý trạng thái đèn (Green/Yellow/Red)
 - Observer Pattern: thông báo đến các xe khi đèn thay đổi
 */

import session09.miniProject.pattern.observer.TrafficObserver;
import session09.miniProject.pattern.observer.TrafficSubject;
import session09.miniProject.pattern.state.GreenState;
import session09.miniProject.pattern.state.RedState;
import session09.miniProject.pattern.state.TrafficLightState;
import session09.miniProject.pattern.state.YellowState;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight implements Runnable, TrafficSubject {

    private TrafficLightState state;

    // danh sách xe đã đăng ký lắng nghe
    private final List<TrafficObserver> observers = new ArrayList<>();

    public TrafficLight() {
        state = new GreenState();
    }

    public TrafficLightState getState() {
        return state;
    }

    // kiểm tra đèn có đang xanh không
    public synchronized boolean isGreen() {
        return state instanceof GreenState;
    }

    // kiểm tra đèn có đang đỏ không
    public synchronized boolean isRed() {
        return state instanceof RedState;
    }

    public synchronized String getStateName() {
        return state.getName();
    }

    public synchronized void changeState(TrafficLightState newState) {
        this.state = newState;
        this.state.handle();           // in ra console
        notifyObservers(newState.getName()); // thông báo cho các xe
    }

    // ====== Observer Pattern ======

    @Override
    public void registerObserver(TrafficObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(TrafficObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String newState) {
        for (TrafficObserver o : observers) {
            o.onLightChanged(newState);
        }
    }

    // ====== Runnable - vòng lặp đèn ======

    @Override
    public void run() {
        try {
            // In trạng thái ban đầu
            synchronized (this) {
                state.handle();
            }

            while (!Thread.currentThread().isInterrupted()) {

                String current = getStateName();

                if ("GREEN".equals(current)) {
                    Thread.sleep(5000);                    // xanh 5 giây
                    changeState(new YellowState());

                } else if ("YELLOW".equals(current)) {
                    Thread.sleep(2000);                    // vàng 2 giây
                    changeState(new RedState());

                } else {
                    Thread.sleep(5000);                    // đỏ 5 giây
                    changeState(new GreenState());
                }

            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}