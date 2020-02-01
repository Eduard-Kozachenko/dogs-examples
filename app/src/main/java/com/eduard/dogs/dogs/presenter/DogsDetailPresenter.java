package com.eduard.dogs.dogs.presenter;

import com.eduard.dogs.dogs.base.BasePresenter;
import com.eduard.dogs.dogs.model.DogsImageList;
import com.eduard.dogs.dogs.retrofit.ApiClient;
import com.eduard.dogs.dogs.presenter.contracts.DogsDetailContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogsDetailPresenter extends BasePresenter<DogsDetailContract.View> implements DogsDetailContract.Presenter {

    @Inject
    ApiClient apiClient;

    @Override
    public void getImageDetail(String name, String subname) {

        if(subname.isEmpty()){

            getNameDogs(name);

        }else {

            getSubnameDogs(name,subname);

        }
    }

    public void getNameDogs(final String name){
        getView().showLoading();
        apiClient.getInstance()
                .getBreedsService()
                .fetchDogImages(name)
                .enqueue(new Callback<DogsImageList>() {

                    @Override
                    public void onResponse(Call<DogsImageList> call, Response<DogsImageList> response) {

                        getView().hideLoading();
                        if (response.body() != null) {
                            getView().setBreedDetail(response.body().getBreedImage());
                        } else {
                            //TODO show error message
                        }
                    }

                    @Override
                    public void onFailure(Call<DogsImageList> call, Throwable t) {
                        getView().hideLoading();
                        getView().showError();
                    }
                });
    }
    public void getSubnameDogs(final String name, final String subname){
        getView().showLoading();
        apiClient.getInstance()
                .getBreedsService()
                .fetchSubDogImages(name,subname)
                .enqueue(new Callback<DogsImageList>() {

                    @Override
                    public void onResponse(Call<DogsImageList> call, Response<DogsImageList> response) {

                        getView().hideLoading();
                        if (response.body() != null) {
                            getView().setBreedDetail(response.body().getBreedImage());
                        } else {
                            //TODO show error message
                        }
                    }
                    @Override
                    public void onFailure(Call<DogsImageList> call, Throwable t) {
                        getView().hideLoading();
                        getView().showError();
                    }
                });
    }
}
