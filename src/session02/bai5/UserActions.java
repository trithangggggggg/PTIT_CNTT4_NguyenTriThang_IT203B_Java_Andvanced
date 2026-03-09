package session02.bai5;

public interface UserActions {
    default void logActivity(String activity) {
        System.out.println("User log: " + activity);
    }
}
