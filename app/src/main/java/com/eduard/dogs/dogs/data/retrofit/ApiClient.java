package com.eduard.dogs.dogs.data.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.eduard.dogs.dogs.presentation.constants.DogsConstants.BASE_URL;

public class ApiClient {

    private static ApiClient mInstance;
    private Retrofit mRetrofit;

    @Inject
    public ApiClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiClient getInstance() {
        if (mInstance == null) {
            mInstance = new ApiClient();
        }
        return mInstance;
    }

    public BreedsService getBreedsService() {
        return mRetrofit.create(BreedsService.class);
    }
}
