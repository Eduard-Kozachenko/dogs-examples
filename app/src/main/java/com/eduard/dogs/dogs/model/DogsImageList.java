package com.eduard.dogs.dogs.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DogsImageList {

    @SerializedName("message")
    private List<String> imageList;

    public DogsImageList(List<String> images) {
    }

    public List<String> getBreedImage() {
        return imageList;
    }
    public void setBreedsList(List<String> imageList) {
        this.imageList = imageList;
    }
}
