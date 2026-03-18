package session08.bai4.sensor;

import session08.bai4.observer.Observer;
import session08.bai4.subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private int temperature;

    public void setTemperature(int temperature) {

        this.temperature = temperature;

        System.out.println("Cảm biến: Nhiệt độ = " + temperature);

        notifyObservers();
    }

    public void attach(Observer o) {

        observers.add(o);
    }

    public void detach(Observer o) {

        observers.remove(o);
    }

    public void notifyObservers() {

        for (Observer o : observers) {
            o.update(temperature);
        }
    }
}
