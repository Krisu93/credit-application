package pl.javaskills.creditapp.core.model;

import java.util.*;

import static pl.javaskills.creditapp.core.model.Education.MIDDLE;
import static pl.javaskills.creditapp.core.model.Education.TERTIARY;
import static pl.javaskills.creditapp.core.model.IncomeType.EMPLOYMENT_CONTRACT;
import static pl.javaskills.creditapp.core.model.MaritalStatus.SEPARATED;
import static pl.javaskills.creditapp.core.model.Type.MORTGAGE;
import static pl.javaskills.creditapp.utils.AgeUtils.generateBirthDate;

public class LoanApplicationTestFactory {

    public static CreditApplication create(Type type, double amount, byte period){

        SourceOfIncome[] source = {new SourceOfIncome(EMPLOYMENT_CONTRACT, 5000.00)};
        ContactData contactData = ContactData.Builder
                .create()
                .withEmail("xd@wp.pl")
                .withPhoneNumber("123456789")
                .withHomeAddress(new Address("","","","",""))
                .build();
       NaturalPerson person =  NaturalPerson.Builder
               .create()
               .withContactData(contactData)
               .withFinanceData(new FinanceData(source))
               .withPersonalData(new PersonalData.Builder()
                       .withName("Jan")
                       .withLastName("Niezbedny")
                       .withMothersMaidenName("Kowalska")
                       .withMaritalStatus(SEPARATED)
                       .withEducation(MIDDLE)
                       .build())
               .withPesel("93070297867")
               .withFamilyMembers(getFamilymembersList(1))
               .build();


        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(type, amount, period);

        return new CreditApplication(person, purposeOfLoan);
    }

    public static CreditApplication create(MaritalStatus maritalStatus, Education education, int numOfFamilyDependants, Type type, double amount, byte period, SourceOfIncome... sourceOfIncomes){
        ContactData contactData = ContactData.Builder
                .create()
                .withEmail("xd@wp.pl")
                .withPhoneNumber("123456789")
                .withHomeAddress(new Address("","","","",""))
                .build();
        NaturalPerson person =  NaturalPerson.Builder
                .create()
                .withContactData(contactData)
                .withFinanceData(new FinanceData(sourceOfIncomes))
                .withPersonalData(new PersonalData.Builder()
                        .withName("Jan")
                        .withLastName("Kowalski")
                        .withMothersMaidenName("Kowalska")
                        .withMaritalStatus(maritalStatus)
                        .withEducation(education)
                        .build())
                .withPesel("93070297867")
                .withFamilyMembers(getFamilymembersList(numOfFamilyDependants))
                .build();

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(type, amount, period);

        return new CreditApplication(person,  purposeOfLoan);
    }

    private static List<FamilyMember> getFamilymembersList(int numOfFamilyDependants) {
        List<FamilyMember> familyMembers = new ArrayList<>();
        for (int i = 0; i < numOfFamilyDependants; i++){
            familyMembers.add(new FamilyMember("User01"+i, generateBirthDate(30)));
        }
        return familyMembers;
    }

    public static CreditApplication create(MaritalStatus maritalStatus, Education education, int numOfFamilyDependants, Type type, double amount, byte period, int yearsSinceFounded, SourceOfIncome... sourceOfIncomes){
        ContactData contactData = ContactData.Builder
                .create()
                .withEmail("xd@wp.pl")
                .withPhoneNumber("123456789")
                .withHomeAddress(new Address("","","","",""))
                .build();
        SelfEmployed person =  SelfEmployed.Builder
                .create()
                .withRegon("123446657634")
                .withContactData(contactData)
                .withFinanceData(new FinanceData(sourceOfIncomes))
                .withPersonalData(new PersonalData.Builder()
                        .withName("Jan")
                        .withLastName("Kowalski")
                        .withMothersMaidenName("Kowalska")
                        .withMaritalStatus(maritalStatus)
                        .withEducation(education)
                        .build())
                .withYearsSinceFounded(yearsSinceFounded)
                .withFamilyMembers(getFamilymembersList(numOfFamilyDependants))
                .build();

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(type, amount, period);

        return new CreditApplication( person, purposeOfLoan);
    }

    public static CreditApplication create(MaritalStatus maritalStatus, Education education, int numOfFamilyDependants, Type type, double amount, byte period, int yearsSinceFounded, Expense[] expenses, SourceOfIncome... sourceOfIncomes){
        ContactData contactData = ContactData.Builder
                .create()
                .withEmail("xd@wp.pl")
                .withPhoneNumber("123456789")
                .withHomeAddress(new Address("","","","",""))
                .build();
        SelfEmployed person =  SelfEmployed.Builder
                .create()
                .withNip("142345632424")
                .withContactData(contactData)
                .withFinanceData(new FinanceData(Set.of(expenses),sourceOfIncomes))
                .withPersonalData(new PersonalData.Builder()
                        .withName("Jan")
                        .withLastName("Kowalski")
                        .withMothersMaidenName("Kowalska")
                        .withMaritalStatus(maritalStatus)
                        .withEducation(education)
                        .build())
                .withYearsSinceFounded(yearsSinceFounded)
                .withFamilyMembers(getFamilymembersList(numOfFamilyDependants))
                .build();

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(type, amount, period);

        return new CreditApplication( person, purposeOfLoan);
    }

    public static CreditApplication create(Guarantor... guarantors){
        SourceOfIncome[] source = {new SourceOfIncome(EMPLOYMENT_CONTRACT, 5000.00)};
        ContactData contactData = ContactData.Builder
                .create()
                .withEmail("xd@wp.pl")
                .withPhoneNumber("123456789")
                .withHomeAddress(new Address("","","","",""))
                .build();
        NaturalPerson person =  NaturalPerson.Builder
                .create()
                .withContactData(contactData)
                .withFinanceData(new FinanceData(source))
                .withPersonalData(new PersonalData.Builder()
                        .withName("Jan")
                        .withLastName("Niezbedny")
                        .withMothersMaidenName("Kowalska")
                        .withMaritalStatus(SEPARATED)
                        .withEducation(MIDDLE)
                        .build())
                .withPesel("93070297867")
                .withFamilyMembers(getFamilymembersList(1))
                .build();


        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(MORTGAGE, 100000.00, (byte) 25);
        Set<Guarantor> guarantorSet = new TreeSet<>(List.of(guarantors));
        return new CreditApplication( person, purposeOfLoan, guarantorSet);
    }

    public static CreditApplication create() {
        FamilyMember user02 = new FamilyMember("User02", generateBirthDate(28));
        FamilyMember user03 = new FamilyMember("User03", generateBirthDate(28));
        FamilyMember user04 = new FamilyMember("User04", generateBirthDate(29));
        FamilyMember user05 = new FamilyMember("User05", generateBirthDate(49));
        FamilyMember user06 = new FamilyMember("User06", generateBirthDate(53));

        Guarantor g1 =  Guarantor.Builder.create().withPesel("93070234543").withAge(generateBirthDate(28)).build();
        Guarantor g2 =  Guarantor.Builder.create().withPesel("81081463737").withAge(generateBirthDate(40)).build();
        Guarantor g3 =  Guarantor.Builder.create().withPesel("71031454632").withAge(generateBirthDate(50)).build();

        Set<Guarantor> guarantors = new TreeSet<>();
        guarantors.add(g1);
        guarantors.add(g2);
        guarantors.add(g3);

        SourceOfIncome[] source = {new SourceOfIncome(EMPLOYMENT_CONTRACT, 6000.00)};
        ContactData contactData = ContactData.Builder
                .create()
                .withEmail("xd@wp.pl")
                .withPhoneNumber("123456789")
                .withHomeAddress(new Address("1","","","",""))
                .withCorespondenceAddress(new Address("2","","","",""))
                .build();
        NaturalPerson person =  NaturalPerson.Builder
                .create()
                .withContactData(contactData)
                .withFinanceData(new FinanceData(source))
                .withPersonalData(new PersonalData.Builder()
                        .withName("Jan")
                        .withLastName("Niezbedny")
                        .withMothersMaidenName("Kowalska")
                        .withMaritalStatus(SEPARATED)
                        .withEducation(TERTIARY)
                        .build())
                .withFamilyMembers(Arrays.asList(user02,user03,user04,user05,user06))
                .withPesel("93070297867")
                .build();


        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(MORTGAGE, 100000.00, (byte) 25);

        return new CreditApplication(person, purposeOfLoan, guarantors);
    }
}
