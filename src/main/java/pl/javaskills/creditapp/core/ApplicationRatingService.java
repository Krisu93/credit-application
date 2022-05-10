package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.utils.Constants;

import java.math.BigDecimal;

public class ApplicationRatingService {

    private static final Logger log = LoggerFactory.getLogger(ApplicationRatingService.class);

    public double calculate(CreditApplication creditApplication) {
        Person person = creditApplication.getPerson();
        double creditRating = person.getIncomePerPeople() * 12 * creditApplication.getPurposeOfLoan().getPeriod();

        switch (creditApplication.getPurposeOfLoan().getType()) {
            case MORTGAGE:
                creditRating *= Constants.MORTGAGE_LOAN_RATE;
                break;
            case PERSONAL_LOAN:
                creditRating *= Constants.PERSONAL_LOAN_RATE;
                break;
        }
        log.info("Calculated rating = {}.", new BigDecimal(creditRating).setScale(2));
        return creditRating;
    }
}
