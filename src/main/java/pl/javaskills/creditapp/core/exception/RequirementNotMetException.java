package pl.javaskills.creditapp.core.exception;

public class RequirementNotMetException extends Exception{

    private final Cause exceptionCause;

    public RequirementNotMetException(Cause exceptionCause) {
        this.exceptionCause = exceptionCause;
    }

    public Cause getExceptionCause() {
        return exceptionCause;
    }

    public enum Cause{
        TOO_HIGH_PERSONAL_EXPENSES,
        TOO_LOW_LOAN_AMOUNT
    }
}
