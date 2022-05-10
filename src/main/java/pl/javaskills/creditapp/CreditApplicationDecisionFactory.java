package pl.javaskills.creditapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.CreditApplicationDecision;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.PersonalData;
import pl.javaskills.creditapp.core.utils.Constants;

import java.math.BigDecimal;
import java.util.ResourceBundle;

public class CreditApplicationDecisionFactory {

    private static final Logger log = LoggerFactory.getLogger(CreditApplicationDecisionFactory.class);

    public String getDecision(CreditApplicationDecision decision, CreditApplication creditApplication){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("translations", creditApplication.getClientLocale());
        PersonalData personalData = creditApplication.getPerson().getPersonalData();
         log.info("Decision = {}", decision.getType());
        switch (decision.getType()) {
            case POSITIVE:
                return String.format(resourceBundle.getString("decision.positive"), personalData.getName(), personalData.getLastName());
            case NEGATIVE_SCORING:
                return String.format(resourceBundle.getString("decision.negative_scoring"), personalData.getName(), personalData.getLastName());
            case NEGATIVE_CREDIT_RATING:
                return String.format(resourceBundle.getString("decision.negative_credit_rating"), personalData.getName(), personalData.getLastName(), new BigDecimal(decision.getRate()).setScale(2));
            case CONTACT_REQUIRED:
                return String.format(resourceBundle.getString("decision.contact_required"), personalData.getName(), personalData.getLastName());
            case NEGATIVE_REQUIREMENTS_NOT_MET:
                switch (decision.getExceptionCause().get()) {
                    case TOO_HIGH_PERSONAL_EXPENSES:
                        return String.format(resourceBundle.getString("decision.too_high_personal_expenses"), personalData.getName(), personalData.getLastName());
                    case TOO_LOW_LOAN_AMOUNT:
                        return String.format(resourceBundle.getString("decision.too_low_loan_amount"), personalData.getName(), personalData.getLastName(), new BigDecimal(Constants.MIN_LOAN_AMOUNT_MORTGAGE).setScale(2));
                }
        }
        return null;
    }
}
