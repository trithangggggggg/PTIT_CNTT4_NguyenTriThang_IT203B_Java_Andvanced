package session09.miniProject.exception;

/*
 xảy ra nếu lock lỗi khiến 2 xe vào cùng lúc
 */

public class CollisionException extends Exception {

    public CollisionException(String message) {
        super(message);
    }

}