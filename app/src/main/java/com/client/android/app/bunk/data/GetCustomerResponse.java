package com.client.android.app.bunk.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCustomerResponse {

    @SerializedName("history")
    private List<HistoryItem> historyCustomerItemList;
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("customer")
    private CustomerItem customer;

    public List<HistoryItem> getHistoryCustomerItemList() {
        return historyCustomerItemList;
    }

    public void setHistoryCustomerItemList(List<HistoryItem> historyCustomerItemList) {
        this.historyCustomerItemList = historyCustomerItemList;
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

    public CustomerItem getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerItem customer) {
        this.customer = customer;
    }
}
