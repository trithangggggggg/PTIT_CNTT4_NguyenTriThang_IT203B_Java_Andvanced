package session02.bai3;

@FunctionalInterface
public interface Authenticatable {
    String getPassword();

    default boolean isAuthenticated() {
        String password = getPassword();
        return password != null && !password.isEmpty();
    }

    static String encrypt(String rawPassword) {
        return "ENC_" + rawPassword;
    }
}
