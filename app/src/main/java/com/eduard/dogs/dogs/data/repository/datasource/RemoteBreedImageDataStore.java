package com.eduard.dogs.dogs.data.repository.datasource;

import com.eduard.dogs.dogs.data.retrofit.ApiClient;
import com.eduard.dogs.dogs.domain.model.BreedImgEntity;

import io.reactivex.Observable;

public class RemoteBreedImageDataStore implements BreedImageDataStore {

    private final ApiClient apiClient;

    public RemoteBreedImageDataStore(ApiClient client) {
        this.apiClient = client;
    }

    @Override
    public Observable<BreedImgEntity> getBreedImage(String breed) {
        return this.apiClient.getBreedsService().fetchDogImages(breed);
    }

    @Override
    public Observable<BreedImgEntity> getBreedSubImage(String breed, String subbreed) {
        return this.apiClient.getBreedsService().fetchSubDogImages(breed,subbreed);
    }

}
