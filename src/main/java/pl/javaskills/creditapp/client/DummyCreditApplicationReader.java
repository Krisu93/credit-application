package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.model.*;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import static pl.javaskills.creditapp.core.model.Education.TERTIARY;
import static pl.javaskills.creditapp.core.model.IncomeType.EMPLOYMENT_CONTRACT;
import static pl.javaskills.creditapp.core.model.MaritalStatus.SEPARATED;
import static pl.javaskills.creditapp.core.model.Type.MORTGAGE;
import static pl.javaskills.creditapp.utils.AgeUtils.generateBirthDate;

public class DummyCreditApplicationReader implements CreditApplicationReader{
    @Override
    public CreditApplication read() {
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
                        .withEducation(TERTIARY)
                        .build())
                .withFamilyMembers(Arrays.asList(user02,user03,user04,user05,user06))
                .withPesel("93070297867")
                .build();


        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(MORTGAGE, 100000.00, (byte) 25);

        return new CreditApplication(person, purposeOfLoan, guarantors, ZoneId.of("Europe/Paris"), new Locale("pl","PL"));
    }
}
