package com.client.android.app.bunk.net;

import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestHandler {

    private ApiCallInterface apiCallInterface;
    private ApiCallInterfaceUrl1 apiCallInterfaceUrl1;
    /*private static final RequestHandler requestHandler = new RequestHandler();

    public static RequestHandler GetInstance() {
        return requestHandler;
    }*/

    private HashMap<String, String> postLoginWithUserParam(String username, String password) {
        HashMap<String, String> param = new HashMap<>();
        param.put(Constant.USERNAME_PARAM, username);
        param.put(Constant.PASSWORD_PARAM, password);
        return param;
    }
    private RequestBody postSaveDetailsParam(String vehicleNumber,String customer_name,String mobile_number,String email,String oil_changed, String odometer_reading, String dateOfOil,String km, String tentativeDate) {
        JSONObject saveObj = new JSONObject();

        RequestBody bodyRequest=null;
        JSONObject paramObject=null;
        try {
            paramObject = new JSONObject();
            paramObject.put(Constant.CUSTOMER_NAME, customer_name);
            paramObject.put(Constant.VEHICLE_NUM, vehicleNumber);
            paramObject.put(Constant.MOBILE_NUM, mobile_number);
            paramObject.put(Constant.EMAIL, email);
            paramObject.put(Constant.OIL_CHANGED, oil_changed);
            paramObject.put(Constant.ODOMETER_READING, odometer_reading);
            paramObject.put(Constant.DATE_OF_OIL_CHANGED, dateOfOil);
            paramObject.put(Constant.TENTATIVE_KM, km);
            paramObject.put(Constant.TENTATIVE_DATE, tentativeDate);

            JSONArray jsonArray = new JSONArray();

            jsonArray.put(paramObject);
            saveObj.put("entries", jsonArray);

             bodyRequest = RequestBody.create(MediaType.parse("application/json"), saveObj.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bodyRequest;
    }
    public RequestHandler(ApiCallInterface apiCallInterface, ApiCallInterfaceUrl1 apiCallInterfaceUrl1) {
        this.apiCallInterface = apiCallInterface;
        this.apiCallInterfaceUrl1 = apiCallInterfaceUrl1;
    }

    public Observable<JsonElement> postLoginWithUserRequest(String apiKey, String username, String password) {
        return apiCallInterface.postLoginWithUserRequest(postLoginWithUserParam(username, password));
    }

    public Observable<JsonElement> getFetchLubeRequest() {
        return apiCallInterface.getApiInfoRequest();
    }
    public Observable<JsonElement> getVehicleDetailRequest(String vehicleNumber) {
        return apiCallInterface.fetchCustomerVehicleNumber(vehicleNumber);
    }
    public Observable<JsonElement> fetchSendSMSRequest(String user,String pass,String sender,String phone,String text,String priority,String stype) {
        return apiCallInterfaceUrl1.fetchSendSMS(user,pass,sender,phone,text,priority,stype);
    }
    public Observable<JsonElement> saveDetailsRequest(String vehicleNumber,String customer_name,String mobile_number,String email,String oil_changed, String odometer_reading, String dateOfOil,String km, String tentativeDate) {
        return apiCallInterface.postSaveDetailRequest(postSaveDetailsParam(vehicleNumber,customer_name,mobile_number,email,oil_changed,odometer_reading,dateOfOil,km,tentativeDate));
    }
}
