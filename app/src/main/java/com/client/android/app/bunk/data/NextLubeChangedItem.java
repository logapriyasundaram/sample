package com.client.android.app.bunk.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NextLubeChangedItem implements Serializable {
    @SerializedName("date")
    public String date;
    @SerializedName("km")
    public String km;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }
}
