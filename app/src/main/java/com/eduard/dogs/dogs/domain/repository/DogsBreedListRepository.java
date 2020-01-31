package com.eduard.dogs.dogs.domain.repository;

import com.eduard.dogs.dogs.data.model.DogsListEntity;

import io.reactivex.Observable;

public interface DogsBreedListRepository {

    Observable<DogsListEntity> getDogsBreedList();

}
