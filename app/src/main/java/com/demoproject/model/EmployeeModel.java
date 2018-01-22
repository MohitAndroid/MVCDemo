package com.demoproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mohit on 11/1/18.
 */

public class EmployeeModel {



    @SerializedName("address")
    private List<AddressModel> addressModels;


    public List<AddressModel> getAddressModels() {
        return addressModels;
    }

    public void setAddressModels(List<AddressModel> addressModels) {
        this.addressModels = addressModels;
    }


    @Override
    public String toString() {

        return "EmployeeModel{" +
                "addressModels=" + addressModels +
                '}';
    }
}
