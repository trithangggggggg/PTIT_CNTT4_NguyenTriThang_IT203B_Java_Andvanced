package session09.miniProject.pattern.observer;

/*
  Subject interface - đèn giao thông quản lý danh sách observer (xe)
 */
public interface TrafficSubject {

    void registerObserver(TrafficObserver observer);

    void removeObserver(TrafficObserver observer);

    void notifyObservers(String newState);

}
