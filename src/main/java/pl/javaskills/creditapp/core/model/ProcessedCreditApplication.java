package pl.javaskills.creditapp.core.model;


import java.io.Serializable;

public class ProcessedCreditApplication implements Serializable {
    public static final long serialVersionUID = 1;
    private CreditApplication creditApplication;
    private String decision;

    public ProcessedCreditApplication(CreditApplication creditApplication, String decision) {
        this.creditApplication = creditApplication;
        this.decision = decision;
    }

    public CreditApplication getCreditApplication() {
        return creditApplication;
    }

    public String getDecision() {
        return decision;
    }

    public void setCreditApplication(CreditApplication creditApplication) {
        this.creditApplication = creditApplication;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    @Override
    public String toString() {
        return "ProcessedCreditApplication{" +
                "creditApplication=" + creditApplication +
                ", decision='" + decision + '\'' +
                '}';
    }
}
