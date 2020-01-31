package com.eduard.dogs.dogs.data.mapper;

import com.eduard.dogs.dogs.data.model.DogsListEntity;
import com.eduard.dogs.dogs.domain.model.DogsListModel;

import javax.inject.Inject;

public class DogsBreedListMapper {

    @Inject
    public DogsBreedListMapper() {
    }

    public DogsListModel mapEntityToDomain(DogsListEntity dogsListEntity) {

            DogsListModel dogsListModels = new DogsListModel(dogsListEntity.getMessage());
            dogsListModels.setDogListModel(dogsListEntity.getMessage());

        return dogsListModels;

    }
}
