package com.client.android.app.bunk.di.module;


import com.client.android.app.bunk.ui.Success.SuccessScreenActivity;
import com.client.android.app.bunk.ui.home.HomeActivity;
import com.client.android.app.bunk.ui.login.LoginActivity;
import com.client.android.app.bunk.ui.splash.SplashScreenActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract SplashScreenActivity bindSplashScreenActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract HomeActivity bindHomeActivity();

    @ContributesAndroidInjector
    abstract SuccessScreenActivity bindSuccessScreenActivity();

}
