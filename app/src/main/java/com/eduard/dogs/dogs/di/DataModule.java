package com.eduard.dogs.dogs.di;

import com.eduard.dogs.dogs.data.executor.JobExecutor;
import com.eduard.dogs.dogs.data.repository.BreedImageDataRepository;
import com.eduard.dogs.dogs.data.repository.DogsBreedListDataRepository;
import com.eduard.dogs.dogs.data.repository.datasource.BreedImageDataStore;
import com.eduard.dogs.dogs.data.repository.datasource.RemoteBreedImageDataStore;
import com.eduard.dogs.dogs.data.repository.datasource.breedList.DogsBreedListDataStore;
import com.eduard.dogs.dogs.data.repository.datasource.breedList.RemoteDogsBreedListDataStore;
import com.eduard.dogs.dogs.data.retrofit.ApiClient;
import com.eduard.dogs.dogs.domain.executor.PostExecutionThread;
import com.eduard.dogs.dogs.domain.executor.ThreadExecutor;
import com.eduard.dogs.dogs.domain.repository.BreedImageRepository;
import com.eduard.dogs.dogs.domain.repository.DogsBreedListRepository;
import com.eduard.dogs.dogs.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    BreedImageRepository provideUserRepository(BreedImageDataRepository breedImageDataRepository) {
        return breedImageDataRepository;
    }

    @Provides
    BreedImageDataStore providesBreedsImgDataStore(ApiClient apiClient) {
        return new RemoteBreedImageDataStore(apiClient);
    }

    @Provides
    DogsBreedListRepository provideDogsBreedListRepository(DogsBreedListDataRepository dogsBreedListDataRepository) {
        return dogsBreedListDataRepository;
    }

    @Provides
    DogsBreedListDataStore providesDogsBreedListDataStore(ApiClient apiClient) {
        return new RemoteDogsBreedListDataStore(apiClient);
    }



}
