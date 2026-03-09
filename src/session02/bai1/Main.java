package session02.bai1;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    static void main(String[] args) {
        User u = new User("TriThang", "ADMIN");
        Predicate<User> isAdmin = user ->  user.role.equals("ADMIN");
        System.out.println(isAdmin.test(u));

        Function<User, String> getName = user -> user.name;
        System.out.println(getName.apply(u));

        Supplier<User> userSupplier = () -> new User("guest", "USER");
        User newUser = userSupplier.get();
        System.out.println(newUser.name);
    }
}
