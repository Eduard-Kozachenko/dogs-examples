package com.eduard.dogs.dogs.di;


import android.content.Context;

import com.eduard.dogs.dogs.domain.usecase.GetBreedImgUseCase;
import com.eduard.dogs.dogs.domain.usecase.GetBreedsListUseCase;
import com.eduard.dogs.dogs.domain.usecase.GetSubBreedImgUseCase;
import com.eduard.dogs.dogs.presentation.adapter.DogsImgAdapter;
import com.eduard.dogs.dogs.presentation.adapter.ExpandableListViewAdapter;
import com.eduard.dogs.dogs.presentation.constants.AlertErrorMessage;

import com.eduard.dogs.dogs.data.mapper.DogsBreedListMapper;
import com.eduard.dogs.dogs.presentation.mapper.DogsImageListMapper;
import com.eduard.dogs.dogs.presentation.presenter.DogsDetailPresenter;
import com.eduard.dogs.dogs.presentation.presenter.DogsListPresenter;


import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    public DogsListPresenter provideListPresenter(GetBreedsListUseCase getBreedsListUseCase, DogsBreedListMapper dogsBreedListMapper) {
        return new DogsListPresenter(getBreedsListUseCase,dogsBreedListMapper);
    }

    @Provides
    public DogsDetailPresenter provideDetailPresenter(GetBreedImgUseCase getBreedImgUseCase,
                                                      GetSubBreedImgUseCase getSubBreedImgUseCase,DogsImageListMapper dogsImageListMapper) {
        return new DogsDetailPresenter(getBreedImgUseCase,getSubBreedImgUseCase,dogsImageListMapper);
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

