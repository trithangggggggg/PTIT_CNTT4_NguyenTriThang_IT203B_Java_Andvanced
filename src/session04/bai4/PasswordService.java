package session04.bai4;

public class PasswordService {

    public String evaluatePasswordStrength(String password) {

        if (password == null || password.length() < 8) {
            return "Yếu";
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");

        // Mạnh
        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            return "Mạnh";
        }

        // Yếu
        if ((hasLower && !hasUpper && !hasDigit && !hasSpecial) ||
                (hasUpper && !hasLower && !hasDigit && !hasSpecial) ||
                (hasUpper && hasDigit && !hasLower && !hasSpecial)) {
            return "Yếu";
        }

        // Còn lại
        return "Trung bình";
    }
}