package pl.javaskills.creditapp.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.javaskills.creditapp.core.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Walidacja danych typu String")
class StringValidatorTest {

    @ParameterizedTest
    @DisplayName("prawidłowe imię")
    @ValueSource(strings = {"Kris", "Kristof"})
    public void test1(String name){
        assertTrue(StringValidator.validateString(name, Constants.NAME_REGEX));
    }

    @ParameterizedTest
    @DisplayName("prawidłowe nazwisko")
    @ValueSource(strings = {"Straczek", "Kurdej-Szatan"})
    public void test2(String lastname){
        assertTrue(StringValidator.validateString(lastname, Constants.LASTNAME_REGEX));
    }

    @ParameterizedTest
    @DisplayName("prawidłowe nazwisko matki")
    @ValueSource(strings = {"Straczek", "Kurdej-Szatan"})
    public void test3(String maiden){
        assertTrue(StringValidator.validateString(maiden, Constants.MOTHERS_MAIDEN_REGEX));
    }

    @ParameterizedTest
    @DisplayName("prawidłowy email")
    @ValueSource(strings = {"kr-str93@wp.pl", "k@interia_onet.pl", "lol_inho@o2"})
    public void test4(String email){
        assertTrue(StringValidator.validateString(email, Constants.EMAIL_REGEX));
    }
}