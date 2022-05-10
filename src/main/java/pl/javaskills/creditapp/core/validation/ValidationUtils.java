package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.*;

import java.util.Objects;

public class ValidationUtils {
    public static void validateNotNull(String field, Object object) throws ValidationException {
        if (Objects.isNull(object))
            throw new NotNullException(field);
    }

    public static void validateRegex(String field, String value, String regex) throws ValidationException {
        if (!value.matches(regex)) {
            throw new RegexException(field);
        }
    }

    public static void validateMinValue(String field, int value, int expMinValue) throws ValidationException {
      if(value < expMinValue)
          throw new MinValueException(field, expMinValue);
    }

    public static void validateMinValue(String field, double value, double expMinValue) throws ValidationException {
        if(value < expMinValue)
            throw new MinValueException(field, expMinValue);
    }

    public static void validateMaxValue(String field, int value, int expMaxValue) throws ValidationException {
        if(value > expMaxValue)
            throw new MaxValueException(field, expMaxValue);
    }

    public static void validateMaxValue(String field, double value, double expMaxValue) throws ValidationException {
        if(value > expMaxValue)
            throw new MaxValueException(field, expMaxValue);
    }
}
