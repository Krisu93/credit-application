package pl.javaskills.creditapp.core.validation.post;

import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.ExpenseType;

import static pl.javaskills.creditapp.core.exception.RequirementNotMetException.Cause.TOO_HIGH_PERSONAL_EXPENSES;

public class ExpensesPostValidator implements PostValidator{

    private static final double per40 = 40;

    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {
        double balance = creditApplication.getPerson().getBalance();
        double personalExpense = creditApplication.getPerson().getFinanceData().getSumOfExpenses(ExpenseType.PERSONAL);

        double percentage = personalExpense * 100 / balance == 0.0 ? 1 : balance;

        if(percentage > per40)
            throw new RequirementNotMetException(TOO_HIGH_PERSONAL_EXPENSES);
    }
}
