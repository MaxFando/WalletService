package Entity;

import java.io.Serializable;


public class Transaction implements Serializable {
    public enum TransactionType {
        INCOME,
        EXPENSE,
    }

    private TransactionType type;
    private String category;
    private double amount;

    public Transaction(TransactionType type, String category, double amount) {
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}
