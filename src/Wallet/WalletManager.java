package Wallet;

import java.util.Scanner;

public class WalletManager {
    private final Scanner scanner;
    private final User.User user;

    public WalletManager(Scanner scanner, User.User user) {
        this.scanner = scanner;
        this.user = user;
    }

    public void manageWallet() {
        while (true) {
            System.out.println("\n1. Добавить доход\n2. Добавить расход\n3. Установить бюджет\n4. Просмотреть кошелёк\n5. Выйти");
            WalletChoice choice = WalletChoice.fromValue(scanner.nextLine());
            switch (choice) {
                case ADD_INCOME:
                    addIncome();
                    break;
                case ADD_EXPENSE:
                    addExpense();
                    break;
                case SET_BUDGET:
                    setBudget();
                    break;
                case VIEW_WALLET:
                    viewWallet();
                    break;
                case LOGOUT:
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void addIncome() {
        System.out.print("Введите сумму дохода: ");
        double amount = Double.parseDouble(scanner.nextLine());
        user.getWallet().addIncome(amount);
        System.out.println("Доход успешно добавлен.");
    }

    private void addExpense() {
        System.out.print("Введите сумму расхода: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Введите категорию: ");
        String category = scanner.nextLine();

        try {
            user.getWallet().addExpense(amount, category);
            System.out.println("Расход успешно добавлен.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setBudget() {
        System.out.print("Введите категорию: ");
        String category = scanner.nextLine();
        System.out.print("Введите сумму бюджета: ");
        double amount = Double.parseDouble(scanner.nextLine());
        user.getWallet().setBudget(category, amount);
        System.out.println("Бюджет успешно установлен.");
    }

    private void viewWallet() {
        user.getWallet().printSummary();
    }
}