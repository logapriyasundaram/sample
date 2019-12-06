package com.client.android.app.bunk.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PickOilItem implements Serializable {

    @SerializedName("Lube_id")
    public String Lube_id;
    @SerializedName("Lube_Name")
    public String Lube_Name;
    @SerializedName("Expiration_KMs")
    public String Expiration_KMs;
    @SerializedName("Expiration_Days")
    public String Expiration_Days;

    public String getLube_id() {
        return Lube_id;
    }

    public void setLube_id(String lube_id) {
        Lube_id = lube_id;
    }

    public String getLube_Name() {
        return Lube_Name;
    }

    public void setLube_Name(String lube_Name) {
        Lube_Name = lube_Name;
    }

    public String getExpiration_KMs() {
        return Expiration_KMs;
    }

    public void setExpiration_KMs(String expiration_KMs) {
        Expiration_KMs = expiration_KMs;
    }

    public String getExpiration_Days() {
        return Expiration_Days;
    }

    public void setExpiration_Days(String expiration_Days) {
        Expiration_Days = expiration_Days;
    }
}
