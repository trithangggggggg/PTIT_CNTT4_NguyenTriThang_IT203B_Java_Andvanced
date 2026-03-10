package session03.bai5;

import java.util.*;

record User(String username, String email, String status) {}

public class Main {
    public static void main(String[] args) {

        List<User> users = List.of(
                new User("alexander", "alex@gmail.com", "ACTIVE"),
                new User("bob", "bob@yahoo.com", "INACTIVE"),
                new User("charlotte", "charlotte@gmail.com", "ACTIVE"),
                new User("Benjamin", "ben@gmail.com", "ACTIVE"),
                new User("anna", "anna@gmail.com", "ACTIVE"),
                new User("mike", "mike@yahoo.com", "INACTIVE")
        );

        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
                .limit(3)
                .map(User::username)
                .forEach(System.out::println);
    }
}