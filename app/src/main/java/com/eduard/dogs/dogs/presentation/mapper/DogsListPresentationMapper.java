package com.eduard.dogs.dogs.presentation.mapper;

import com.eduard.dogs.dogs.domain.model.DogsListModel;
import com.eduard.dogs.dogs.presentation.model.DogsListView;

import javax.inject.Inject;

public class DogsListPresentationMapper {

    @Inject
    public DogsListPresentationMapper() {
    }

    public DogsListView mapEntityToPres(DogsListModel dogsListModels) {

        DogsListView dogsListView = new DogsListView(dogsListModels.getDogListModel());
        dogsListView.setDogListView(dogsListModels.getDogListModel());

        return dogsListView;

    }

}

