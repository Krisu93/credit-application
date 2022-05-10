package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.validation.reflection.ObjectValidator;
import pl.javaskills.creditapp.di.Inject;

public class CreditApplicationValidator implements Validator{
    @Inject
    private ObjectValidator objectValidator;

    public CreditApplicationValidator(){

    }
    public CreditApplicationValidator(ObjectValidator objectValidator) {
        this.objectValidator = objectValidator;
    }

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        try {
            objectValidator.validate(creditApplication);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
