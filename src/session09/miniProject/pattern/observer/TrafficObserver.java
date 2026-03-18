package session09.miniProject.pattern.observer;

/*
  Observer interface - các xe đăng ký lắng nghe trạng thái đèn
 */
public interface TrafficObserver {

    void onLightChanged(String newState);

}
