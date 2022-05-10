package pl.javaskills.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static pl.javaskills.creditapp.utils.AgeUtils.generateBirthDate;

class AbstarctPersonTest {

    @Test
    @DisplayName("Sprawdź sortowanie FamilyMember po polu 'age'")
    public void test(){
        //given
        FamilyMember user02 = new FamilyMember("user02", generateBirthDate(28));
        FamilyMember user03 = new FamilyMember("user03", generateBirthDate(28));
        FamilyMember user04 = new FamilyMember("User04", generateBirthDate(29));
        FamilyMember user05 = new FamilyMember("User05", generateBirthDate(49));
        FamilyMember user06 = new FamilyMember("User06", generateBirthDate(53));

        //when
        Person person = NaturalPerson.Builder
                .create()
                .withFamilyMembers(Arrays.asList(user02, user03,user04,user05,user06))
                .build();
        //then
        assertNotNull(person.getFamilyMembers());
        assertTrue(person.getFamilyMembers().size() == 5);
        assertEquals(user06, person.getFamilyMembers().get(4));
        assertEquals(user05, person.getFamilyMembers().get(3));
        assertEquals(user04, person.getFamilyMembers().get(2));
        assertEquals(user03, person.getFamilyMembers().get(1));
        assertEquals(user02, person.getFamilyMembers().get(0));
    }
}