package com.eduard.dogs.dogs.presentation.presenter;

import com.eduard.dogs.dogs.domain.model.BreedImgEntity;
import com.eduard.dogs.dogs.domain.usecase.GetBreedImgUseCase;
import com.eduard.dogs.dogs.domain.usecase.GetSubBreedImgUseCase;
import com.eduard.dogs.dogs.presentation.base.BasePresenter;
import com.eduard.dogs.dogs.presentation.mapper.DogsImageListMapper;

import com.eduard.dogs.dogs.presentation.presenter.contracts.DogsDetailContract;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class DogsDetailPresenter extends BasePresenter<DogsDetailContract.View> implements DogsDetailContract.Presenter {

    GetBreedImgUseCase breedImgUseCase;
    GetSubBreedImgUseCase subBreedImgUseCase;

    DogsImageListMapper dogsImageListMapper;

    @Inject
    public DogsDetailPresenter(GetBreedImgUseCase breedImgUseCase, GetSubBreedImgUseCase subBreedImgUseCase,DogsImageListMapper dogsImageListMapper) {
        this.breedImgUseCase = breedImgUseCase;
        this.subBreedImgUseCase = subBreedImgUseCase;
        this.dogsImageListMapper = dogsImageListMapper;
    }

    @Override
    public void getImageDetail(String name, String subname) {

        if(subname.isEmpty()){

            getNameDogs(name);

        }else {

            getSubnameDogs(name,subname);

        }
    }

    private void getNameDogs(String name) {
        breedImgUseCase.execute(new BreedImageObserver() , GetBreedImgUseCase.Params.forBreedImages(name));
    }


    private void getSubnameDogs(String name, String subname) {
        subBreedImgUseCase.execute(new SubBreedImageObserver() , GetSubBreedImgUseCase.Params.forBreedSubImages(name,subname));
    }


    private final class BreedImageObserver extends DisposableObserver<BreedImgEntity> {

        @Override
        public void onNext(BreedImgEntity breedImages) {
            getView().hideLoading();
            getView().setBreedDetail(dogsImageListMapper.mapEntityToView(breedImages));
        }

        @Override public void onComplete() {
            getView().hideLoading();
        }

        @Override public void onError(Throwable e) {
            getView().hideLoading();
            getView().showError();
        }
    }

    private final class SubBreedImageObserver extends DisposableObserver<BreedImgEntity> {

        @Override
        public void onNext(BreedImgEntity breedImages) {
            getView().hideLoading();
            getView().setBreedDetail(dogsImageListMapper.mapEntityToView(breedImages));
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
