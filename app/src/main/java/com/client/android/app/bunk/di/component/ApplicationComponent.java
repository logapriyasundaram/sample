package com.client.android.app.bunk.di.component;

import android.app.Application;

import com.client.android.app.bunk.base.BaseApplication;
import com.client.android.app.bunk.di.module.ActivityBindingModule;
import com.client.android.app.bunk.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Component(modules = {AndroidSupportInjectionModule.class, ActivityBindingModule.class, NetworkModule.class})
@Singleton
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
