package session04.bai5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccessServiceTest {

    private AccessService accessService;
    private User admin;
    private User moderator;
    private User user;

    @BeforeEach
    void setUp() {
        accessService = new AccessService();

        admin = new User("admin1", Role.ADMIN);
        moderator = new User("mod1", Role.MODERATOR);
        user = new User("user1", Role.USER);
    }

    @AfterEach
    void tearDown() {
        admin = null;
        moderator = null;
        user = null;
    }

    @Test
    void testAdminPermissions() {
        assertAll(
                () -> assertTrue(
                        accessService.canPerformAction(admin, Action.DELETE_USER)
                ),
                () -> assertTrue(
                        accessService.canPerformAction(admin, Action.LOCK_USER)
                ),
                () -> assertTrue(
                        accessService.canPerformAction(admin, Action.VIEW_PROFILE)
                )
        );
    }

    @Test
    void testModeratorPermissions() {
        assertAll(
                () -> assertFalse(
                        accessService.canPerformAction(moderator, Action.DELETE_USER)
                ),
                () -> assertTrue(
                        accessService.canPerformAction(moderator, Action.LOCK_USER)
                ),
                () -> assertTrue(
                        accessService.canPerformAction(moderator, Action.VIEW_PROFILE)
                )
        );
    }

    @Test
    void testUserPermissions() {
        assertAll(
                () -> assertFalse(
                        accessService.canPerformAction(user, Action.DELETE_USER)
                ),
                () -> assertFalse(
                        accessService.canPerformAction(user, Action.LOCK_USER)
                ),
                () -> assertTrue(
                        accessService.canPerformAction(user, Action.VIEW_PROFILE)
                )
        );
    }
}