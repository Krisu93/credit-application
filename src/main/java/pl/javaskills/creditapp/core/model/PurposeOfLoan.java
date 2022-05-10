package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;

import java.io.Serializable;

public class PurposeOfLoan implements Serializable {
    public static final long serialVersionUID = 1;
    @NotNull()
    private final Type type;
    private final double amount;
    private final byte period;

    public PurposeOfLoan(Type type, double amount, byte period) {
        this.type = type;
        this.amount = amount;
        this.period = period;
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public byte getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "PurposeOfLoan{" +
                "type=" + type +
                ", amount=" + amount +
                ", period=" + period +
                '}';
    }
}
