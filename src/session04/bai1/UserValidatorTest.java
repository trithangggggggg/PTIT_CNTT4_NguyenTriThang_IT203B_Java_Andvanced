package session04.bai1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidatorTest {

    @Test
    void TC01() {
        UserValidator validator = new UserValidator();
        boolean result = validator.isValidUsername("user123");
        assertTrue(result);
    }

    @Test
    void TC02() {
        UserValidator validator = new UserValidator();
        String username = "abc";
        boolean result = validator.isValidUsername(username);
        assertFalse(result);
    }

    @Test
    void TC03() {
        UserValidator validator = new UserValidator();
        String username = "user name";
        boolean result = validator.isValidUsername(username);
        assertFalse(result);
    }
}