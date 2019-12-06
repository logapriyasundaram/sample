package com.client.android.app.bunk.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HistoryItem implements Serializable {

    @SerializedName("customer")
    public HistoryCustomerItem historyCustomerItem;
    @SerializedName("last_modified_history")
    public LastModifiedHistory lastModifiedHistory;
    @SerializedName("next_lube_change_expected")
    public NextLubeChangedItem nextLubeChangedItem;

    public HistoryCustomerItem getHistoryCustomerItem() {
        return historyCustomerItem;
    }

    public void setHistoryCustomerItem(HistoryCustomerItem historyCustomerItem) {
        this.historyCustomerItem = historyCustomerItem;
    }

    public LastModifiedHistory getLastModifiedHistory() {
        return lastModifiedHistory;
    }

    public void setLastModifiedHistory(LastModifiedHistory lastModifiedHistory) {
        this.lastModifiedHistory = lastModifiedHistory;
    }

    public NextLubeChangedItem getNextLubeChangedItem() {
        return nextLubeChangedItem;
    }

    public void setNextLubeChangedItem(NextLubeChangedItem nextLubeChangedItem) {
        this.nextLubeChangedItem = nextLubeChangedItem;
    }
}
