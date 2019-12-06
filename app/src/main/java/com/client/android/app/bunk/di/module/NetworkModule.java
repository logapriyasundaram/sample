package com.client.android.app.bunk.di.module;


import com.client.android.app.bunk.di.utils.Utility;
import com.client.android.app.bunk.net.ApiCallInterface;
import com.client.android.app.bunk.net.ApiCallInterfaceUrl1;
import com.client.android.app.bunk.net.BaseUrlHolder;
import com.client.android.app.bunk.net.RequestHandler;
import com.client.android.app.bunk.net.WebserviceUrls;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkModule {

    private static final NetworkModule networkModule = new NetworkModule();

    public static NetworkModule GetInstance() {
        return networkModule;
    }

    @Provides
    @Singleton
    protected Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    protected Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient,BaseUrlHolder baseUrlHolder) {

        return new Retrofit.Builder()
                .baseUrl(baseUrlHolder.getBaseUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    protected Retrofit provideRetrofitBaseUrl1(Gson gson, OkHttpClient okHttpClient,BaseUrlHolder baseUrlHolder) {

        return new Retrofit.Builder()
                .baseUrl(WebserviceUrls.BASE_URL1)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Singleton
    @Provides
    BaseUrlHolder provideBaseUrlHolder() {
        return new BaseUrlHolder();
    }

    @Provides
    @Singleton
    protected OkHttpClient getRequestHeader() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .build();
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("Content-Type", "application/json");
            return chain.proceed(requestBuilder.build());
        })
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);

        return httpClient.build();
    }

    @Provides
    public ApiCallInterface getApiCallInterface() {
        OkHttpClient okHttpClient = getRequestHeader();
        Retrofit retrofit = provideRetrofit(provideGson(), okHttpClient,provideBaseUrlHolder());
        return retrofit.create(ApiCallInterface.class);
    }

    @Provides
    public ApiCallInterfaceUrl1 getUrl1ApiCallInterface() {
        OkHttpClient okHttpClient = getRequestHeader();
        Retrofit retrofit = provideRetrofitBaseUrl1(provideGson(), okHttpClient,provideBaseUrlHolder());
        return retrofit.create(ApiCallInterfaceUrl1.class);
    }

    @Provides
    protected RequestHandler provideRequestHandler(ApiCallInterface apiCallInterface, ApiCallInterfaceUrl1 apiCallInterfaceUrl1) {
        return new RequestHandler(apiCallInterface, apiCallInterfaceUrl1);
    }

    @Provides
    @Singleton
    protected Utility provideUtility() {
        return new Utility();
    }
}
