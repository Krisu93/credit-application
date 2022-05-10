package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.ValidationCollection;
import pl.javaskills.creditapp.core.annotation.ValidationObject;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public abstract class Person implements Serializable {
    public static final long serialVersionUID = 1;
    @NotNull()
    @ValidationObject
    private final PersonalData personalData;
    @NotNull()
    @ValidationObject
    private final ContactData contactData;
    @NotNull()
    @ValidationObject
    private final FinanceData financeData;
    @NotNull()
    @ValidationCollection
    private final List<FamilyMember> familyMembers;

    protected Person(PersonalData personalData, ContactData contactData, FinanceData financeData, List<FamilyMember> familyMembers) {
        this.personalData = personalData;
        this.contactData = contactData;
        this.financeData = financeData;
        this.familyMembers = familyMembers;
        Collections.sort(this.familyMembers);
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public FinanceData getFinanceData() {
        return financeData;
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public double getIncomePerPeople() {
        return getBalance() / (Math.max(getNumOfFamilyDependants(), 1));
    }

    public int getNumOfFamilyDependants() {
        return 1 + this.familyMembers.size();
    }

    public double getBalance(){
        return this.getFinanceData().sumMonthlyIncomeInPln()
                - (this.getFinanceData().getSumOfExpenses(ExpenseType.CREDIT_INSTALLMENT)
                        + this.getFinanceData().getSumOfExpenses(ExpenseType.PERSONAL)
                        + this.getFinanceData().getSumOfExpenses(ExpenseType.RENT));
    }

    @Override
    public String toString() {
        return "Person{" +
                "personalData=" + personalData +
                ", contactData=" + contactData +
                ", financeData=" + financeData +
                ", familyMembers=" + familyMembers +
                '}';
    }
}
