package session07.homework;

public class SMSNotification implements NotificationService {

    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi SMS tới " + recipient + ": " + message);
    }
}