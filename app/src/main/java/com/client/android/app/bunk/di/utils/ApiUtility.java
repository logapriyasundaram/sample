package com.client.android.app.bunk.di.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.client.android.app.bunk.base.BaseApplication;
import com.client.android.app.bunk.net.Constant;

public class ApiUtility {
    private static final ApiUtility ourInstance = new ApiUtility();

    public static ApiUtility getInstance() {
        return ourInstance;
    }

    private ApiUtility() {
    }

    public String getAccessTokenMetaData() {
        try {
            ApplicationInfo appInfo = BaseApplication.getBaseApplication().getPackageManager()
                    .getApplicationInfo(BaseApplication.getBaseApplication().getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null)
                    return String.valueOf(appInfo.metaData.getString(BaseApplication.getBaseApplication().getPackageName() + Constant.ACCESS_TOKE_KEY));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
