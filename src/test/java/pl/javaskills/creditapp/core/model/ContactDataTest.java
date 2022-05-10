package pl.javaskills.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testowanie klasy ContactDataTest")
class ContactDataTest {

    @Test
    @DisplayName("Adres domowy = adres korespondencyjny to zwróci Optional empty")
    public void test1(){
        //given
        Address homeAddress = new Address("Kisiel", "Sosnowiec", "3", "41-219", "Slaskie");
        Address corespondenceAddress = new Address("Kisiel", "Sosnowiec", "3", "41-219", "Slaskie");
        ContactData contactData = ContactData.Builder
                .create()
                .withEmail("krstr@wp.pl")
                .withPhoneNumber("123456789")
                .withHomeAddress(homeAddress)
                .withCorespondenceAddress(corespondenceAddress)
                .build();
        //then
        assertTrue(contactData.getCorespondenceAddress().isEmpty());
    }

    @Test
    @DisplayName("Adres domowy != adres korespondencyjny to zwróci Optional present")
    public void test2(){
        //given
        Address homeAddress = new Address("Kisiel", "Sosnowiec", "5", "41-219", "Slaskie");
        Address corespondenceAddress = new Address("Biało", "Sosnowiec", "3", "41-219", "Slaskie");
        ContactData contactData = ContactData.Builder
                .create()
                .withEmail("krstr@wp.pl")
                .withPhoneNumber("123456789")
                .withHomeAddress(homeAddress)
                .withCorespondenceAddress(corespondenceAddress)
                .build();
        //then
        assertTrue(contactData.getCorespondenceAddress().isPresent());
    }

}