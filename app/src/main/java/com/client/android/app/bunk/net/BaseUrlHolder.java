package com.client.android.app.bunk.net;

public class BaseUrlHolder {
    private static String baseUrl;


    /*public BaseUrlHolder(String baseUrl){
        BaseUrlHolder.baseUrl =baseUrl;
    }*/
    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrls) {
        baseUrl = baseUrls;
    }
}