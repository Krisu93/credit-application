package pl.javaskills.creditapp.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Walidacja numeru telefonu")
class PhoneValidatorTest {

    @ParameterizedTest
    @DisplayName("prawidłowy numer telefonu")
    @ValueSource(strings = {"603107578", "+48603107578"})
    public void test(String number){
            assertTrue(PhoneValidator.validate(number));
        }

}