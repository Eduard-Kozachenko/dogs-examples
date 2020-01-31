package com.eduard.dogs.dogs.data.model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DogsListEntity {

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public Map<String, List<String>> message;

    public Map<String, ArrayList<String>> getMessage() {
        return message;
    }

}
