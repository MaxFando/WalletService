package Service;

import Entity.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private final Map<String, User> users;

    public AuthService() {
        this.users = new HashMap<>();
    }

    public User register(String username, String password) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User(username, password);
        users.put(username, user);
        return user;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user == null || !user.passwordIsEqual(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return user;
    }
}
