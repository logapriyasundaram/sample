package com.client.android.app.bunk.net;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCallInterfaceUrl1 {

    @GET(WebserviceUrls.SENDMSG)
    Observable<JsonElement> fetchSendSMS(@Query(Constant.USER_PARAM) String user, @Query(Constant.PASS_PARAM) String pass, @Query(Constant.SENDER_PARAM) String sender, @Query(Constant.PHONE_PARAM) String phone, @Query(Constant.TEXT_PARAM) String text, @Query(Constant.PRIORITY_PARAM) String priority, @Query(Constant.STYPE_PARAM) String stype);
}
