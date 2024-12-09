import Entity.User;
import Service.AuthService;
import Service.FileService;
import Service.WalletService;

import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "data.ser";

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        WalletService walletService = new WalletService();
        FileService fileService = new FileService();
        User currentUser = null;

        try {
            authService = (AuthService) fileService.loadData(DATA_FILE);
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command: (register, login, addIncome, addExpense, report, exit)");
            String command = scanner.nextLine();

            try {
                switch (command) {
                    case "register":
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        currentUser = authService.register(username, password);
                        break;

                    case "login":
                        System.out.print("Enter username: ");
                        username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();
                        currentUser = authService.login(username, password);
                        break;

                    case "addIncome":
                        if (currentUser == null) throw new IllegalStateException("Login required");
                        System.out.print("Enter category: ");
                        String category = scanner.nextLine();
                        System.out.print("Enter amount: ");
                        double incomeAmount = Double.parseDouble(scanner.nextLine());
                        walletService.addIncome(currentUser, category, incomeAmount);
                        break;

                    case "addExpense":
                        if (currentUser == null) throw new IllegalStateException("Login required");
                        System.out.print("Enter category: ");
                        category = scanner.nextLine();
                        System.out.print("Enter amount: ");
                        double expenseAmount = Double.parseDouble(scanner.nextLine());
                        walletService.addExpense(currentUser, category, expenseAmount);
                        break;

                    case "report":
                        if (currentUser == null) throw new IllegalStateException("Login required");
                        String report = walletService.generateReport(currentUser);
                        System.out.println(report);
                        break;

                    case "exit":
                        fileService.saveData(DATA_FILE, authService);
                        System.out.println("Data saved. Goodbye!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid command");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}