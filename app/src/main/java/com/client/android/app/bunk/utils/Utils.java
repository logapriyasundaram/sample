package com.client.android.app.bunk.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.widget.Toast;

import com.client.android.app.bunk.R;
import com.client.android.app.bunk.base.BaseApplication;

import java.io.File;

public class Utils {

    public static final String SOCIAL_ACCOUNT_ACTION = "social_account_item";
    private static final String DRAWABLE = "drawable";
    private static final Object LOCK = new Object();
    private static Utils utils;
    private ProgressDialog progressDialog;

    public static Utils GetInstance() {
        synchronized (LOCK) {
            if (null == utils) {
                utils = new Utils();
            }
        }
        return utils;
    }

    private Utils() {

    }

    private String retrieveIconNameFromPath(String path) {
        String[] filePath = path.split(File.separator);
        int length = filePath.length;
        int nameLength = 0;
        String[] nameArray = new String[0];
        String fullName = (length > 1 ? filePath[length - 1] : null);
        if (fullName != null) {
            nameArray = !TextUtils.isEmpty(fullName) ? fullName.split("\\.") : new String[]{};
            nameLength = nameArray.length;
        }
        return (nameLength > 1 ? nameArray[0] : "");
    }

    public  static boolean isNetworkConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static void saveBoolToPrefs(Context context, String key,
                                       Boolean value) {

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolToPrefs(Context context, String key) {
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharedPrefs.getBoolean(key, false);

    }

    public void showProgressDialog(@UiThread Context context) {
        progressDialog = ProgressDialog.show(context, null, context.getString(R.string.loading), false);
    }

    @UiThread
    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * This method to disply toast message based in UI context.
     * @param context
     * @param msg
     */
    @UiThread
    public void showToastMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method to show the toast message based on the app context
     * @param msg string
     */
    public void showToastMsg(String msg) {
        Toast.makeText(BaseApplication.getBaseApplication(), msg, Toast.LENGTH_SHORT).show();
    }

    public String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
