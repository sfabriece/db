package model;

/**
 * Created by helifab on 28.03.2014.
 */
public class Address {
    private int zip;
    private String street;
    private String city;

    public Address(int zip, String street, String city) {
        this.zip = zip;
        this.street = street;
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (zip != address.zip) return false;
        if (!city.equals(address.city)) return false;
        if (!street.equals(address.street)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = zip;
        result = 31 * result + street.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
