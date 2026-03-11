package session04.bai2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {
    @Test
    void TC01() {
        UserService userService = new UserService();
        int age = 18;
        boolean result = userService.checkRegistrationAge(age);
        assertEquals(true, result);
    }

    @Test
    void TC02() {
        UserService userService = new UserService();
        int age = 17;
        boolean result = userService.checkRegistrationAge(age);
        assertEquals(false, result);
    }

    @Test
    void TC03() {
        UserService userService = new UserService();
        int age = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            userService.checkRegistrationAge(age);
        });
    }
}
