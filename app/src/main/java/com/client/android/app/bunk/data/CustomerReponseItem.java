package com.client.android.app.bunk.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerReponseItem  implements Serializable {

    @SerializedName("customer_name")
    public String customer_name;
    @SerializedName("vehicle_num")
    public String vehicle_num;
    @SerializedName("mobile_number")
    public String mobile_number;
    @SerializedName("oil_changed")
    public String oil_changed;
    @SerializedName("odometer_reading")
    public String odometer_reading;
    @SerializedName("date_of_oil_change")
    public String date_of_oil_change;
    @SerializedName("tentative_km")
    public String tentative_km;

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getVehicle_num() {
        return vehicle_num;
    }

    public void setVehicle_num(String vehicle_num) {
        this.vehicle_num = vehicle_num;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getOil_changed() {
        return oil_changed;
    }

    public void setOil_changed(String oil_changed) {
        this.oil_changed = oil_changed;
    }

    public String getOdometer_reading() {
        return odometer_reading;
    }

    public void setOdometer_reading(String odometer_reading) {
        this.odometer_reading = odometer_reading;
    }

    public String getDate_of_oil_change() {
        return date_of_oil_change;
    }

    public void setDate_of_oil_change(String date_of_oil_change) {
        this.date_of_oil_change = date_of_oil_change;
    }

    public String getTentative_km() {
        return tentative_km;
    }

    public void setTentative_km(String tentative_km) {
        this.tentative_km = tentative_km;
    }
}
