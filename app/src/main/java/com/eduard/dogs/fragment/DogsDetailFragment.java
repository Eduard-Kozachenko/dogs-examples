package com.eduard.dogs.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eduard.dogs.R;
import com.eduard.dogs.activity.MainActivity;
import com.eduard.dogs.adapter.DogsImgAdapter;
import com.eduard.dogs.base.BaseFragment;
import com.eduard.dogs.presenter.DogsDetailPresenter;
import com.eduard.dogs.presenter.contracts.DogsDetailContract;

import java.util.List;

import static com.eduard.dogs.constants.DogsConstants.BREED;
import static com.eduard.dogs.constants.DogsConstants.SUBBREED;

public class DogsDetailFragment extends BaseFragment implements DogsDetailContract.View {

    private String breedName = "";
    private String subbreedName = "";
    private DogsImgAdapter adapter;
    private RecyclerView recyclerView;
    private DogsDetailPresenter presenter = new DogsDetailPresenter();
    private DialogFragment loadingFragment = LoadingDialogFragment.getInstance();

    @Override
    public void onPreparePresenter() {
        attachPresenter(presenter, this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dogs_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_container_img);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.getImageDetail(this.breedName, this.subbreedName);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String breedName = getArguments().getString(BREED, "");
        String subbreedName = getArguments().getString(SUBBREED, "");
        ActionBar toolbar = ((MainActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle(breedName + " " + subbreedName);
        toolbar.setDisplayHomeAsUpEnabled(true);

        if (breedName != null && !breedName.isEmpty()) {
            this.breedName = breedName;
        }
        if (subbreedName != null && !subbreedName.isEmpty()) {
            this.subbreedName = subbreedName;
        }
    }

    public static DogsDetailFragment newInstance(String breed, String subbreed) {
        DogsDetailFragment breeedDetailFragment = new DogsDetailFragment();
        Bundle args = new Bundle();

        args.putString(BREED, breed);
        args.putString(SUBBREED, subbreed);

        breeedDetailFragment.setArguments(args);
        return breeedDetailFragment;
    }

    @Override
    public void setBreedDetail(List<String> imgList) {

        adapter = new DogsImgAdapter(getActivity());
        adapter.setImgList(imgList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        if (!loadingFragment.isVisible()) {
            loadingFragment.show(getActivity().getSupportFragmentManager(), "LOADING");
        }
    }

    @Override
    public void hideLoading() {
        if (loadingFragment.isVisible()) {
            loadingFragment.dismiss();
        }
    }

    @Override
    public void showError() {

    }

}
