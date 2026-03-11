package session04.bai6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileServiceTest {

    private ProfileService profileService;
    private User existingUser;
    private List<User> users;

    @BeforeEach
    void setUp() {
        profileService = new ProfileService();

        existingUser = new User(
                "old@gmail.com",
                LocalDate.of(2000,1,1)
        );

        users = new ArrayList<>();
        users.add(existingUser);
        users.add(new User(
                "other@gmail.com",
                LocalDate.of(1999,5,5)
        ));
    }

    @Test
    void updateProfile_success_whenEmailAndBirthDateValid() {

        UserProfile newProfile =
                new UserProfile("new@gmail.com",
                        LocalDate.of(1998,1,1));

        User result =
                profileService.updateProfile(existingUser,newProfile,users);

        assertNotNull(result);
    }

    @Test
    void updateProfile_fail_whenBirthDateInFuture() {

        UserProfile newProfile =
                new UserProfile("new@gmail.com",
                        LocalDate.now().plusDays(1));

        User result =
                profileService.updateProfile(existingUser,newProfile,users);

        assertNull(result);
    }

    @Test
    void updateProfile_fail_whenEmailDuplicated() {

        UserProfile newProfile =
                new UserProfile("other@gmail.com",
                        LocalDate.of(1998,1,1));

        User result =
                profileService.updateProfile(existingUser,newProfile,users);

        assertNull(result);
    }

    @Test
    void updateProfile_success_whenEmailSameAsCurrent() {

        UserProfile newProfile =
                new UserProfile("old@gmail.com",
                        LocalDate.of(1995,1,1));

        User result =
                profileService.updateProfile(existingUser,newProfile,users);

        assertNotNull(result);
    }

    @Test
    void updateProfile_success_whenUserListEmpty() {

        UserProfile newProfile =
                new UserProfile("unique@gmail.com",
                        LocalDate.of(1995,1,1));

        User result =
                profileService.updateProfile(existingUser,newProfile,new ArrayList<>());

        assertNotNull(result);
    }

    @Test
    void updateProfile_fail_whenEmailDuplicateAndBirthDateFuture() {

        UserProfile newProfile =
                new UserProfile("other@gmail.com",
                        LocalDate.now().plusDays(1));

        User result =
                profileService.updateProfile(existingUser,newProfile,users);

        assertNull(result);
    }
}