package com.client.android.app.bunk.ui.Success;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.client.android.app.bunk.R;
import com.client.android.app.bunk.base.BaseActivity;

public class SuccessScreenActivity  extends Activity {
    LinearLayout linearLayout;
    int width, height;

    TextView tvsuccess, tvthanks;
    public String fromScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_screen);
        calculateScreenSize();
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        linearLayout = findViewById(R.id.llmainlayout);
        tvsuccess = findViewById(R.id.tvsuccess);
        tvthanks = findViewById(R.id.tvthanks);
        ImageView imgCorrect = findViewById(R.id.imgcorrect);
        @SuppressLint("CutPasteId") LinearLayout linearLayoutmain = findViewById(R.id.llmainlayout);
        linearLayoutmain.getBackground().setAlpha(180);

        int imgReadWidth = (int) ( width* 0.1550);


        int topBottomSpace = (int) ( width* 0.080);


        LinearLayout.LayoutParams imgLayParams = (LinearLayout.LayoutParams) imgCorrect.getLayoutParams();
        imgLayParams.width = imgReadWidth;
        imgLayParams.height = imgReadWidth;
        imgLayParams.setMargins(0, 0, 0, topBottomSpace * 2);
        imgCorrect.setLayoutParams(imgLayParams);

        LinearLayout.LayoutParams tvsuccessll = (LinearLayout.LayoutParams) tvsuccess.getLayoutParams();
        tvsuccess.setLayoutParams(tvsuccessll);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dismissLayout();

    }

    private void calculateScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        assert windowmanager != null;
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

    }
    private void dismissLayout() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }

}

