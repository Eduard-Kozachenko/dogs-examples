package com.eduard.dogs.dogs.data.repository.datasource.breedList;

import com.eduard.dogs.dogs.data.repository.datasource.BreedImageDataStore;

import javax.inject.Inject;

public class DogsBreedListDataStoreFactory {

    @Inject
    DogsBreedListDataStore remoteListDataStore;

    @Inject
    DogsBreedListDataStoreFactory() {
    }

    public DogsBreedListDataStore create() {
        DogsBreedListDataStore breedListDataStore;
        breedListDataStore = createRemoteListDataStore();

        return breedListDataStore;
    }

    public DogsBreedListDataStore createRemoteListDataStore() {
        return remoteListDataStore;
    }
}


