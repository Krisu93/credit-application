package pl.javaskills.creditapp.core.validation.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.di.Inject;

public class CompoundPostValidator implements PostValidator{

    private static final Logger log = LoggerFactory.getLogger(CompoundPostValidator.class);
    @Inject
    private PostValidator[] postValidators;

    public CompoundPostValidator(){}

    public CompoundPostValidator(PostValidator... postValidators) {
        this.postValidators = postValidators;
    }

    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {

        for (PostValidator postValidator : postValidators){
            postValidator.validate(creditApplication, scoring, rating);
        }
    }
}
