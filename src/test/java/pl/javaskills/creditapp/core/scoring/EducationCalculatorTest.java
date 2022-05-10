package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.javaskills.creditapp.core.model.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static pl.javaskills.creditapp.utils.AgeUtils.generateBirthDate;

class EducationCalculatorTest {

    private EducationCalculator educationCalculator = new EducationCalculator();

    @ParameterizedTest //testy parametryzowane
    @DisplayName("zwróć punkty względem parametru 'Education'")
    @EnumSource(Education.class)
    public void test(Education education){
        //given
        Person person = NaturalPerson.Builder
                .create()
                .withPersonalData(new PersonalData.Builder()
                        .withEducation(education)
                        .build())
                .withFamilyMembers(Arrays.asList(new FamilyMember("User01", generateBirthDate(30))))
                .build();
        //when
        int points = educationCalculator.calculate(person);
        //then
        assertEquals(education.getScoringPoints(), points);

    }

}