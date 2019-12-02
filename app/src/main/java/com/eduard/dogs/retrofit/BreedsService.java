package com.eduard.dogs.retrofit;

import com.eduard.dogs.model.DogsImageList;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import static com.eduard.dogs.constants.DogsConstants.BREED;
import static com.eduard.dogs.constants.DogsConstants.BREED_LIST_URL;
import static com.eduard.dogs.constants.DogsConstants.SUBBREED;

public interface BreedsService {

    @GET(BREED_LIST_URL)
    Call<JsonObject> fetchBreedsList();

    @GET("api/breed/{breed}/images/random/5")
    Call<DogsImageList> fetchDogImages(@Path(BREED) String breed);

    @GET("api/breed/{breed}/{subbreed}/images/random/5")
    Call<DogsImageList> fetchSubDogImages(@Path(BREED) String breed,@Path(SUBBREED) String subbreed);
}
