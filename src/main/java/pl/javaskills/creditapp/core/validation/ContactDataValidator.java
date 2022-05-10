package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.ContactData;
import pl.javaskills.creditapp.core.model.CreditApplication;

import static pl.javaskills.creditapp.core.utils.Constants.EMAIL_REGEX;
import static pl.javaskills.creditapp.core.utils.Constants.PHONE_REGEX;

public class ContactDataValidator implements Validator{
    private final HomeAddressValidator homeAddressValidator;

    public ContactDataValidator(HomeAddressValidator homeAddressValidator) {
        this.homeAddressValidator = homeAddressValidator;
    }

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        ContactData contactData = creditApplication.getPerson().getContactData();

        ValidationUtils.validateNotNull("Email",contactData.getEmail());
        ValidationUtils.validateNotNull("PhoneNumber",contactData.getPhoneNumber());

        ValidationUtils.validateRegex("Email",contactData.getEmail(), EMAIL_REGEX);
        ValidationUtils.validateRegex("PhoneNumber",contactData.getPhoneNumber(), PHONE_REGEX);

        ValidationUtils.validateNotNull("HomeAddress",contactData.getHomeAddress());
        homeAddressValidator.validate(creditApplication);
    }
}
