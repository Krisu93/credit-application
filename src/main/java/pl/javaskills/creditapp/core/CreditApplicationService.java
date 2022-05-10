package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pl.javaskills.creditapp.di.Inject;
import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.scoring.PersonScoringCalculatorFactory;
import pl.javaskills.creditapp.core.validation.*;
import pl.javaskills.creditapp.core.validation.post.CompoundPostValidator;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;

public class CreditApplicationService {
    private static final Logger log = LoggerFactory.getLogger(CreditApplicationService.class);

    @Inject
    private PersonScoringCalculatorFactory personScoringCalculatorFactory;
    @Inject
    private ApplicationRatingService calculatorRating;
    @Inject
    private CreditApplicationValidator validator;
    @Inject
    private CompoundPostValidator postValidator;

    public CreditApplicationService(){
    }

    public CreditApplicationService(PersonScoringCalculatorFactory personScoringCalculatorFactory, ApplicationRatingService calculatorRating, CreditApplicationValidator validator, CompoundPostValidator postValidator){
        this.personScoringCalculatorFactory = personScoringCalculatorFactory;
        this.calculatorRating = calculatorRating;
        this.validator = validator;
        this.postValidator = postValidator;
    }

    public CreditApplicationDecision getDecision(CreditApplication creditApplication){
        Instant instantStart = Instant.now();
        String id = creditApplication.getId();
        MDC.put("id", id);

        try {
            //step 1
            validator.validate(creditApplication);
            //step 2
            Person person = creditApplication.getPerson();
            int scoring = personScoringCalculatorFactory.getCalculator(person).calculate(creditApplication);
            //step 3
            double creditRating = calculatorRating.calculate(creditApplication);
            //step 4
            try {
                postValidator.validate(creditApplication, scoring, creditRating);
            } catch (RequirementNotMetException e) {
                return new CreditApplicationDecision(DecisionType.NEGATIVE_REQUIREMENTS_NOT_MET, person.getPersonalData(), creditRating, scoring, e.getExceptionCause());
            }
            return getCreditApplicationDecision(creditApplication, person, scoring, creditRating);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            throw new IllegalStateException();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new IllegalStateException();
        } finally {
            Instant instantEnd = Instant.now();
            long time = Duration.between(instantStart, instantEnd).toMillis();
            long time2 = Duration.between(creditApplication.getCreationDateClientZone(), ZonedDateTime.now(creditApplication.getClientTimeZone())).toMillis();
            log.info("Application processing is finished. Took {}/{} ms", time,time2);
        }
    }

    private CreditApplicationDecision getCreditApplicationDecision(CreditApplication creditApplication, Person person, int scoring, double creditRating) {

        if (scoring < 300)
            return new CreditApplicationDecision(DecisionType.NEGATIVE_SCORING, person.getPersonalData(), scoring);
        else if (scoring <= 400)
            return new CreditApplicationDecision(DecisionType.CONTACT_REQUIRED, person.getPersonalData(), scoring);
        else {
            if (creditRating >= creditApplication.getPurposeOfLoan().getAmount())
                return new CreditApplicationDecision(DecisionType.POSITIVE, person.getPersonalData(), scoring);
            else
                return new CreditApplicationDecision(DecisionType.NEGATIVE_CREDIT_RATING, person.getPersonalData(), creditRating, scoring);
        }
    }
}
