package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.ExactlyOneNotNull;

import java.io.Serializable;
import java.util.List;

@ExactlyOneNotNull({"nip", "regon"})
public class SelfEmployed extends Person implements Serializable {
    public static final long serialVersionUID = 1;
    private final String nip;
    private final String regon;
    private final int yearsSinceFounded;

    private SelfEmployed(PersonalData personalData, ContactData contactData, FinanceData financeData, List<FamilyMember> familyMembers, String nip, String regon, int yearsSinceFounded) {
        super(personalData, contactData, financeData, familyMembers);
        this.nip = nip;
        this.regon = regon;
        this.yearsSinceFounded = yearsSinceFounded;
    }

    public String getNip() {
        return nip;
    }

    public String getRegon() {
        return regon;
    }

    public int getYearsSinceFounded() {
        return yearsSinceFounded;
    }

    public static class Builder{
        private String nip;
        private String regon;
        private int yearsSinceFounded;
        private PersonalData personalData;
        private ContactData contactData;
        private FinanceData financeData;
        private List<FamilyMember> familyMembers;

        public static Builder create(){
            return new Builder();
        }

        public Builder withNip(String nip){
            this.nip = nip;
            return this;
        }

        public Builder withRegon(String regon){
            this.regon = regon;
            return this;
        }

        public Builder withYearsSinceFounded(Integer yearsSinceFounded){
            this.yearsSinceFounded = yearsSinceFounded;
            return this;
        }

        public Builder withPersonalData(PersonalData personalData){
            this.personalData = personalData;
            return this;
        }
        public Builder withContactData(ContactData contactData){
            this.contactData = contactData;
            return this;
        }
        public Builder withFinanceData(FinanceData financeData){
            this.financeData = financeData;
            return this;
        }
        public Builder withFamilyMembers(List<FamilyMember> familyMembers) {
            this.familyMembers = familyMembers;
            return this;
        }

        public SelfEmployed build(){
            return new SelfEmployed(this.personalData, this.contactData, this.financeData, this.familyMembers, this.nip, this.regon, this.yearsSinceFounded);
        }
    }
}
