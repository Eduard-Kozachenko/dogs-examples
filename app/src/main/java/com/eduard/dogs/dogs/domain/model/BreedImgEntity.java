package com.eduard.dogs.dogs.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BreedImgEntity {

    @SerializedName("message")
    private List<String> imageList;

    public List<String> getBreedImage() {
        return imageList;
    }
    public void setBreedsList(List<String> imageList) {
        this.imageList = imageList;
    }
}
