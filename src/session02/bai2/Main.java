package session02.bai2;

public class Main {
    static void main(String[] args) {
        PasswordValidator validator = password -> password.length() >= 8;

        System.out.println(validator.isValid("12345678"));
        System.out.println(validator.isValid("1234"));;
    }
}
