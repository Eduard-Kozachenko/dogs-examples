package com.eduard.dogs.dogs.presentation.presenter.contracts;


import com.eduard.dogs.dogs.presentation.base.BasePresenter;
import com.eduard.dogs.dogs.presentation.model.BreedImage;

import java.util.List;

public interface DogsDetailContract {

    interface View extends BasePresenter.View{

        void setBreedDetail(List<BreedImage> imgList);
    }

    interface Presenter{

        void getImageDetail(String name , String subname);
    }
}
