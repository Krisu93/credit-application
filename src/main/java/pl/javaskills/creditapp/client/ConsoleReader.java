package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.utils.Constants;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.utils.StringCreator;

import java.util.Scanner;

public class ConsoleReader implements CreditApplicationReader{

    private Scanner in;

    public ConsoleReader() {
        this.in = new Scanner(System.in);
    }


    @Override
    public CreditApplication read() {
        return readInputParameters();
    }

    private CreditApplication readInputParameters() {

        String name = getName("Enter your name:", Constants.NAME_REGEX);
        String lastName = getName("Enter your last name:", Constants.LASTNAME_REGEX);
        String motherName = getName("Enter your mother’s maiden name:", Constants.MOTHERS_MAIDEN_REGEX);
        MaritalStatus maritalStatus = getMaritalStatus("What is your marital status? ");
        Education education = getEducation("What is your education level? ");
        String email = getEmail("Enter your email address:", Constants.EMAIL_REGEX);
        String phone = getPhone("Enter your phone number:");
        int cntSources = getCntSources("How many sources of income do you have:");
        FinanceData financeData = readSourceOfIncome("Enter net monthly income of source of income ", cntSources);
        int numOfFamily = getNumberOfFamily("Enter number of family dependants (including applicant):");
        Type typePurpose = getType("What is purpose of loan? ");
        double amount = getAmount("Enter loan amount:");
        byte period = getPeriod("Enter loan period (in years):");

        ContactData contactData = ContactData.Builder
                .create()
                .withEmail(email)
                .withPhoneNumber(phone)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(typePurpose, amount, period);
        NaturalPerson naturalPerson = NaturalPerson.Builder
                .create()
                .withContactData(contactData)
                .withFinanceData(financeData)
                .withPersonalData(new PersonalData.Builder()
                        .withName(name)
                        .withLastName(lastName)
                        .withMothersMaidenName(motherName)
                        .withMaritalStatus(maritalStatus)
                        .withEducation(education)
                        .build())
                .withPesel("93070297867")
                .build();

        return new CreditApplication(naturalPerson,  purposeOfLoan);
    }

    private byte getPeriod(String s) {
        String periodInput;
        do {
            System.out.println(s);
            periodInput = this.in.next();
        } while (!NumberValidator.validateInteger(periodInput, 5, 10, 15, 20, 25, 30));
        return Byte.parseByte(periodInput);
    }

    private double getAmount(String s) {
        String amountInput;
        do {
            System.out.println(s);
            amountInput = this.in.next();
        } while (!NumberValidator.validateDouble(amountInput, 0, Double.MAX_VALUE));
        return Double.parseDouble(amountInput);
    }

    private int getCntSources(String s) {
        String sourcesInput;
        do {
            System.out.println(s);
            sourcesInput = this.in.next();
        } while (!NumberValidator.validateInteger(sourcesInput, 1, Integer.MAX_VALUE));
        return Integer.parseInt(sourcesInput);
    }

    private int getNumberOfFamily(String s) {
        String numOfFamilyInput;
        do {
            System.out.println(s);
            numOfFamilyInput = this.in.next();
        } while (!NumberValidator.validateInteger(numOfFamilyInput, 1, Integer.MAX_VALUE));

        return Integer.parseInt(numOfFamilyInput);

    }

    private String getPhone(String s) {
        String phone;
        do {
            System.out.println(s);
            phone = this.in.next();
        } while (!PhoneValidator.validate(phone));
        return phone;
    }

    private String getName(String s, String nameRegex) {
        String name;
        do {
            System.out.println(s);
            name = this.in.next();
        } while (!StringValidator.validateString(name, nameRegex));
        return name;
    }

    private String getEmail(String s, String nameRegex) {
        return getName(s, nameRegex);
    }

    private Type getType(String s) {
        String typeName;
        do {
            System.out.println(s + StringCreator.generateElements(Type.values()));
            typeName = this.in.next().toUpperCase();
        } while (!EnumValidator.isTypeInputValue(typeName));

        Type typePurpose = Type.valueOf(typeName);
        return typePurpose;
    }

    private MaritalStatus getMaritalStatus(String s) {
        String martialStatusName;
        do {
            System.out.println(s + StringCreator.generateElements(MaritalStatus.values()));
            martialStatusName = this.in.next().toUpperCase();
        } while (!EnumValidator.isMaritalStatusInputValue(martialStatusName));

        MaritalStatus maritalStatus = MaritalStatus.valueOf(martialStatusName);
        return maritalStatus;
    }

    private Education getEducation(String s) {
        String educationName;
        do {
            System.out.println(s + StringCreator.generateElements(Education.values()));
            educationName = this.in.next().toUpperCase();
        } while (!EnumValidator.isEducationInputValue(educationName));

        Education education = Education.valueOf(educationName);
        return education;
    }

    private FinanceData readSourceOfIncome(String s, int cntSources) {
        if (cntSources <= 0 || cntSources > IncomeType.values().length)
            return new FinanceData();

        SourceOfIncome[] sourceOfIncome = new SourceOfIncome[cntSources];
        for (int i = 0, cnt = 1; i < cntSources; i++, cnt++) {

            IncomeType incomeType = getIncomeType(cnt);
            double netMonthlyIncome = getNetMonthlyIncome(s, cnt);
            sourceOfIncome[i] = new SourceOfIncome(incomeType, netMonthlyIncome);
        }

        return new FinanceData(sourceOfIncome);
    }

    private double getNetMonthlyIncome(String s, int cnt) {
        double netMonthlyIncome;
        String netMonthlyIncomeInput;
        do {
            System.out.println(s + cnt + ":");
            netMonthlyIncomeInput = this.in.next();
        } while (!NumberValidator.validateDouble(netMonthlyIncomeInput, 0, Double.MAX_VALUE));
        netMonthlyIncome = Double.parseDouble(netMonthlyIncomeInput);
        return netMonthlyIncome;
    }

    private IncomeType getIncomeType(int cnt) {
        String incomeTypeName;
        do {
            System.out.println("Enter type of source of income " + cnt + " " + StringCreator.generateElements(IncomeType.values()));
            incomeTypeName = this.in.next().toUpperCase();
        } while (!EnumValidator.isIncomeTypeInputValue(incomeTypeName));

        IncomeType incomeType = IncomeType.valueOf(incomeTypeName);
        return incomeType;
    }
}
