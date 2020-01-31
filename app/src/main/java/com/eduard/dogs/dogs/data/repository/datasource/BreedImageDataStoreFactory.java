package com.eduard.dogs.dogs.data.repository.datasource;


import javax.inject.Inject;

public class BreedImageDataStoreFactory {

    @Inject
    BreedImageDataStore remoteDataStore;

    @Inject
    BreedImageDataStoreFactory() {
    }

    public BreedImageDataStore create() {
        BreedImageDataStore breedImageDataStore;
        breedImageDataStore = createRemoteDataStore();

        return breedImageDataStore;
    }

    public BreedImageDataStore createRemoteDataStore() {
        return remoteDataStore;
    }
}
