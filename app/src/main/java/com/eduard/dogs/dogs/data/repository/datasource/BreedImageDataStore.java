package com.eduard.dogs.dogs.data.repository.datasource;

import com.eduard.dogs.dogs.domain.model.BreedImgEntity;

import io.reactivex.Observable;

public interface BreedImageDataStore {

    Observable<BreedImgEntity> getBreedImage(final String breed);

    Observable<BreedImgEntity> getBreedSubImage(final String breed , final String subbreed);
}
