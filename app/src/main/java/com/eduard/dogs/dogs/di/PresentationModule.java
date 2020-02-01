package com.eduard.dogs.dogs.di;


import android.content.Context;

import com.eduard.dogs.dogs.adapter.DogsImgAdapter;
import com.eduard.dogs.dogs.adapter.ExpandableListViewAdapter;
import com.eduard.dogs.dogs.model.AlertErrorMessage;
import com.eduard.dogs.dogs.presenter.DogsDetailPresenter;
import com.eduard.dogs.dogs.presenter.DogsListPresenter;
import com.eduard.dogs.dogs.retrofit.ApiClient;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    public DogsListPresenter provideListPresenter() {
        return new DogsListPresenter();
    }

    @Provides
    public DogsDetailPresenter provideDetailPresenter() {
        return new DogsDetailPresenter();
    }

    @Provides
    public ExpandableListViewAdapter provideListViewAdapter(Context context) {
        return new ExpandableListViewAdapter(context);
    }

    @Provides
    public DogsImgAdapter provideImgAdapter(Context context) {
        return new DogsImgAdapter(context);
    }

    @Provides
    public AlertErrorMessage provideErrorDialogFragment() {
        return new AlertErrorMessage();
    }
}

