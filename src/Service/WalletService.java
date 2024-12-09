package Service;

import Entity.Category;
import Entity.Transaction;
import Entity.User;
import Entity.Wallet;

import java.util.HashMap;
import java.util.Map;

public class WalletService {
    public void addIncome(User user, String category, double amount) {
        user.getWallet().addTransaction(new Transaction(Transaction.TransactionType.INCOME, category, amount));
        user.getWallet().addBalance(amount);
    }

    public void addExpense(User user, String category, double amount) {
        if (user.getWallet().getBalance() < amount) {
            throw new IllegalArgumentException("Not enough balance");
        }

        if (user.getWallet().getCategories().stream().noneMatch(c -> c.getName().equals(category))) {
            throw new IllegalArgumentException("Category not found");
        }

        if (user.getWallet().getCategoryBudget(category) < amount) {
            throw new IllegalArgumentException("Exceeding budget");
        }

        user.getWallet().addTransaction(new Transaction(Transaction.TransactionType.EXPENSE, category, amount));
        user.getWallet().addBalance(-amount);
    }

    public String generateReport(User user) {
        Wallet wallet = user.getWallet();
        double totalIncome = 0;
        double totalExpense = 0;

        Map<String, Double> incomeByCategory = new HashMap<>();
        Map<String, Double> expenseByCategory = new HashMap<>();
        Map<String, Double> budgets = new HashMap<>();

        for (Transaction transaction : wallet.getTransactions()) {
            String category = transaction.getCategory();
            if (transaction.getType() == Transaction.TransactionType.INCOME) {
                totalIncome += transaction.getAmount();
                incomeByCategory.put(category, incomeByCategory.getOrDefault(category, 0.0) + transaction.getAmount());
            } else {
                totalExpense += transaction.getAmount();
                expenseByCategory.put(category, expenseByCategory.getOrDefault(category, 0.0) + transaction.getAmount());
            }
        }

        for (Category category : wallet.getCategories()) {
            budgets.put(category.getName(), category.getBudget());
        }

        StringBuilder report = new StringBuilder();
        report.append("Total income: ").append(totalIncome).append("\n");
        report.append("Total expense: ").append(totalExpense).append("\n");

        report.append("\nIncome by category: \n");
        for (Map.Entry<String, Double> entry : incomeByCategory.entrySet()) {
            report.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        report.append("\nBudget by category: \n");
        for (Map.Entry<String, Double> entry : budgets.entrySet()) {
            String category = entry.getKey();
            double budget = entry.getValue();
            double expense = expenseByCategory.getOrDefault(category, 0.0);
            double remainingBudget = budget - expense;
            report.append(category).append(": ").append(budget)
                    .append(", Remaining Budget: ").append(remainingBudget).append("\n");
        }

        return report.toString();
    }
}
