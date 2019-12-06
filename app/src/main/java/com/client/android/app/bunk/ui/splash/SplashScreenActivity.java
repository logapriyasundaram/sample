package com.client.android.app.bunk.ui.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.client.android.app.bunk.R;
import com.client.android.app.bunk.base.BaseActivity;
import com.client.android.app.bunk.net.BaseUrlHolder;
import com.client.android.app.bunk.net.WebserviceUrls;
import com.client.android.app.bunk.ui.home.HomeActivity;
import com.client.android.app.bunk.ui.login.LoginActivity;
import com.client.android.app.bunk.utils.Utils;

@SuppressLint("Registered")
public class SplashScreenActivity extends BaseActivity {

    private static final int MINUTE = 1000;
    private static final int INTERVAL_TIME = 1 * MINUTE;


    @Override
    protected int layoutRes() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseUrlHolder.setBaseUrl(WebserviceUrls.BASE_URL);
        createHandler();
    }

    private void createHandler() {
        Handler handler = new Handler();
        handler.postDelayed(()->{

            if (Utils.getBoolToPrefs(getApplicationContext(),"isLogin")){
                Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, INTERVAL_TIME);
    }
}
