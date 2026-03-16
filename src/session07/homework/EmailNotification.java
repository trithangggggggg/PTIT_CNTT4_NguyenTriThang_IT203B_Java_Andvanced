package session07.homework;

public class EmailNotification implements NotificationService {

    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi email tới " + recipient + ": " + message);
    }
}