package com.eduard.dogs.dogs.presentation.mapper;

import com.eduard.dogs.dogs.domain.model.BreedImgEntity;
import com.eduard.dogs.dogs.presentation.model.BreedImage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DogsImageListMapper {

    @Inject
    public DogsImageListMapper() {
    }

    public List<BreedImage> mapEntityToView(BreedImgEntity entity) {

        List<BreedImage> list = new ArrayList<>();

        for (String name : entity.getBreedImage()) {
            BreedImage breedImage = new BreedImage();
            breedImage.setBreedImage(name);
            list.add(breedImage);
        }

        return list;
    }
}

//por qué no hay necesidad de agregar "subname"???