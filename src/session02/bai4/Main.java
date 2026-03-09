package session02.bai4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        users.add(new User("thang"));
        users.add(new User("minh"));
        users.add(new User("tien"));

        // 1
        Function<User, String> getUsername = User::getUsername;
        users.stream().map(getUsername).forEach(System.out::println);

        // 2
        Consumer<String> printer = System.out::println;
        printer.accept("Hello Java");

        // 3
        Supplier<User> createUser = User::new;
        User u = createUser.get();
        System.out.println(u.getUsername());
    }
}
