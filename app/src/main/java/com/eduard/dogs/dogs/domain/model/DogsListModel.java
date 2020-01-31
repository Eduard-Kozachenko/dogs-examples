package com.eduard.dogs.dogs.domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DogsListModel {

    private Map<String, ArrayList<String>> message;

    public DogsListModel(Map<String, ArrayList<String>> message) {
        this.message = message;
    }

    public Map<String, ArrayList<String>> getDogListModel() {
        return message;
    }

    public void setDogListModel(Map<String, ArrayList<String>> message) {
        this.message = message;
    }

}




