package com.eduard.dogs.retrofit;

import com.eduard.dogs.model.DogsImageList;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.eduard.dogs.constants.DogsConstants.BREED_LIST_URL;


public interface BreedsService {

    @GET(BREED_LIST_URL)
    Call<JsonObject> fetchBreedsList();

    @GET("api/breed/{breed}/images/random/3")
    Call<DogsImageList> fetchDogImages(@Path("breed") String breed);

    @GET("api/breed/{breed}/{subbreed}/images/random/3")
    Call<DogsImageList> fetchSubDogImages(@Path("breed") String breed,@Path("subbreed") String subbreed);

}
