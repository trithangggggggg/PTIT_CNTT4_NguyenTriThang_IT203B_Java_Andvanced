package session09.miniProject.exception;

/*
 xảy ra khi hàng đợi quá dài
 */

public class TrafficJamException extends Exception {

    public TrafficJamException(String message) {
        super(message);
    }

}
