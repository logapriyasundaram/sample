package com.client.android.app.bunk.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLubeListReponse {

    @SerializedName("data")
    private List<PickOilItem> dataLube;
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;

    public List<PickOilItem> getDataLube() {
        return dataLube;
    }

    public void setDataLube(List<PickOilItem> dataLube) {
        this.dataLube = dataLube;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
