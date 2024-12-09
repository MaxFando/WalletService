package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wallet implements Serializable {
    private double balance;
    private List<Transaction> transactions;
    private List<Category> categories;

    public Wallet() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public double getBalance() {
        return balance;
    }

    // TODO: possible concurrency issue, but we did not cover this in the course
    public void addBalance(double amount) {
        this.balance += amount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public double getCategoryBudget(String category) {
        return categories.stream()
                .filter(c -> c.getName().equals(category))
                .findFirst()
                .map(Category::getBudget)
                .orElse(0.0);
    }
}
