package com.client.android.app.bunk.di.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.client.android.app.bunk.net.RequestHandler;
import com.client.android.app.bunk.ui.login.LoginScreenViewModel;


import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private RequestHandler requestHandler;

    @Inject
    public ViewModelFactory(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginScreenViewModel.class)) {
            return (T) new LoginScreenViewModel(requestHandler);
        }
        return null;
    }
}
