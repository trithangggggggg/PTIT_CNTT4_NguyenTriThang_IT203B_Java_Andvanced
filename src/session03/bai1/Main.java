package session03.bai1;

import java.util.List;

record User(String userName, String email, String status) {
}

public class Main {
    static void main(String[] args) {
        List<User> users = List.of(
                new User("tri thang dep trai", "trithangne@gmail.com", "active"),
                new User("unefined", " trithang2006vn@gmail.com", "inactive")
        );
        users.forEach(System.out::println);
    }
}
