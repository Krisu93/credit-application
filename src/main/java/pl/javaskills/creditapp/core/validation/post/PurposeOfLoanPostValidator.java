package pl.javaskills.creditapp.core.validation.post;


import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.utils.Constants;

import static pl.javaskills.creditapp.core.exception.RequirementNotMetException.Cause.TOO_LOW_LOAN_AMOUNT;


public class PurposeOfLoanPostValidator implements PostValidator{

    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {
        if(creditApplication.getPurposeOfLoan().getAmount() < Constants.MIN_LOAN_AMOUNT_MORTGAGE)
            throw new RequirementNotMetException(TOO_LOW_LOAN_AMOUNT);
    }
}
