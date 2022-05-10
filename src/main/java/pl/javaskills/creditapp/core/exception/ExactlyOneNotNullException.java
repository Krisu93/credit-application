package pl.javaskills.creditapp.core.exception;

import java.text.MessageFormat;
import java.util.Set;

public class ExactlyOneNotNullException extends ValidationException{
    public ExactlyOneNotNullException(Set<String> fields, String className) {
        super(MessageFormat.format("Exactly one of filed should be not null {0} in class {1}", fields, className));
    }
}
