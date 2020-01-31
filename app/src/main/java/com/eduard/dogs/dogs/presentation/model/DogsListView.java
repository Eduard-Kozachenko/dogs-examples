
package com.eduard.dogs.dogs.presentation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DogsListView {

    private Map<String, ArrayList<String>> message;

    public DogsListView(Map<String, ArrayList<String>> message) {
        this.message = message;
    }

    public DogsListView() {
    }

    public Map<String, ArrayList<String>> getDogListView() {
        return message;
    }

    public void setDogListView(Map<String, ArrayList<String>> message) {
        this.message = message;
    }
}


