package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.SourceOfIncome;

import java.util.List;

public class FinanceDataValidator implements Validator{
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        List<SourceOfIncome> sourceOfIncomes = creditApplication.getPerson().getFinanceData().getSourceOfIncome();

            ValidationUtils.validateNotNull("SourceOfIncome",sourceOfIncomes);
            if(sourceOfIncomes.size() > 0){
                for (SourceOfIncome source : sourceOfIncomes){
                    ValidationUtils.validateNotNull("IncomeType",source.getIncomeType());
                    ValidationUtils.validateMinValue("NetMonthlyIncome",source.getNetMonthlyIncome(), 0.0);
                }
            } else {
                throw new ValidationException("You have to provide at least one source of income");
            }
    }
}
