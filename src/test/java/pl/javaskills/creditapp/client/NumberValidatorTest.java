package pl.javaskills.creditapp.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Walidacja danych typu Number")
class NumberValidatorTest {

    private static int[] allowedValue = {5,10,15,20,25,30};

    @ParameterizedTest
    @DisplayName("prawidłowe wartości zmiennoprzecinkowe")
    @ValueSource(strings = {"1000", "200000", "3500.00", "250000.00"})
    public void test1(String doubles){
        assertTrue(NumberValidator.validateDouble(doubles, 0, Double.MAX_VALUE));
    }

    @ParameterizedTest
    @DisplayName("prawidłowe wartości liczb całkowitych [wszystkich]")
    @ValueSource(strings = {"1000", "200000", "1", "12"})
    public void test2(String ints){
        assertTrue(NumberValidator.validateInteger(ints, 1, Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @DisplayName("prawidłowe wartości liczb całkowitych [wszystkich]")
    @ValueSource(strings = {"5", "10", "20", "25"})
    public void test3(String ints){
        assertTrue(NumberValidator.validateInteger(ints, allowedValue));
    }
}