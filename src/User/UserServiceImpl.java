package User;

public class UserServiceImpl implements Finance.UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUserData() {
        userRepository.saveAll();
    }

    @Override
    public void loadUserData() {
        userRepository.loadAll();
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public void registerUser(String username, String password) {
        User user = new User(username, password);
        userRepository.save(user);
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return (user != null && user.getPassword().equals(password)) ? user : null;
    }
}