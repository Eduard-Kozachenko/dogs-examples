package com.eduard.dogs.presenter.contracts;

import com.eduard.dogs.base.BasePresenter;

import java.util.HashMap;
import java.util.List;

public interface DogsListContract {

    interface View extends BasePresenter.View {

        void setBreedList(List<String> listDataHeader, HashMap<String, List<String>> listChildData);

    }

    interface Presenter {

        void getDogsList();
    }
}
