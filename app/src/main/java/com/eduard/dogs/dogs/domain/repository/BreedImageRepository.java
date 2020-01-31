package com.eduard.dogs.dogs.domain.repository;

import com.eduard.dogs.dogs.domain.model.BreedImgEntity;

import io.reactivex.Observable;

public interface BreedImageRepository {

    Observable<BreedImgEntity> getBreedImages(String breed);

    Observable<BreedImgEntity> getBreedSubImages(String breed, String subbreed);
}
