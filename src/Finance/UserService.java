package Finance;

public interface UserService {
    void saveUserData();

    void loadUserData();

    boolean userExists(String username);

    void registerUser(String username, String password);

    User.User authenticate(String username, String password);
}
