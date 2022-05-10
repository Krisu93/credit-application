package pl.javaskills.creditapp.core.exception;

import java.text.MessageFormat;

public class MinValueException extends ValidationException{
    public MinValueException(String field, int expMaxValue) {
        super(MessageFormat.format("Field {0} is invalid. Min value={1}", field, expMaxValue));
    }

    public MinValueException(String field, double expMaxValue) {
        super(MessageFormat.format("Field {0} is invalid. Min value={1}", field, expMaxValue));
    }
}
