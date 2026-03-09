package session02.bai3;

public class User implements Authenticatable{
    private String password;

    public User(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
