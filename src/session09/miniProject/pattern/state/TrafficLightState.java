package session09.miniProject.pattern.state;

/*
 Interface cho State Pattern
 mỗi trạng thái đèn sẽ implement interface này
 */

public interface TrafficLightState {

    void handle();

    String getName();

}