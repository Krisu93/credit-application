package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.Regex;
import pl.javaskills.creditapp.core.annotation.ValidationObject;
import pl.javaskills.creditapp.core.annotation.ValidationOptional;
import pl.javaskills.creditapp.core.utils.Constants;

import java.io.Serializable;
import java.util.Optional;

public class ContactData implements Serializable {
    public static final long serialVersionUID = 1;
    @NotNull()
    @Regex(Constants.EMAIL_REGEX)
    private String email;
    @NotNull()
    @Regex(Constants.PHONE_REGEX)
    private String phoneNumber;
    @NotNull()
    @ValidationObject
    private Address homeAddress;
    @NotNull()
    @ValidationOptional
    private transient Optional<Address> corespondenceAddress;


    private ContactData(String email, String phoneNumber, Address homeAddress, Optional<Address> corespondenceAddress) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.corespondenceAddress = corespondenceAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Optional<Address> getCorespondenceAddress() {
        return corespondenceAddress;
    }

    public static class Builder {
        private String email;
        private String phoneNumber;
        private Address corespondenceAddress;
        private Address homeAddress;

        public static Builder create() {
            return new Builder();
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withCorespondenceAddress(Address corespondenceAddress) {
            this.corespondenceAddress = corespondenceAddress;
            return this;
        }

        public Builder withHomeAddress(Address homeAddress) {
            this.homeAddress = homeAddress;
            return this;
        }

        public ContactData build() {
            Optional<Address> corespondenceAddress = (this.homeAddress == null || this.corespondenceAddress == null || this.homeAddress.equals(this.corespondenceAddress)) ? Optional.empty() : Optional.ofNullable(this.corespondenceAddress);

            return new ContactData(this.email, this.phoneNumber, this.homeAddress, corespondenceAddress);
        }
    }
}
