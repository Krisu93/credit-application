package pl.javaskills.creditapp.core.exception;

import java.text.MessageFormat;

public class MaxValueException extends ValidationException{
    public MaxValueException(String field, int expMaxValue) {
        super(MessageFormat.format("Field {0} is invalid. Max value={1}", field, expMaxValue));
    }

    public MaxValueException(String field, double expMaxValue) {
        super(MessageFormat.format("Field {0} is invalid. Max value={1}", field, expMaxValue));
    }
}
