package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.model.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static pl.javaskills.creditapp.utils.AgeUtils.generateBirthDate;

class IncomeCalculatorTest {

    private IncomeCalculator incomeCalculator = new IncomeCalculator();

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
        int points = incomeCalculator.calculate(person);
        //than
        assertEquals(200, points);
    }

}