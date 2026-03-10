package session03.bai2;

import java.util.List;

record  User(String name, String email, String status) {
}

public class Main {
    static void main(String[] args) {
        List<User> users = List.of(
                new User("trithang ","trithang@gmail.com", "active"),
                new User("undefined", "trithang2006vn@haha.com", "inactive"),
                new User("trithang2222 ","trithang222@gmail.com", "active")
        );
        users.stream()
                .filter(user -> user.email().endsWith("gmail.com"))
                .map(user -> user.name())
                .forEach(System.out::println);
    }
}
