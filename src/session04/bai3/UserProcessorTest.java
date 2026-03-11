package session04.bai3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserProcessorTest {
    private UserProcessor userProcessor;

    @BeforeEach
    void setUp() {
        userProcessor = new UserProcessor();
    }

    @Test
    void shouldReturnEmailWhenFormatIsValid() {

        String email = "user@gmail.com";
        String result = userProcessor.processEmail(email);
        assertEquals("user@gmail.com", result);
    }


    @Test
    void shouldThrowExceptionWhenEmailMissingAtSymbol() {

        String email = "usergmail.com";

        assertThrows(IllegalArgumentException.class, () -> {
            userProcessor.processEmail(email);
        });
    }

    @Test
    void shouldThrowExceptionWhenEmailMissingDomain() {

        String email = "user@";

        assertThrows(IllegalArgumentException.class, () -> {
            userProcessor.processEmail(email);
        });
    }

    @Test
    void shouldNormalizeEmailToLowercase() {

        String email = "Example@Gmail.com";

        String result = userProcessor.processEmail(email);

        assertEquals("example@gmail.com", result);
    }
}
