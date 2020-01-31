package com.eduard.dogs.dogs.data.repository.datasource.breedList;

import com.eduard.dogs.dogs.data.model.DogsListEntity;

import io.reactivex.Observable;

public interface DogsBreedListDataStore {

    Observable<DogsListEntity> getDogsBreedList();

}
