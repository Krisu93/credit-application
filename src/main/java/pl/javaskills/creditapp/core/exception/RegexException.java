package pl.javaskills.creditapp.core.exception;

import java.text.MessageFormat;

public class RegexException extends ValidationException{
    public RegexException(String field) {
        super(MessageFormat.format("Bad value for field {0}", field));
    }
}
