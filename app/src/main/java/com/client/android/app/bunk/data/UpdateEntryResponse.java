package com.client.android.app.bunk.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateEntryResponse {

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private List<CustomerReponseItem> customerReponseItemList;

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

    public List<CustomerReponseItem> getCustomerReponseItemList() {
        return customerReponseItemList;
    }

    public void setCustomerReponseItemList(List<CustomerReponseItem> customerReponseItemList) {
        this.customerReponseItemList = customerReponseItemList;
    }
}
