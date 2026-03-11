package session04.bai6;

import java.time.LocalDate;
import java.util.List;

public class ProfileService {

    public User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {

        if (existingUser == null || newProfile == null) {
            return null;
        }

        if (newProfile.getBirthDate().isAfter(LocalDate.now())) {
            return null;
        }

        if (allUsers != null) {
            for (User u : allUsers) {
                if (u != existingUser &&
                        u.getEmail().equalsIgnoreCase(newProfile.getEmail())) {
                    return null;
                }
            }
        }

        existingUser.setEmail(newProfile.getEmail());
        existingUser.setBirthDate(newProfile.getBirthDate());

        return existingUser;
    }
}