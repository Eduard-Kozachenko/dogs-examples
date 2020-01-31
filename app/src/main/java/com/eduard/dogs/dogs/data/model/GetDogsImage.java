package com.eduard.dogs.dogs.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

@Deprecated
public class GetDogsImage {

    @SerializedName("message")
    private List<String> imageList;

    public GetDogsImage(List<String> images) {
    }

    public List<String> getBreedImage() {
        return imageList;
    }
    public void setBreedsList(List<String> imageList) {
        this.imageList = imageList;
    }
}
