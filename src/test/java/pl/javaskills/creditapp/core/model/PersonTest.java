package pl.javaskills.creditapp.core.model;

public class PersonTest {

    public static final class Builder {
        private String name;
        private String lastName;
        private String mothersMaidenName;
        private MaritalStatus maritalStatus;
        private Education education;
        private String email;
        private String phoneNumber;
        private SourceOfIncome [] sourceOfIncomes;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder mothersMaidenName(String mothersMaidenName) {
            this.mothersMaidenName = mothersMaidenName;
            return this;
        }

        public Builder maritalStatus(MaritalStatus maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public Builder education(Education education) {
            this.education = education;
            return this;
        }

        public Builder sourceOfIncomes(SourceOfIncome... sourceOfIncomes) {
            this.sourceOfIncomes = sourceOfIncomes;
            return this;
        }

        public Builder email (String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber (String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Person build() {

            PersonalData personalData = new PersonalData.Builder()
                    .withName(name)
                    .withLastName(lastName)
                    .withMothersMaidenName(mothersMaidenName)
                    .withMaritalStatus(maritalStatus)
                    .withEducation(education)
                    .build();
            ContactData contactData = ContactData.Builder
                    .create()
                    .withEmail(this.email)
                    .withPhoneNumber(this.phoneNumber)
                    .build();
            FinanceData financeData = new FinanceData(this.sourceOfIncomes);

            return NaturalPerson.Builder
                    .create()
                    .withContactData(contactData)
                    .withFinanceData(financeData)
                    .withPersonalData(personalData)
                    .build();
        }
    }
}
