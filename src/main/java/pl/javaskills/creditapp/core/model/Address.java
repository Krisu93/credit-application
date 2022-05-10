package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;

import java.io.Serializable;

public class Address implements Serializable {
    public static final long serialVersionUID = 1;
    @NotNull()
    private final String street;
    @NotNull()
    private final String city;
    @NotNull()
    private final String houseNumber;
    @NotNull()
    private final String zipCode;
    @NotNull()
    private final String state;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public Address(String street, String city, String houseNumber, String zipCode, String state) {
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) && city.equals(address.city) && houseNumber.equals(address.houseNumber) && zipCode.equals(address.zipCode) && state.equals(address.state);
    }

}
