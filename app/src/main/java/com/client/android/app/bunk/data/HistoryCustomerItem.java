package com.client.android.app.bunk.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HistoryCustomerItem implements Serializable {

    @SerializedName("name")
    public String name;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("email")
    public String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
