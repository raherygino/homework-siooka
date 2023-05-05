package com.gsoft.homework.models;

public class Location {
    public String country, formattedAddress, locality;

    public Location(String country, String formattedAddress, String locality) {
        this.country = country;
        this.formattedAddress = formattedAddress;
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public String getLocality() {
        return locality;
    }
}
