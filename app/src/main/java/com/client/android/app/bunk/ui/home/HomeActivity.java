package com.client.android.app.bunk.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.client.android.app.bunk.R;
import com.client.android.app.bunk.base.BaseActivity;
import com.client.android.app.bunk.data.GetCustomerResponse;
import com.client.android.app.bunk.data.GetLubeListReponse;
import com.client.android.app.bunk.data.PickOilItem;
import com.client.android.app.bunk.data.SaveCustomerItem;
import com.client.android.app.bunk.data.UpdateEntryResponse;
import com.client.android.app.bunk.di.module.NetworkModule;
import com.client.android.app.bunk.localstorage.PxDatabase;
import com.client.android.app.bunk.net.ApiResponse;
import com.client.android.app.bunk.net.BaseUrlHolder;
import com.client.android.app.bunk.net.RequestHandler;
import com.client.android.app.bunk.net.WebserviceUrls;
import com.client.android.app.bunk.ui.Success.SuccessScreenActivity;
import com.client.android.app.bunk.ui.login.LoginActivity;
import com.client.android.app.bunk.ui.login.LoginScreenApiResponse;
import com.client.android.app.bunk.utils.CustomDialogModal;
import com.client.android.app.bunk.utils.DialogClickListner;
import com.client.android.app.bunk.utils.Utils;
import com.google.gson.Gson;
import com.product.annotationbuilder.ProductBindView;
import com.product.process.annotation.Initialize;
import com.product.process.annotation.Onclick;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends BaseActivity {

    @Initialize(R.id.imgReload)
    ImageView imgReload;
    @Initialize(R.id.tvTitle)
    TextView tvTitle;
    @Initialize(R.id.rlVehicleNo)
    RelativeLayout rlVehicleNo;
    @Initialize(R.id.rlCustomerDetails)
    RelativeLayout rlCustomerDetails;
    @Initialize(R.id.rlVehicleDetails)
    RelativeLayout rlVehicleDetails;
    @Initialize(R.id.imgVehicle)
    ImageView imgVehicle;
    @Initialize(R.id.etVehicleNo)
    EditText etVehicleNo;
    @Initialize(R.id.llUserName)
    LinearLayout llUserName;
    @Initialize(R.id.llMobileNumber)
    LinearLayout llMobileNumber;
    /*@Initialize(R.id.llEmail)
    LinearLayout llEmail;*/
    @Initialize(R.id.llPickOil)
    LinearLayout llPickOil;
    @Initialize(R.id.llodoMeter)
    LinearLayout llodoMeter;
    /*@Initialize(R.id.llDate)
    LinearLayout llDate;*/
    @Initialize(R.id.imgProfile)
    ImageView imgProfile;
    @Initialize(R.id.etFirstName)
    EditText etFirstName;
    @Initialize(R.id.etMobile)
    EditText etMobile;
    @Initialize(R.id.etEmail)
    EditText etEmail;
    @Initialize(R.id.etOdoMerter)
    EditText etOdoMerter;
    @Initialize(R.id.etDate)
    EditText etDate;
    @Initialize(R.id.tvPick)
    TextView tvPick;
    @Initialize(R.id.imgPickArrow)
    ImageView imgPickArrow;
    @Initialize(R.id.imgDate)
    ImageView imgDate;
    @Initialize(R.id.imgodo)
    ImageView imgodo;
    @Initialize(R.id.imgOilChange)
    ImageView imgOilChange;
    @Initialize(R.id.imgSave)
    FloatingActionButton imgSave;
    @Initialize(R.id.imgAddEmail)
    ImageView imgAddEmail;
    @Initialize(R.id.imgPhone)
    ImageView imgPhone;
    @Initialize(R.id.tvDate)
    TextView tvDate;
    @Initialize(R.id.tvOdoMeter)
    TextView tvOdoMeter;
    @Initialize(R.id.tvPickError)
    TextView tvPickError;
    @Initialize(R.id.tvEmail)
    TextView tvEmail;
    @Initialize(R.id.tvMobileNumber)
    TextView tvMobileNumber;

    @Initialize(R.id.tvFirstName)
    TextView tvFirstName;
    @Initialize(R.id.tvVehicle)
    TextView tvVehicle;
    @Initialize(R.id.imgLogout)
    ImageView imgLogout;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Inject
    RequestHandler requestHandler;
    static int topBottomSpace;

    int mSelectedItemOil;
    boolean oilSelFirstTime;
    String oilValue;
    List<String> oilList = new ArrayList<>();
    @Initialize(R.id.PickRelativeLayout)
    RelativeLayout PickRelativeLayout;
    final Calendar myCalendar = Calendar.getInstance();
    List<PickOilItem> arralist;
    String tentativeKm = null, tentativedate = null;

    PxDatabase pxDatabase;
    boolean isSaveDate;

    @Override
    protected int layoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pxDatabase = new PxDatabase(this);
        ProductBindView.bind(this);
        setDynamicValue();
        /*if (pxDatabase != null) {
            SaveCustomerItem saveCustomerItem = pxDatabase.getFirstIdentity();
            if (saveCustomerItem != null) {
                if (Utils.isNetworkConnected(HomeActivity.this)) {
                    isSaveDate = true;
                    updateVehicleDetails(saveCustomerItem.getVehicleNumber(), saveCustomerItem.getCustomerName(), saveCustomerItem.getMobileNumber(), saveCustomerItem.getCustomerEmail(), saveCustomerItem.getOilChanged(), saveCustomerItem.getOdometerReading(), saveCustomerItem.getDateOfOil(), saveCustomerItem.getTentativeKm(), saveCustomerItem.getTentativeDate());
                }
            }else{*/
                if (Utils.isNetworkConnected(HomeActivity.this)) {
                    fetchLube();
                } else {
                    CustomDialogModal.newInstance(HomeActivity.this, getResources().getString(R.string.error_head_oops), getResources().getString(R.string.error_no_internet),
                            CustomDialogModal.DialogType.ALERT, screenWidth, screenHeight, new DialogClickListner() {
                                @Override
                                public void onClick(int clickedId, Dialog dialog, String data) {

                                }
                            });
                }
           /* }*/
        /*}*/

        isTabletDevice(this);
        tvTitle.setText("Enroll My Oil Sale");
        functionality();
        Utils.saveBoolToPrefs(getApplicationContext(), "isLogin", true);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void functionality() {
        etDate.setInputType(InputType.TYPE_NULL);


        etVehicleNo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tvVehicle.getVisibility() == View.VISIBLE) {
                    tvVehicle.setVisibility(View.GONE);
                }
                return false; // return is important...
            }
        });
        etFirstName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tvFirstName.getVisibility() == View.VISIBLE) {
                    tvFirstName.setVisibility(View.GONE);
                }
                return false; // return is important...
            }
        });
        etMobile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tvMobileNumber.getVisibility() == View.VISIBLE) {
                    tvMobileNumber.setVisibility(View.GONE);
                }
                return false; // return is important...
            }
        });
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        etDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(HomeActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        etVehicleNo.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (s.length() >= 10) {

                    if (Utils.isNetworkConnected(HomeActivity.this)) {
                        fetchVehicleNumber(etVehicleNo.getText().toString().trim());
                    } else {
                        CustomDialogModal.newInstance(HomeActivity.this, getResources().getString(R.string.error_head_oops), getResources().getString(R.string.error_no_internet),
                                CustomDialogModal.DialogType.ALERT, screenWidth, screenHeight, new DialogClickListner() {
                                    @Override
                                    public void onClick(int clickedId, Dialog dialog, String data) {

                                    }
                                });
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(HomeActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void setDynamicValue() {
        int imgReloadIconWidth = (int) (screenWidth * 0.075);
        int imgReloadIconHeight = (int) (screenWidth * 0.075);
        int imgSaveParamsIconWidth = (int) (screenWidth * 0.095);
        int imgSaveParamsIconHeight = (int) (screenWidth * 0.095);
        int imgPickArrowIconWidth = (int) (screenWidth * 0.055);
        int imgPickArrowIconHeight = (int) (screenWidth * 0.055);

        int imgIconWidth = (int) (screenWidth * 0.070);
        int imgEmailIconWidth = (int) (screenWidth * 0.075);
        int imgIconHeight = (int) (screenWidth * 0.070);

        topBottomSpace = (int) (screenHeight * 0.0089);

        RelativeLayout.LayoutParams imgLayParams = (RelativeLayout.LayoutParams) imgReload.getLayoutParams();
        imgLayParams.width = imgReloadIconWidth;
        imgLayParams.height = imgReloadIconHeight;
        imgReload.setLayoutParams(imgLayParams);

        RelativeLayout.LayoutParams imgLogoutLayParams = (RelativeLayout.LayoutParams) imgLogout.getLayoutParams();
        imgLogoutLayParams.width = imgReloadIconWidth;
        imgLogoutLayParams.height = imgReloadIconHeight;
        imgLogout.setLayoutParams(imgLogoutLayParams);

//        RelativeLayout.LayoutParams imgSaveParams = (RelativeLayout.LayoutParams) imgSave.getLayoutParams();
//        imgSaveParams.width = imgSaveParamsIconWidth;
//        imgSaveParams.height = imgSaveParamsIconHeight;
//        imgSaveParams.setMargins(0, 0, topBottomSpace * 3, topBottomSpace * 5);
//        imgSave.setLayoutParams(imgSaveParams);

        RelativeLayout.LayoutParams imgPickArrowParams = (RelativeLayout.LayoutParams) imgPickArrow.getLayoutParams();
        imgPickArrowParams.width = imgPickArrowIconWidth;
        imgPickArrowParams.height = imgPickArrowIconHeight;
        imgPickArrow.setLayoutParams(imgPickArrowParams);

        RelativeLayout.LayoutParams imgDateParams = (RelativeLayout.LayoutParams) imgDate.getLayoutParams();
        imgDateParams.width = imgIconWidth;
        imgDateParams.height = imgIconHeight;
        imgDate.setLayoutParams(imgDateParams);

        RelativeLayout.LayoutParams imgAddEmailParams = (RelativeLayout.LayoutParams) imgAddEmail.getLayoutParams();
        imgAddEmailParams.width = imgEmailIconWidth;
        imgAddEmailParams.height = imgIconHeight;
        imgAddEmail.setLayoutParams(imgAddEmailParams);

        RelativeLayout.LayoutParams imgPhoneParams = (RelativeLayout.LayoutParams) imgPhone.getLayoutParams();
        imgPhoneParams.width = imgIconWidth;
        imgPhoneParams.height = imgIconHeight;
        imgPhone.setLayoutParams(imgPhoneParams);

        RelativeLayout.LayoutParams imgProfileParams = (RelativeLayout.LayoutParams) imgProfile.getLayoutParams();
        imgProfileParams.width = imgIconWidth;
        imgProfileParams.height = imgIconHeight;
        imgProfile.setLayoutParams(imgProfileParams);

        RelativeLayout.LayoutParams imgOilChangeParams = (RelativeLayout.LayoutParams) imgOilChange.getLayoutParams();
        imgOilChangeParams.width = imgIconWidth;
        imgOilChangeParams.height = imgIconHeight;
        imgOilChange.setLayoutParams(imgOilChangeParams);

        RelativeLayout.LayoutParams imgodoParams = (RelativeLayout.LayoutParams) imgodo.getLayoutParams();
        imgodoParams.width = imgIconWidth;
        imgodoParams.height = imgIconHeight;
        imgodo.setLayoutParams(imgodoParams);

        RelativeLayout.LayoutParams imgVehicleParams = (RelativeLayout.LayoutParams) imgVehicle.getLayoutParams();
        imgVehicleParams.width = imgIconWidth;
        imgVehicleParams.height = imgIconHeight;
        imgVehicle.setLayoutParams(imgVehicleParams);


    }

    public void fetchLube() {
        compositeDisposable.add(requestHandler.getFetchLubeRequest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> apiResponseHandler(ApiResponse.loading(), ServiceType.GETLUBE))
                .subscribe(result -> apiResponseHandler(ApiResponse.success(result), ServiceType.GETLUBE),
                        error -> apiResponseHandler(ApiResponse.error(error), ServiceType.GETLUBE)));
    }

    public void fetchVehicleNumber(String vehicleNumber) {
        compositeDisposable.add(requestHandler.getVehicleDetailRequest(vehicleNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> apiResponseHandler(ApiResponse.loading(), ServiceType.GETVEHICLENUMBER))
                .subscribe(result -> apiResponseHandler(ApiResponse.success(result), ServiceType.GETVEHICLENUMBER),
                        error -> apiResponseHandler(ApiResponse.error(error), ServiceType.GETVEHICLENUMBER)));
    }

    public void updateVehicleDetails(String vehicleNumber, String customer_name, String mobile_number, String email, String oil_changed, String odometer_reading, String dateOfOil, String tentativeKm, String tentativeDate) {
        compositeDisposable.add(requestHandler.saveDetailsRequest(vehicleNumber, customer_name, mobile_number, email, oil_changed, odometer_reading, dateOfOil, tentativeKm, tentativeDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> apiResponseHandler(ApiResponse.loading(), ServiceType.SAVEDETAILS))
                .subscribe(result -> apiResponseHandler(ApiResponse.success(result), ServiceType.SAVEDETAILS),
                        error -> apiResponseHandler(ApiResponse.error(error), ServiceType.SAVEDETAILS)));
    }

    public void sendSMS(String user, String pass, String sender, String phone, String text, String priority, String stype) {
        compositeDisposable.add(requestHandler.fetchSendSMSRequest(user, pass, sender, phone, text, priority, stype)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> apiResponseHandler(ApiResponse.loading(), ServiceType.SENDSMS))
                .subscribe(result -> apiResponseHandler(ApiResponse.success(result), ServiceType.SENDSMS),
                        error -> apiResponseHandler(ApiResponse.error(error), ServiceType.SENDSMS)));
    }


    private enum ServiceType {
        GETLUBE, GETVEHICLENUMBER, SAVEDETAILS, SENDSMS
    }

    private void showListPopupScreen(View view, final TextView updateNameTextView, final String selectionItem, final List<String> list) {
        View customView = LayoutInflater.from(this).inflate(R.layout.spinner_popup_screen, null);
        int leftRightSpace = (int) (screenWidth * 0.0153);
        int topBottomSpace = (int) (screenHeight * 0.0089);
        final PopupWindow popupWindow;
        if (selectionItem.equalsIgnoreCase("Oil"))
            popupWindow = new PopupWindow(customView, leftRightSpace * 40, topBottomSpace * 30);
        else
            popupWindow = new PopupWindow(customView, leftRightSpace * 49, ViewGroup.LayoutParams.WRAP_CONTENT);

        final ListView listView = customView.findViewById(R.id.listItemListView);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.shipping_dropdown_textview, list) {

            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getView(position, null, parent);
                TextView tv = (TextView) v;
                if (selectionItem.equalsIgnoreCase("Oil")) {
                    if (position == mSelectedItemOil) {
                        v.setBackgroundColor(getResources().getColor(R.color.background_color));
                        tv.setTextColor(getResources().getColor(R.color.typed_text_color));
                    } else {
                        v.setBackgroundColor(Color.WHITE);
                        tv.setTextColor(getResources().getColor(R.color.typed_text_color));
                    }
                    if (oilSelFirstTime) {
                        v.setBackgroundColor(Color.WHITE);
                        tv.setTextColor(getResources().getColor(R.color.typed_text_color));
                    }
                }

                return v;
            }
        };
        listView.setAdapter(dataAdapter);
        if (selectionItem.equalsIgnoreCase("Oil"))
            listView.setSelection(mSelectedItemOil);

        final List<String> fState;
        fState = list;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                String selectedState = list.get(position);
                int selectedPosition = fState.indexOf(selectedState);
                // Here is your corresponding country code
                updateNameTextView.setText(selectedState);
                updateNameTextView.setTextColor(getResources().getColor(R.color.typed_text_color));
                if (selectionItem.equalsIgnoreCase("Oil")) {
                    mSelectedItemOil = position;
                    oilSelFirstTime = false;
                }
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(view);
    }


    private void apiResponseHandler(ApiResponse apiResponse, ServiceType serviceType) {
        switch (apiResponse.status) {
            case LOADING:
                Utils.GetInstance().showProgressDialog(this);
                break;
            case SUCCESS:
                Utils.GetInstance().dismissDialog();
                if (apiResponse.isValidResponse()) {
                    if (ServiceType.GETLUBE == serviceType) {
                        GetLubeListReponse lubeListReponse = new Gson().fromJson(apiResponse.data, GetLubeListReponse.class);
                        if (lubeListReponse.getDataLube().size() > 0) {
                            arralist = lubeListReponse.getDataLube();
                            for (int i = 0; i < arralist.size(); i++) {
                                oilList.add(arralist.get(i).getLube_Name());
                            }
                        } else {
                            CustomDialogModal.newInstance(HomeActivity.this, getResources().getString(R.string.error_head_oops), getResources().getString(R.string.error_no_record_found),
                                    CustomDialogModal.DialogType.ALERT, screenWidth, screenHeight, new DialogClickListner() {
                                        @Override
                                        public void onClick(int clickedId, Dialog dialog, String data) {

                                        }
                                    });
                        }

                    } else if (ServiceType.GETVEHICLENUMBER == serviceType) {
                        GetCustomerResponse getCustomerResponse = new Gson().fromJson(apiResponse.data, GetCustomerResponse.class);
                        if (getCustomerResponse.getMessage().equalsIgnoreCase("Customer record found")) {
                            if (getCustomerResponse.getCustomer() != null) {
                                etFirstName.setText(getCustomerResponse.getCustomer().getName());
                                etEmail.setText(getCustomerResponse.getCustomer().getEmail());
                                etMobile.setText(getCustomerResponse.getCustomer().getMobile());
                            } else {
                                if (getCustomerResponse.getHistoryCustomerItemList().size() > 0) {
                                    etFirstName.setText(getCustomerResponse.getHistoryCustomerItemList().get(getCustomerResponse.getHistoryCustomerItemList().size() - 1).getHistoryCustomerItem().getName());
                                    etEmail.setText(getCustomerResponse.getHistoryCustomerItemList().get(getCustomerResponse.getHistoryCustomerItemList().size() - 1).getHistoryCustomerItem().getEmail());
                                    etMobile.setText(getCustomerResponse.getHistoryCustomerItemList().get(getCustomerResponse.getHistoryCustomerItemList().size() - 1).getHistoryCustomerItem().getMobile());
                                }
                            }
                        } else {
                            CustomDialogModal.newInstance(HomeActivity.this, getResources().getString(R.string.error_head_oops), getCustomerResponse.getMessage(),
                                    CustomDialogModal.DialogType.ALERT, screenWidth, screenHeight, new DialogClickListner() {
                                        @Override
                                        public void onClick(int clickedId, Dialog dialog, String data) {

                                        }
                                    });
                        }


                    } else if (ServiceType.SAVEDETAILS == serviceType) {
                        /*if (isSaveDate) {
                            SaveCustomerItem saveCustomerItem = pxDatabase.getFirstIdentity();
                            if (saveCustomerItem != null) {
                                pxDatabase.deleteAllIdentities();
                            }
                            fetchLube();
                        } else {*/

                            UpdateEntryResponse updateEntryResponse = new Gson().fromJson(apiResponse.data, UpdateEntryResponse.class);
                            if (updateEntryResponse.getMessage().equalsIgnoreCase("Successful")) {
                            /*String text = "Dear Customer, Thanks for changing " + tvPick.getText().toString() + " for " + etVehicleNo.getText().toString().trim() + " Rathinam Agencies. Your next oil change " + tentativeKm + " kms or on " + tentativedate + ". Contact " + getResources().getString(R.string.contact_number);
                            BaseUrlHolder.setBaseUrl(WebserviceUrls.BASE_URL1);
                            sendSMS("rathinamtrans", "R@th!n@m1234", "RATNAM", etMobile.getText().toString().trim(), text, "Priority", "smstype");*/
                                clearValue();
                                Intent intent = new Intent(this, SuccessScreenActivity.class);
                                startActivity(intent);
                            } else {
                                CustomDialogModal.newInstance(HomeActivity.this, getResources().getString(R.string.error_head_oops), updateEntryResponse.getMessage(),
                                        CustomDialogModal.DialogType.ALERT, screenWidth, screenHeight, new DialogClickListner() {
                                            @Override
                                            public void onClick(int clickedId, Dialog dialog, String data) {

                                            }
                                        });
                            }
                        }

                        } else if (ServiceType.SENDSMS == serviceType) {
                            Intent intent = new Intent(this, SuccessScreenActivity.class);
                            startActivity(intent);
                       /*}*/

                } else {

                }
                break;
            case ERROR:
                Utils.GetInstance().dismissDialog();
                break;
        }
    }

    private void isTabletDevice(Context activityContext) {

        int imgIconWidth = (int) (screenWidth * 1.270);
        int imgIconHeight = (int) (screenWidth * 1.270);

        boolean device_large = ((activityContext.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE);
        DisplayMetrics metrics = new DisplayMetrics();
        Activity activity = (Activity) activityContext;
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        if (device_large) {
            //Tablet
            if (metrics.densityDpi == DisplayMetrics.DENSITY_DEFAULT) {

                tvTitle.setTextSize(28);
                RelativeLayout.LayoutParams btnSetProfileParams = (RelativeLayout.LayoutParams) rlCustomerDetails.getLayoutParams();
                btnSetProfileParams.setMargins(topBottomSpace*6, topBottomSpace*3, topBottomSpace*6, 0);
                rlCustomerDetails.setLayoutParams(btnSetProfileParams);

                RelativeLayout.LayoutParams rlVehicleNoParams = (RelativeLayout.LayoutParams) rlVehicleNo.getLayoutParams();
                rlVehicleNoParams.setMargins(topBottomSpace*6, topBottomSpace*10, topBottomSpace*6, 0);
                rlVehicleNo.setLayoutParams(rlVehicleNoParams);

                RelativeLayout.LayoutParams rlVehicleDetailsParams = (RelativeLayout.LayoutParams) rlVehicleDetails.getLayoutParams();
                rlVehicleDetailsParams.setMargins(topBottomSpace*6, topBottomSpace*3, topBottomSpace*6, 0);
                rlVehicleDetails.setLayoutParams(rlVehicleDetailsParams);

                rlCustomerDetails.setPadding(0,topBottomSpace*2,0,topBottomSpace*2);
                rlVehicleNo.setPadding(0,topBottomSpace*2,0,topBottomSpace*2);
                rlVehicleDetails.setPadding(0,topBottomSpace*2,0,topBottomSpace*2);


            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM) {
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_TV) {
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_HIGH) {
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_280) {
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH) {
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_400) {
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_XXHIGH) {
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_560) {
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_XXXHIGH) {
            }
        } else {
            //Mobile
        }
    }

    @Onclick(R.id.PickRelativeLayout)
    public void PickRelativeLayout(View v) {
        showListPopupScreen(PickRelativeLayout, tvPick, "Oil", oilList);
    }

    @Onclick(R.id.imgReload)
    public void imgReload(View v) {
        clearValue();
    }

    @Onclick(R.id.imgLogout)
    public void imgLogout(View v) {
        Utils.saveBoolToPrefs(getApplicationContext(), "isLogin", false);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void clearValue() {
        etVehicleNo.getText().clear();
        etFirstName.getText().clear();
//        etLastName.getText().clear();
        etMobile.getText().clear();
        etEmail.getText().clear();
        etOdoMerter.getText().clear();
        etDate.getText().clear();
        tvPick.setText("");
        tvVehicle.setVisibility(View.GONE);
        tvFirstName.setVisibility(View.GONE);
        tvMobileNumber.setVisibility(View.GONE);
    }

    @Onclick(R.id.imgSave)
    public void imgSave(View v) {

        Date date1 = null, date2 = null;
        if (checkFieldRequired()) {

            String vehicleNo = etVehicleNo.getText().toString().trim();
            String customerName = etFirstName.getText().toString().trim();
            String mobileNo = etMobile.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pickOil = tvPick.getText().toString().trim();
            String odoMeter = etOdoMerter.getText().toString().trim();
            String dateOfOil = etDate.getText().toString().trim();
            for (int i = 0; i < arralist.size(); i++) {
                if (arralist.get(i).getLube_Name().equalsIgnoreCase(pickOil)) {
                    tentativeKm = String.valueOf(Integer.parseInt(odoMeter) + Integer.parseInt(arralist.get(i).getExpiration_KMs()));
                    tentativedate = getTentativeDate(arralist.get(i).getExpiration_Days());
                }
            }
            if (Utils.isNetworkConnected(HomeActivity.this)) {
                isSaveDate = false;
                updateVehicleDetails(vehicleNo, customerName, mobileNo, email, pickOil, odoMeter, dateOfOil, tentativeKm, tentativedate);
            } else {
/*
                pxDatabase.insertIdentity(vehicleNo, customerName, mobileNo, email, pickOil, odoMeter, dateOfOil, tentativeKm, tentativedate);
*/
                CustomDialogModal.newInstance(HomeActivity.this, getResources().getString(R.string.error_head_oops), getResources().getString(R.string.error_no_internet),
                        CustomDialogModal.DialogType.ALERT, screenWidth, screenHeight, new DialogClickListner() {
                            @Override
                            public void onClick(int clickedId, Dialog dialog, String data) {

                            }
                        });
            }
        }
    }

    public String getTentativeDate(String days) {
        String dt = etDate.getText().toString().trim();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, Integer.parseInt(days));  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String output = sdf1.format(c.getTime());
        return output;
    }


    private boolean checkFieldRequired() {
        boolean isNoEmptySpaces = false;
        String[] editTextLabels = {"etVehicleNo", "etFirstName", "etMobile"};
        String[] textViewLabels = {"tvVehicle", "tvFirstName", "tvMobileNumber"};
        String[] errorText = {getResources().getString(R.string.vehicle_no_required), getResources().getString(R.string.first_name_required), getResources().getString(R.string.mobile_num_required)};
        try {
            isNoEmptySpaces = inputFieldRequiredValidation(HomeActivity.this, editTextLabels,
                    textViewLabels, errorText);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", "Mis configured" + e);
        }
        return isNoEmptySpaces;
    }


    public static boolean inputFieldRequiredValidation(Activity activity, String[] editTextLabels,
                                                       String[] textViewLabels, String[] errorText) throws Exception {
        boolean isNoEmptySpaces = true;
        try {
            for (int i = 0; i < editTextLabels.length; i++) {
                int editTextnameID = activity.getResources().getIdentifier(editTextLabels[i], "id",
                        activity.getPackageName());
                EditText fieldView = (EditText) activity.findViewById(editTextnameID);
                if (fieldView.getText().toString().trim().length() == 0) {
                    isNoEmptySpaces = false;
                    int textViewnameID = activity.getResources().getIdentifier(textViewLabels[i], "id",
                            activity.getPackageName());
                    TextView tvErrorFieldView = (TextView) activity.findViewById(textViewnameID);
                    tvErrorFieldView.setVisibility(View.VISIBLE);
                    tvErrorFieldView.setText(errorText[i]);
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return isNoEmptySpaces;
    }

}
