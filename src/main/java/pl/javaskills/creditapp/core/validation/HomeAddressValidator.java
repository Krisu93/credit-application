package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.Address;
import pl.javaskills.creditapp.core.model.CreditApplication;

public class HomeAddressValidator implements Validator{
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        Address address = creditApplication.getPerson().getContactData().getHomeAddress();

        ValidationUtils.validateNotNull("City",address.getCity());
        ValidationUtils.validateNotNull("Street",address.getStreet());
        ValidationUtils.validateNotNull("State",address.getState());
        ValidationUtils.validateNotNull("ZipCode",address.getZipCode());
        ValidationUtils.validateNotNull("HouseNumber",address.getHouseNumber());
    }
}
