package com.demoproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mohit on 11/1/18.
 */

public class AddressModel {


    @SerializedName("street")
    private String streetName;

    @SerializedName("city")
    private String city;

    @SerializedName("zipcode")
    private String zipcode;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
