package session02.bai6;

public class Main {
    public static void main(String[] args) {

        User user = new User("thang la nha vua java ");
        UserProcessor processor = UserUtils::convertToUpperCase;

        String result = processor.process(user);

        System.out.println(result);

    }
}