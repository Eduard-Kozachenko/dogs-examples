package com.eduard.dogs.presenter;

import com.eduard.dogs.base.BasePresenter;
import com.eduard.dogs.model.DogsImageList;
import com.eduard.dogs.presenter.contracts.DogsDetailContract;
import com.eduard.dogs.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogsDetailPresenter extends BasePresenter<DogsDetailContract.View> implements DogsDetailContract.Presenter {

    @Override
    public void getImageDetail(String name, String subname) {

        if(subname.isEmpty()){
            getView().showLoading();
            ApiClient.getInstance()
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
        }else {
            getView().showLoading();
            ApiClient.getInstance()
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
}
