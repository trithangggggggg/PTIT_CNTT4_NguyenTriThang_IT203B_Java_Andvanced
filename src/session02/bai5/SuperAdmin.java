package session02.bai5;

public class SuperAdmin implements UserActions, AdminActions {
    @Override
    public void logActivity(String activity) {
        UserActions.super.logActivity(activity);
    }
}
