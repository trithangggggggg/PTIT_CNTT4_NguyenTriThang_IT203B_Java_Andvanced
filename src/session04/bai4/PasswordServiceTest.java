package session04.bai4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordServiceTest {

    private PasswordService passwordService;

    @BeforeEach
    void setUp() {
        passwordService = new PasswordService();
    }

    @Test
    void testPasswordStrengthCases() {

        assertAll(

                // TC01
                () -> assertEquals("Mạnh",
                        passwordService.evaluatePasswordStrength("Abc123!@")),

                // TC02
                () -> assertEquals("Trung bình",
                        passwordService.evaluatePasswordStrength("abc123!@")),

                // TC03
                () -> assertEquals("Trung bình",
                        passwordService.evaluatePasswordStrength("ABC123!@")),

                // TC04
                () -> assertEquals("Trung bình",
                        passwordService.evaluatePasswordStrength("Abcdef!@")),

                // TC05
                () -> assertEquals("Trung bình",
                        passwordService.evaluatePasswordStrength("Abc12345")),

                // TC06
                () -> assertEquals("Yếu",
                        passwordService.evaluatePasswordStrength("Ab1!")),

                // TC07
                () -> assertEquals("Yếu",
                        passwordService.evaluatePasswordStrength("password")),

                // TC08
                () -> assertEquals("Yếu",
                        passwordService.evaluatePasswordStrength("ABC12345"))

        );
    }
}