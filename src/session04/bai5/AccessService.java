package session04.bai5;

public class AccessService {

    public boolean canPerformAction(User user, Action action) {

        if (user == null || action == null) {
            return false;
        }

        Role role = user.getRole();

        switch (role) {

            case ADMIN:
                return true;

            case MODERATOR:
                if (action == Action.DELETE_USER) {
                    return false;
                }
                return true;

            case USER:
                return action == Action.VIEW_PROFILE;

            default:
                return false;
        }
    }
}