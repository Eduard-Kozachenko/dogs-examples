package com.eduard.dogs.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduard.dogs.R;
import com.eduard.dogs.adapter.DogsImgAdapter;
import com.eduard.dogs.model.DogsImageList;
import com.eduard.dogs.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogsDetailFragment extends Fragment {

    public static String TAG = DogsDetailFragment.class.getSimpleName();
    String breedName = "";
    String subbreedName = "";
    private DogsImgAdapter adapter;
    RecyclerView recyclerView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dogs_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_container_img);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DogsImgAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        getImageList(this.breedName,this.subbreedName);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String breedName = getArguments().getString("breed", "");

        String subbreedName = getArguments().getString("subbreed" , "");

        if (breedName != null && !breedName.isEmpty()) {
           this.breedName = breedName;
        }

        if (subbreedName != null && !subbreedName.isEmpty()) {
            this.subbreedName = subbreedName;
        }
    }

    private void getImageList(String name , String subname) {

        if(subname == ""){

        ApiClient.getInstance()
                .getBreedsService()
                .fetchDogImages(name)
                .enqueue(new Callback<DogsImageList>() {

                    @Override
                    public void onResponse(Call<DogsImageList> call, Response<DogsImageList> response) {

                        if (response.body() != null) {
                            adapter.setImgList(response.body().getBreedImage());
                            adapter.notifyDataSetChanged();
                        } else {
                            //TODO show error message
                        }

//                        Log.d(TAG, "onResponse ");
                    }

                    @Override
                    public void onFailure(Call<DogsImageList> call, Throwable t) {
                        Log.d(TAG, "onFailure ");
                    }
                });
        }else {

            ApiClient.getInstance()
                    .getBreedsService()
                    .fetchSubDogImages(name,subname)
                    .enqueue(new Callback<DogsImageList>() {

                        @Override
                        public void onResponse(Call<DogsImageList> call, Response<DogsImageList> response) {

                            if (response.body() != null) {
                                adapter.setImgList(response.body().getBreedImage());
                                adapter.notifyDataSetChanged();
                            } else {
                                //TODO show error message
                            }

//                        Log.d(TAG, "onResponse ");
                        }

                        @Override
                        public void onFailure(Call<DogsImageList> call, Throwable t) {
                            Log.d(TAG, "onFailure ");
                        }
                    });
        }
    }

    public static DogsDetailFragment newInstance(String breed , String subbreed) {
        DogsDetailFragment breeedDetailFragment = new DogsDetailFragment();
        Bundle args = new Bundle();

        args.putString("breed", breed);
        args.putString("subbreed", subbreed);

        breeedDetailFragment.setArguments(args);
        return breeedDetailFragment;
    }


}
