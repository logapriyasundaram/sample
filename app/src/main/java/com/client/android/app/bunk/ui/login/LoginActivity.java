package com.client.android.app.bunk.ui.login;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.client.android.app.bunk.R;
import com.client.android.app.bunk.base.BaseActivity;
import com.client.android.app.bunk.net.ApiResponse;
import com.client.android.app.bunk.net.RequestHandler;
import com.client.android.app.bunk.ui.home.HomeActivity;
import com.client.android.app.bunk.utils.CustomDialogModal;
import com.client.android.app.bunk.utils.DialogClickListner;
import com.client.android.app.bunk.utils.Utils;
import com.product.annotationbuilder.ProductBindView;
import com.product.process.annotation.Initialize;
import com.product.process.annotation.Onclick;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends BaseActivity implements DialogClickListner {


    private static final String TAG = LoginActivity.class.getName();

    @Initialize(R.id.imgBunk)
    ImageView imgBunk;
    @Initialize(R.id.tvAuthenticate)
    TextView tvAuthenticate;
    @Initialize(R.id.etUserName)
    EditText etUserName;
    @Initialize(R.id.etPassword)
    EditText etPassword;
    @Initialize(R.id.btnSigned)
    Button btnSigned;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    LoginScreenViewModel loginScreenViewModel;
    @Inject
    RequestHandler requestHandler;

    @Override
    protected int layoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProductBindView.bind(this);
        setDynamicValue();
        functionality();
    }

    private void functionality() {

        btnSigned.setEnabled(false);
        btnSigned.setClickable(false);
        btnSigned.setBackgroundResource(R.drawable.round_corner_disable_button);
        btnSigned.setTextColor(getResources().getColor(R.color.background_color));


        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkFieldsForEmptyValues();
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkFieldsForEmptyValues();
            }
        });


    }

    private void setDynamicValue() {
//        int imgIconWidth = (int) (screenWidth * 0.075);
//        int imgIconHeight = (int) (screenWidth * 0.075);
//        int imgArrowWidth = (int) (screenWidth * 0.075);
//        int imgArrowHeight = (int) (screenWidth * 0.075);
        int topBottomSpace = (int) (screenHeight * 0.0089);


        RelativeLayout.LayoutParams rlMainLayParams = (RelativeLayout.LayoutParams) etUserName.getLayoutParams();
        rlMainLayParams.setMargins(topBottomSpace * 4, topBottomSpace * 5, topBottomSpace * 4, 0);
        etUserName.setLayoutParams(rlMainLayParams);

        RelativeLayout.LayoutParams etPasswordParams = (RelativeLayout.LayoutParams) etPassword.getLayoutParams();
        etPasswordParams.setMargins(topBottomSpace * 4, topBottomSpace * 2, topBottomSpace * 4, 0);
        etPassword.setLayoutParams(etPasswordParams);

        RelativeLayout.LayoutParams btnSignedParams = (RelativeLayout.LayoutParams) btnSigned.getLayoutParams();
        btnSignedParams.setMargins(topBottomSpace * 4, topBottomSpace * 3, topBottomSpace * 4, 0);
        btnSigned.setLayoutParams(btnSignedParams);

    }

    @SuppressLint("ResourceType")
    private void checkFieldsForEmptyValues() {
        // TODO Auto-generated method stub
        String s1 = etUserName.getText().toString().trim();
        String s2 = etPassword.getText().toString().trim();
        if (s1.length() < 1 || s2.length() < 1) {
            btnSigned.setEnabled(false);
            btnSigned.setClickable(false);
            btnSigned.setBackgroundResource(R.drawable.round_corner_disable_button);
            btnSigned.setTextColor(getResources().getColor(R.color.background_color));
        } else {
            btnSigned.setEnabled(true);
            btnSigned.setClickable(true);
            btnSigned.setBackgroundResource(R.drawable.round_corner_enable_button);
            btnSigned.setTextColor(getResources().getColor(R.color.typed_text_color));
        }
    }

    @Override
    public void onClick(int ClickedId, Dialog dialog, String data) {

    }

    @Onclick(R.id.btnSigned)
    public void btnSigned(View v) {
        if (Utils.isNetworkConnected(LoginActivity.this)){
            fetchCountryInDataBase();
        }else{
            CustomDialogModal.newInstance(LoginActivity.this, getResources().getString(R.string.error_head_oops), getResources().getString(R.string.error_no_internet),
                    CustomDialogModal.DialogType.ALERT,screenWidth,screenHeight, new DialogClickListner() {
                        @Override
                        public void onClick(int clickedId, Dialog dialog, String data) {

                        }
                    });
        }
//        Intent intent = new Intent(this, HomeActivity.class);
//        startActivity(intent);
    }


    private void fetchCountryInDataBase() {
        compositeDisposable.add(requestHandler.postLoginWithUserRequest(Utils.GetInstance().getDeviceId(this),getUserName(),getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d->apiResponseHandler(ApiResponse.loading()))
                .subscribe(result->apiResponseHandler(ApiResponse.success(result)),
                        error->apiResponseHandler(ApiResponse.error(error))));
    }


    public String getUserName() {
        return etUserName.getText().toString().trim();
    }
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

    private void apiResponseHandler(ApiResponse apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                Utils.GetInstance().showProgressDialog(this);
                break;
            case SUCCESS:
                Utils.GetInstance().dismissDialog();
                if (apiResponse.isValidResponse()) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    CustomDialogModal.newInstance(LoginActivity.this, getResources().getString(R.string.error_text_login), getResources().getString(R.string.error_text_login_des),
                            CustomDialogModal.DialogType.ALERT,screenWidth,screenHeight, new DialogClickListner() {
                                @Override
                                public void onClick(int clickedId, Dialog dialog, String data) {

                                }
                            });
                }
                break;
            case ERROR:
                Utils.GetInstance().dismissDialog();
                CustomDialogModal.newInstance(LoginActivity.this, getResources().getString(R.string.error_text_login), getResources().getString(R.string.error_text_login_des),
                        CustomDialogModal.DialogType.ALERT,screenWidth,screenHeight, new DialogClickListner() {
                            @Override
                            public void onClick(int clickedId, Dialog dialog, String data) {

                            }
                        });
                break;
        }
    }


}
