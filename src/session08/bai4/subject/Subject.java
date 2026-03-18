package session08.bai4.subject;

import session08.bai4.observer.Observer;

public interface Subject {

    void attach(Observer o);

    void detach(Observer o);

    void notifyObservers();
}