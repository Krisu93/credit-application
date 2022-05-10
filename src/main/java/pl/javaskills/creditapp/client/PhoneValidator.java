package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.utils.Constants;

public class PhoneValidator {

    public static boolean validate(String input) {
        return input.matches(Constants.PHONE_REGEX);
    }
}