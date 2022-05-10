package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.javaskills.creditapp.core.model.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static pl.javaskills.creditapp.utils.AgeUtils.generateBirthDate;

class MaritalStatusCalculatorTest {
    private MaritalStatusCalculator maritalStatusCalculator = new MaritalStatusCalculator();

    @ParameterizedTest //testy parametryzowane
    @DisplayName("zwróć punkty względem parametru 'MaritalStatus'")
    @EnumSource(MaritalStatus.class)
    public void test(MaritalStatus maritalStatus){
        //given
        Person person = NaturalPerson.Builder
                .create()
                .withPersonalData(new PersonalData.Builder()
                                                    .withMaritalStatus(maritalStatus)
                                                    .build())
                .withFamilyMembers(Arrays.asList(new FamilyMember("User01", generateBirthDate(30))))
                .build();
        //when
        int points = maritalStatusCalculator.calculate(person);
        //then
        assertEquals(maritalStatus.getScoringPoints(), points);

    }
}