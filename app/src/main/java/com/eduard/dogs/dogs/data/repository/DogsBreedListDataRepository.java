package com.eduard.dogs.dogs.data.repository;

import com.eduard.dogs.dogs.data.mapper.DogsBreedListMapper;
import com.eduard.dogs.dogs.data.model.DogsListEntity;
import com.eduard.dogs.dogs.data.repository.datasource.breedList.DogsBreedListDataStore;
import com.eduard.dogs.dogs.data.repository.datasource.breedList.DogsBreedListDataStoreFactory;
import com.eduard.dogs.dogs.domain.repository.DogsBreedListRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DogsBreedListDataRepository implements DogsBreedListRepository {

    private final DogsBreedListDataStoreFactory dogsBreedListDataStoreFactory;
    private final DogsBreedListMapper dogsBreedListMapper;

    @Inject
    public DogsBreedListDataRepository(DogsBreedListDataStoreFactory dogsBreedListDataStoreFactory,DogsBreedListMapper dogsBreedListMapper) {
        this.dogsBreedListDataStoreFactory = dogsBreedListDataStoreFactory;
        this.dogsBreedListMapper = dogsBreedListMapper;
    }

    @Override
    public Observable<DogsListEntity> getDogsBreedList() {
        final DogsBreedListDataStore dogsBreedListDataStore = this.dogsBreedListDataStoreFactory.create();
        return dogsBreedListDataStore.getDogsBreedList().map();
    }

}
