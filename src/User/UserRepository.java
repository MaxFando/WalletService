package User;

public interface UserRepository {
    void save(User user);
    void saveAll();
    void loadAll();
    User findByUsername(String username);
}

