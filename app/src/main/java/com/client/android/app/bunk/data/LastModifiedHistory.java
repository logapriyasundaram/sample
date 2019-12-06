package com.client.android.app.bunk.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LastModifiedHistory implements Serializable {

    @SerializedName("oil_changed")
    public String oil_changed;
    @SerializedName("date_of_changed")
    public String date_of_changed;
    @SerializedName("km_of_changed")
    public String km_of_changed;

    public String getOil_changed() {
        return oil_changed;
    }

    public void setOil_changed(String oil_changed) {
        this.oil_changed = oil_changed;
    }

    public String getDate_of_changed() {
        return date_of_changed;
    }

    public void setDate_of_changed(String date_of_changed) {
        this.date_of_changed = date_of_changed;
    }

    public String getKm_of_changed() {
        return km_of_changed;
    }

    public void setKm_of_changed(String km_of_changed) {
        this.km_of_changed = km_of_changed;
    }
}
