package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static pl.javaskills.creditapp.utils.AgeUtils.generateBirthDate;

class SourceOfIncomeCalculatorTest {

    private SourceOfIncomeCalculator sourceOfIncomeCalculator = new SourceOfIncomeCalculator();

    @Test
    @DisplayName("zwróć punkty względem jednego źródła dochodu SourceOfIncome i NumOfFamilyDependants")
    public void test(){
        //given
        SourceOfIncome[] source = {new SourceOfIncome(IncomeType.EMPLOYMENT_CONTRACT, 5000.00)};
        Person person = NaturalPerson.Builder
                .create()
                .withFinanceData(new FinanceData(source))
                .withFamilyMembers(Arrays.asList(new FamilyMember("User01", generateBirthDate(30))))
                .build();
        //when
        int points = sourceOfIncomeCalculator.calculate(person);
        //than
        assertEquals(0, points);
    }

    @Test
    @DisplayName("zwróć punkty względem więcej niż jednego źródła dochodu SourceOfIncome i NumOfFamilyDependants")
    public void test2(){
        //given
        SourceOfIncome[] source = {
                new SourceOfIncome(IncomeType.EMPLOYMENT_CONTRACT, 5000.00),
                new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 100)
        };
        Person person = new NaturalPerson.Builder()
                .withFinanceData(new FinanceData(source))
                .withFamilyMembers(Arrays.asList(new FamilyMember("User01", generateBirthDate(30))))
                .build();
        //when
        int points = sourceOfIncomeCalculator.calculate(person);
        //than
        assertEquals(100, points);
    }
}