package com.eduard.dogs.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.eduard.dogs.constants.DogsConstants.BASE_URL;

public class ApiClient {

    private static ApiClient mInstance;
    private Retrofit mRetrofit;

    private ApiClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
