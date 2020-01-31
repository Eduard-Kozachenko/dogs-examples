package com.eduard.dogs.dogs.presentation.presenter.contracts;

import com.eduard.dogs.dogs.presentation.base.BasePresenter;

import java.util.List;
import java.util.Map;

public interface DogsListContract {

    interface View extends BasePresenter.View {

        void setBreedList(Map<String, List<String>> dogsList);

    }

    interface Presenter {

        void getDogsList();
    }
}
