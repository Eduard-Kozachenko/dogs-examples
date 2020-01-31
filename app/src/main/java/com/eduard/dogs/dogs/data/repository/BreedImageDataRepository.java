package com.eduard.dogs.dogs.data.repository;

import com.eduard.dogs.dogs.data.repository.datasource.BreedImageDataStore;
import com.eduard.dogs.dogs.data.repository.datasource.BreedImageDataStoreFactory;
import com.eduard.dogs.dogs.domain.model.BreedImgEntity;
import com.eduard.dogs.dogs.domain.repository.BreedImageRepository;


import javax.inject.Inject;

import io.reactivex.Observable;

public class BreedImageDataRepository implements BreedImageRepository {

    private final BreedImageDataStoreFactory breedImageDataStoreFactory;

    @Inject
    BreedImageDataRepository(BreedImageDataStoreFactory breedImageDataStoreFactory) {
        this.breedImageDataStoreFactory = breedImageDataStoreFactory;
    }

    @Override
    public Observable<BreedImgEntity> getBreedImages(String breed) {
        final BreedImageDataStore breedImageDataStore = this.breedImageDataStoreFactory.create();
        return breedImageDataStore.getBreedImage(breed);
    }

    @Override
    public Observable<BreedImgEntity> getBreedSubImages(String breed, String subbreed) {
        final BreedImageDataStore breedSubImageDataStore = this.breedImageDataStoreFactory.create();
        return breedSubImageDataStore.getBreedSubImage(breed,subbreed);
    }
}
