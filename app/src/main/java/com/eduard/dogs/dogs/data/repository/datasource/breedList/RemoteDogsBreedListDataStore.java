package com.eduard.dogs.dogs.data.repository.datasource.breedList;

import com.eduard.dogs.dogs.data.model.DogsListEntity;
import com.eduard.dogs.dogs.data.retrofit.ApiClient;

import io.reactivex.Observable;

public class RemoteDogsBreedListDataStore implements DogsBreedListDataStore {

    private final ApiClient apiClient;

    public RemoteDogsBreedListDataStore(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Observable<DogsListEntity> getDogsBreedList() {
        return this.apiClient.getBreedsService().fetchBreedsList();
    }
}
