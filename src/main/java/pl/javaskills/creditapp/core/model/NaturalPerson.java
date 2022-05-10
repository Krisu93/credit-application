package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.Regex;
import pl.javaskills.creditapp.core.utils.Constants;

import java.io.Serializable;
import java.util.List;

public class NaturalPerson extends Person implements Serializable {
    public static final long serialVersionUID = 1;
    @NotNull()
    @Regex(Constants.PESEL_REGEX)
    private final String pesel;

    public NaturalPerson(PersonalData personalData, ContactData contactData, FinanceData financeData, List<FamilyMember> familyMembers, String pesel) {
        super(personalData, contactData, financeData, familyMembers);
        this.pesel = pesel;
    }

    public String getPesel() {
        return pesel;
    }

    public static class Builder {
        private String pesel;
        private PersonalData personalData;
        private ContactData contactData;
        private FinanceData financeData;
        private List<FamilyMember> familyMembers;

        public static Builder create() {
            return new Builder();
        }

        public Builder withPesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public Builder withPersonalData(PersonalData personalData) {
            this.personalData = personalData;
            return this;
        }

        public Builder withContactData(ContactData contactData) {
            this.contactData = contactData;
            return this;
        }

        public Builder withFinanceData(FinanceData financeData) {
            this.financeData = financeData;
            return this;
        }

        public Builder withFamilyMembers(List<FamilyMember> familyMembers) {
            this.familyMembers = familyMembers;
            return this;
        }

        public NaturalPerson build() {
            return new NaturalPerson(this.personalData, this.contactData, this.financeData, this.familyMembers, this.pesel);
        }
    }

    @Override
    public String toString() {
        return "NaturalPerson{" +
                "personalData=" + getPersonalData() +
                ", contactData=" + getContactData() +
                ", financeData=" + getFinanceData() +
                ", familyMembers=" + getFamilyMembers() +
                ", pesel='" + pesel + '\'' +
                '}';
    }
}
