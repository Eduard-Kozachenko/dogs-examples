package com.eduard.dogs.dogs.di;

import com.eduard.dogs.dogs.retrofit.ApiClient;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModul {
        @Provides
        public ApiClient provideApiClient(){
            return new ApiClient();
        }
}
