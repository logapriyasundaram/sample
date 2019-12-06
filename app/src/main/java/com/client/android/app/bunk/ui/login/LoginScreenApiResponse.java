package com.client.android.app.bunk.ui.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.client.android.app.bunk.net.ApiResponse;
import com.client.android.app.bunk.net.Status;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import static com.client.android.app.bunk.net.Status.LOADING;
import static com.client.android.app.bunk.net.Status.SUCCESS;
import static com.client.android.app.bunk.net.Status.ERROR;


public class LoginScreenApiResponse extends ApiResponse {

    private ServiceType serviceType;

    public ServiceType getServiceType() {
        return serviceType;
    }

    private LoginScreenApiResponse(Status status, @Nullable JsonElement data, @Nullable Throwable error) {
        super(status, data, error);
    }

    public LoginScreenApiResponse setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public static LoginScreenApiResponse loading() {
        return new LoginScreenApiResponse(LOADING, null, null);
    }

    public static LoginScreenApiResponse success(@NonNull JsonElement data) {
        return new LoginScreenApiResponse(SUCCESS, data, null);
    }

    public static LoginScreenApiResponse error(@NonNull Throwable error) {
        return new LoginScreenApiResponse(ERROR, null, error);
    }

    public enum ServiceType {
        LOGIN
    }
}
