package com.eduard.dogs.dogs.di;


import com.eduard.dogs.dogs.presenter.DogsDetailPresenter;
import com.eduard.dogs.dogs.presenter.DogsListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModul {

    @Provides
    public DogsListPresenter provideListPresenter(){
        return new DogsListPresenter();
    }

    @Provides
    public DogsDetailPresenter provideDetailPresenter(){
        return new DogsDetailPresenter();
    }
}
