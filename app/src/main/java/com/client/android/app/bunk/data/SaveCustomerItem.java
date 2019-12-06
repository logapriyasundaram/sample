package com.client.android.app.bunk.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class SaveCustomerItem implements Parcelable {

    @ColumnInfo(name = "mobile_number")
    @SerializedName("mobile_number")
    public String mobileNumber;

    @ColumnInfo(name = "customer_name")
    @SerializedName("customer_name")
    public String customerName;

    @ColumnInfo(name = "oil_changed")
    @SerializedName("oil_changed")
    public String oilChanged;

    @ColumnInfo(name = "odometer_reading")
    @SerializedName("odometer_reading")
    public String odometerReading;

    @ColumnInfo(name = "date_of_oil")
    @SerializedName("date_of_oil")
    public String dateOfOil;

    @ColumnInfo(name = "customer_email")
    @SerializedName("customer_email")
    public String customerEmail;

    @ColumnInfo(name = "tentative_km")
    @SerializedName("tentative_km")
    public String tentativeKm;
    @ColumnInfo(name = "tentative_date")
    @SerializedName("tentative_date")
    public String tentativeDate;

    @PrimaryKey
    @ColumnInfo(name = "vehicle_number")
    @SerializedName("vehicle_number")
    @NonNull
    public String vehicleNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOilChanged() {
        return oilChanged;
    }

    public void setOilChanged(String oilChanged) {
        this.oilChanged = oilChanged;
    }

    public String getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(String odometerReading) {
        this.odometerReading = odometerReading;
    }

    public String getDateOfOil() {
        return dateOfOil;
    }

    public void setDateOfOil(String dateOfOil) {
        this.dateOfOil = dateOfOil;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getTentativeKm() {
        return tentativeKm;
    }

    public void setTentativeKm(String tentativeKm) {
        this.tentativeKm = tentativeKm;
    }

    public String getTentativeDate() {
        return tentativeDate;
    }

    public void setTentativeDate(String tentativeDate) {
        this.tentativeDate = tentativeDate;
    }

    @NonNull
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(@NonNull String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String toString() {
        return customerName;
                /*"CountriesItem{" +
                        "country_dial_code = '" + countryDialCode + '\'' +
                        ",country_name = '" + countryName + '\'' +
                        ",country_id = '" + countryId + '\'' +
                        "}";*/
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mobileNumber);
        dest.writeString(this.customerName);
        dest.writeString(this.vehicleNumber);
        dest.writeString(this.oilChanged);
        dest.writeString(this.odometerReading);
        dest.writeString(this.dateOfOil);
        dest.writeString(this.customerEmail);
        dest.writeString(this.tentativeKm);
        dest.writeString(this.tentativeDate);
    }

    public SaveCustomerItem(@NonNull String vehicleNumber,String mobileNumber, String customerName, String oilChanged, String odometerReading, String dateOfOil, String customerEmail, String tentativeKm, String tentativeDate) {
        this.mobileNumber = mobileNumber;
        this.customerName = customerName;
        this.vehicleNumber = vehicleNumber;
        this.oilChanged = oilChanged;
        this.odometerReading = odometerReading;
        this.dateOfOil = dateOfOil;
        this.customerEmail = customerEmail;
        this.tentativeKm = tentativeKm;
        this.tentativeDate = tentativeDate;
    }

    protected SaveCustomerItem(Parcel in) {
        this.mobileNumber = in.readString();
        this.customerName = in.readString();
        this.vehicleNumber = in.readString();
        this.oilChanged = in.readString();
        this.odometerReading = in.readString();
        this.dateOfOil = in.readString();
        this.customerEmail = in.readString();
        this.tentativeKm = in.readString();
        this.tentativeDate = in.readString();
    }

    public static final Creator<SaveCustomerItem> CREATOR = new Creator<SaveCustomerItem>() {
        @Override
        public SaveCustomerItem createFromParcel(Parcel source) {
            return new SaveCustomerItem(source);
        }

        @Override
        public SaveCustomerItem[] newArray(int size) {
            return new SaveCustomerItem[size];
        }
    };
}