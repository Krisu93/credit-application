package pl.javaskills.creditapp.core.validation.post;

import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.model.CreditApplication;

public interface PostValidator {
    void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException;
}
