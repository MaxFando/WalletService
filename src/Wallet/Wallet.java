package Wallet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Wallet implements Serializable {
    private double balance;
    private final Map<String, Double> expenses = new HashMap<>();
    private final Map<String, Double> budgets = new HashMap<>();

    public void addIncome(double amount) {
        balance += amount;
    }

    public void addExpense(double amount, String category) {
        if (amount > balance) {
            throw new IllegalArgumentException("Недостаточно средств.");
        }

        balance -= amount;
        expenses.put(category, expenses.getOrDefault(category, 0.0) + amount);

        if (budgets.containsKey(category) && expenses.get(category) > budgets.get(category)) {
            System.out.println("Внимание: Превышен бюджет для категории " + category);
        }
    }

    public void setBudget(String category, double amount) {
        budgets.put(category, amount);
    }

    public void printSummary() {
        System.out.println("Текущий баланс: " + balance);
        System.out.println("Расходы по категориям: " + expenses);
        System.out.println("Бюджеты: " + budgets);
    }
}