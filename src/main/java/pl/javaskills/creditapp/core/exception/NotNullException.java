package pl.javaskills.creditapp.core.exception;

import java.text.MessageFormat;

public class NotNullException extends ValidationException{
    public NotNullException(String field) {
        super(MessageFormat.format("{0} cannot be null", field));
    }
}
