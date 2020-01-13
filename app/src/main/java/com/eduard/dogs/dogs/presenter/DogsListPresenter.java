package com.eduard.dogs.dogs.presenter;

import com.eduard.dogs.dogs.base.BasePresenter;
import com.eduard.dogs.dogs.presenter.contracts.DogsListContract;
import com.eduard.dogs.dogs.retrofit.ApiClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eduard.dogs.dogs.constants.DogsConstants.BREED_NAME;

public class DogsListPresenter extends BasePresenter<DogsListContract.View> implements DogsListContract.Presenter {

   @Inject
   ApiClient apiClient;

    @Override
    public void getDogsList() {
        getView().showLoading();                        //start loading
        apiClient.getInstance()
                .getBreedsService()
                .fetchBreedsList()
                .enqueue(new Callback<JsonObject>() {

                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        List<String> listHeaderData = new ArrayList<>();
                        HashMap<String, List<String>> listChildData = new HashMap<>();


                        JsonObject body = response.body();
                        JsonObject breedObj = body.get(BREED_NAME).getAsJsonObject();
                        Set<String> breedStrList;

                        if (body != null) {

                            breedStrList = breedObj.keySet();
                            Iterator<String> iterators = breedStrList.iterator();

                            while (iterators.hasNext()) {
                                listHeaderData.add(iterators.next());
                            }

                            ArrayList<String> subBreedS;

                            for (int i = 0; i < listHeaderData.size(); i++) {

                                subBreedS = new ArrayList<>(breedObj.get(listHeaderData.get(i)).getAsJsonArray().size());
                                if (breedObj.get(listHeaderData.get(i)).getAsJsonArray().size() != 0) {
                                    for (int j = 0; j < breedObj.get(listHeaderData.get(i)).getAsJsonArray().size(); j++) {
                                        subBreedS.add(breedObj.get(listHeaderData.get(i)).getAsJsonArray().get(j).getAsString());
                                    }
                                }
                                listChildData.put(listHeaderData.get(i), subBreedS);
                            }
                            getView().hideLoading();
                            getView().setBreedList(listHeaderData, listChildData);

                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        getView().hideLoading();
                        getView().showError();
                    }
                });
    }
}
