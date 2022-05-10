package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class Expense implements Serializable {
    public static final long serialVersionUID = 1;
    @NotNull()
    private final String name;
    @NotNull()
    private final ExpenseType type;
    private final double amount;

    public Expense(String name, ExpenseType type, double amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public ExpenseType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(name, expense.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
