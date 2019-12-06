package com.client.android.app.bunk.net;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCallInterface {

    //@Headers(Constant.API_AUTH_KEY_WITH_VALUE)
    @GET(WebserviceUrls.GET_LUBEDB)
    Observable<JsonElement> getApiInfoRequest();

    @GET(WebserviceUrls.GETCUSTOMERVEHICLENUMBER)
    Observable<JsonElement> fetchCustomerVehicleNumber(@Query(Constant.VEHICLENUMBER_PARAM) String vehicleNumber);

    /*@GET(WebserviceUrls.SENDMSG)
    Observable<JsonElement> fetchSendSMS(@Query(Constant.USER_PARAM) String user,@Query(Constant.PASS_PARAM) String pass,@Query(Constant.SENDER_PARAM) String sender,@Query(Constant.PHONE_PARAM) String phone,@Query(Constant.TEXT_PARAM) String text,@Query(Constant.PRIORITY_PARAM) String priority,@Query(Constant.STYPE_PARAM) String stype);
*/
    @Headers("Content-Type: application/json")
    @POST(WebserviceUrls.LOGIN)
    Observable<JsonElement> postLoginWithUserRequest(@Body HashMap<String, String> param);

    @Headers("Content-Type: application/json")
    @POST(WebserviceUrls.SAVEDETAIL)
    Observable<JsonElement> postSaveDetailRequest(@Body RequestBody param);
}
