package User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public void saveAll() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных пользователей: " + e.getMessage());
        }
    }

    @Override
    public void loadAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            Map<String, User> loadedUsers = (Map<String, User>) ois.readObject();
            users.putAll(loadedUsers);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Данные пользователей не найдены. Начало с чистого листа.");
        }
    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }
}