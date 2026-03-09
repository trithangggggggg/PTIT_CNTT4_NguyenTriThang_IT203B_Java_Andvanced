package session02.bai3;

public class Main {
    static void main(String[] args) {
        User user1 = new User("123456");
        User user2 = new User("");

        System.out.println(user1.isAuthenticated());
        System.out.println(user2.isAuthenticated());

        String encrypted = Authenticatable.encrypt("123456");
        System.out.println(encrypted);
    }
}
