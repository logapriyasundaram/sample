package com.client.android.app.bunk.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.client.android.app.bunk.net.RequestHandler;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginScreenViewModel extends ViewModel {

    private RequestHandler requestHandler;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<LoginScreenApiResponse> apiResponseMutableLiveData = new MutableLiveData<>();

    @Inject
    public LoginScreenViewModel(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public MutableLiveData<LoginScreenApiResponse> getApiResponseMutableLiveData() {
        return apiResponseMutableLiveData;
    }

    public void postContinueWithMobileRequest(String deviceId, String username, String password) {
        disposables.add(requestHandler.postLoginWithUserRequest(deviceId, username, password)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(d->apiResponseMutableLiveData.setValue(LoginScreenApiResponse.loading()
                        .setServiceType(LoginScreenApiResponse.ServiceType.LOGIN)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> apiResponseMutableLiveData.setValue(LoginScreenApiResponse.success(result)
                                .setServiceType(LoginScreenApiResponse.ServiceType.LOGIN)),
                        error -> apiResponseMutableLiveData.setValue(LoginScreenApiResponse.error(error)
                                .setServiceType(LoginScreenApiResponse.ServiceType.LOGIN))));
    }
}
