package Finance;

import User.UserChoice;
import User.UserRepositoryImpl;
import User.UserServiceImpl;
import Wallet.WalletManager;

import java.util.Scanner;

public class FinanceApp {
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private User.User currentUser;

    public void run() {
        userService.loadUserData();

        while (true) {
            System.out.println("1. Войти\n2. Зарегистрироваться\n3. Выйти");
            UserChoice choice = UserChoice.fromValue(scanner.nextLine());
            switch (choice) {
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                case EXIT:
                    userService.saveUserData();
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void login() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        currentUser = userService.authenticate(username, password);
        if (currentUser != null) {
            System.out.println("Успешный вход! Добро пожаловать, " + username + "!");
            WalletManager walletManager = new WalletManager(scanner, currentUser);
            walletManager.manageWallet();
        } else {
            System.out.println("Неверное имя пользователя или пароль.");
        }
    }

    private void register() {
        System.out.print("Введите новое имя пользователя: ");
        String username = scanner.nextLine();

        if (userService.userExists(username)) {
            System.out.println("Имя пользователя уже существует.");
            return;
        }

        System.out.print("Введите новый пароль: ");
        String password = scanner.nextLine();

        userService.registerUser(username, password);
        System.out.println("Регистрация успешна. Теперь вы можете войти.");
    }
}
