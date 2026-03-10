package session03.bai3;

import java.util.List;
import java.util.Optional;

record User(String name, String email, String status) {
}

class UserRepository {
    List<User> users = List.of(
            new User("alice", "alice@gmail.com", "ACTIVE"),
            new User("bob", "bob@yahoo.com", "INACTIVE"),
            new User("charlie", "charlie@gmail.com", "ACTIVE")
    );

    public Optional<User> findUserByUsername(String name) {
        return users.stream()
                .filter(user -> user.name().equals(name))
                .findFirst();
    }
}

public class Main {
    static void main(String[] args) {

        UserRepository repo = new UserRepository();

        Optional<User> result = repo.findUserByUsername("alice");

        result.ifPresent(u -> System.out.println("Welcome " + u.name()));

        String message = result
                .map(u -> "Welcome " + u.name())
                .orElse("Guest login");

        System.out.println(message);
    }
}
