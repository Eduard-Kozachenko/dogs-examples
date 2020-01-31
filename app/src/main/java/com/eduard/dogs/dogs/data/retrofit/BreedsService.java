package com.eduard.dogs.dogs.data.retrofit;

import com.eduard.dogs.dogs.data.model.DogsListEntity;
import com.eduard.dogs.dogs.domain.model.BreedImgEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.eduard.dogs.dogs.presentation.constants.DogsConstants.BREED;
import static com.eduard.dogs.dogs.presentation.constants.DogsConstants.BREED_LIST_URL;
import static com.eduard.dogs.dogs.presentation.constants.DogsConstants.SUBBREED;

public interface BreedsService {

    @GET(BREED_LIST_URL)
    Observable<DogsListEntity> fetchBreedsList();

    @GET("api/breed/{breed}/images/random/5")
    Observable<BreedImgEntity> fetchDogImages(@Path(BREED) String breed);

    @GET("api/breed/{breed}/{subbreed}/images/random/5")
    Observable<BreedImgEntity> fetchSubDogImages(@Path(BREED) String breed, @Path(SUBBREED) String subbreed);
}
