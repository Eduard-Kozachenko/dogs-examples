package com.eduard.dogs.dogs.presentation.presenter;

import com.eduard.dogs.dogs.data.model.DogsListEntity;
import com.eduard.dogs.dogs.domain.usecase.GetBreedsListUseCase;
import com.eduard.dogs.dogs.presentation.base.BasePresenter;
import com.eduard.dogs.dogs.data.mapper.DogsBreedListMapper;
import com.eduard.dogs.dogs.presentation.mapper.DogsListPresentationMapper;
import com.eduard.dogs.dogs.presentation.presenter.contracts.DogsListContract;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class DogsListPresenter extends BasePresenter<DogsListContract.View> implements DogsListContract.Presenter {

    GetBreedsListUseCase getBreedsListUseCase;
    DogsBreedListMapper dogsBreedListMapper;
    DogsListPresentationMapper dogsListPresentationMapper;

    @Inject
    public DogsListPresenter(GetBreedsListUseCase getBreedsListUseCase, DogsBreedListMapper dogsBreedListMapper , DogsListPresentationMapper dogsListPresentationMapper;) {
        this.getBreedsListUseCase = getBreedsListUseCase;
        this.dogsBreedListMapper = dogsBreedListMapper;
        this.dogsListPresentationMapper = dogsListPresentationMapper;
    }

    @Override
    public void getDogsList() {
        getBreedsListUseCase.execute(new BreedListObserver(), null);
    }

    private final class BreedListObserver extends DisposableObserver<DogsListEntity> {

        @Override
        public void onNext(DogsListEntity entity) {
            getView().hideLoading();
            getView().setBreedList(dogsListPresentationMapper.mapEntityToPres(entity)); // TODO Map to view
        }

        @Override public void onComplete() {
            getView().hideLoading();
        }

        @Override public void onError(Throwable e) {
            getView().hideLoading();
            getView().showError();
        }
    }
}
