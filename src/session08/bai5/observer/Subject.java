package session08.bai5.observer;

public interface Subject {

    void attach(Observer o);

    void notifyObservers();
}