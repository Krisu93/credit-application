package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.PersonalData;

import static pl.javaskills.creditapp.core.utils.Constants.*;

public class PersonalDataValidator implements Validator{
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        PersonalData personalData = creditApplication.getPerson().getPersonalData();

        ValidationUtils.validateNotNull("Name", personalData.getName());
        ValidationUtils.validateNotNull("Lastname", personalData.getLastName());
        ValidationUtils.validateNotNull("MothersMaidenName", personalData.getMothersMaidenName());
        ValidationUtils.validateNotNull("Education", personalData.getEducation());
        ValidationUtils.validateNotNull("MaritalStatus", personalData.getMaritalStatus());

        ValidationUtils.validateRegex("Name", personalData.getName(), NAME_REGEX);
        ValidationUtils.validateRegex("Lastname", personalData.getLastName(), LASTNAME_REGEX);
        ValidationUtils.validateRegex("MothersMaidenName", personalData.getMothersMaidenName(), MOTHERS_MAIDEN_REGEX);

    }
}
