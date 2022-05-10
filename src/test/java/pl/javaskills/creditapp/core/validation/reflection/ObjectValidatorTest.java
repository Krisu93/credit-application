package pl.javaskills.creditapp.core.validation.reflection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.LoanApplicationTestFactory;

import java.util.List;

class ObjectValidatorTest {
    private List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
    private List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
    private ObjectValidator validator = new ObjectValidator(classProcessors, fieldProcessors);

    @Test
    @DisplayName("Walidacja wszystkich pól dla modelu CreditApplication")
    public void test() throws ValidationException, NoSuchFieldException, IllegalAccessException {
        //given
        CreditApplication creditApplication = LoanApplicationTestFactory.create();
        //when
        validator.validate(creditApplication);
    }

}