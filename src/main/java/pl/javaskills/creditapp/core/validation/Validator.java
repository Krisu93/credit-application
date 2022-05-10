package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;

public interface Validator {
    void validate(CreditApplication creditApplication) throws ValidationException;
}
