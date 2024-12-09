package Entity;

import java.io.Serializable;

public class Category implements Serializable {
    private String name;
    private double budget;

    public Category(String name, double budget) {
        this.name = name;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }
}
