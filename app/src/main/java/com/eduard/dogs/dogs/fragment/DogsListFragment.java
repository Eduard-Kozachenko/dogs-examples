package com.eduard.dogs.dogs.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.eduard.dogs.DogsApp;
import com.eduard.dogs.R;
import com.eduard.dogs.dogs.activity.MainActivity;
import com.eduard.dogs.dogs.adapter.ExpandableListViewAdapter;
import com.eduard.dogs.dogs.base.BaseFragment;
import com.eduard.dogs.dogs.di.DaggerDogsComponent;
import com.eduard.dogs.dogs.di.PresenterModul;
import com.eduard.dogs.dogs.presenter.DogsListPresenter;
import com.eduard.dogs.dogs.presenter.contracts.DogsListContract;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class DogsListFragment extends BaseFragment implements DogsListContract.View {

    @Inject
    DogsListPresenter presenter;

    public static final String TAG = DogsListFragment.class.getSimpleName();
    private ExpandableListAdapter expListAdapter;
    private ExpandableListView expListView;
    private DialogFragment loadingFragment = LoadingDialogFragment.getInstance();

    @Override
    public void onPreparePresenter() {
        attachPresenter(presenter, this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar toolbar = ((MainActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("Dogs List");
        toolbar.setDisplayHomeAsUpEnabled(false);

        return inflater.inflate(R.layout.fragment_dogs_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expListView = view.findViewById(R.id.expListDogs);
        presenter.getDogsList();
    }

    @Override
    protected void onInjection() {
        DaggerDogsComponent.builder()
                .appComponent(DogsApp.getComponent())
                .presenterModul(new PresenterModul())
                .build()
                .inject(this);
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

    @Override
    public void setBreedList(final List<String> listDataHeader, final HashMap<String, List<String>> listChildData) {

        //LayoutAnimationController controller;
        //controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_slide_from_right);

        expListView = expListView.findViewById(R.id.expListDogs);
        expListAdapter = new ExpandableListViewAdapter(getActivity(), listDataHeader, listChildData);
        expListView.setAdapter(expListAdapter);
        //expListView.setLayoutAnimation(controller);
        expListView.scheduleLayoutAnimation();
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                if (listChildData.get(listDataHeader.get(groupPosition)).size() == 0) {
                    try {

                        ((MainActivity) getActivity()).replaceFragment(listDataHeader.get(groupPosition), "");

                    } catch (Exception e) {
                        //TODO show error message
                    }
                }
                return false;
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                try {

                    ((MainActivity) getActivity()).replaceFragment(listDataHeader.get(groupPosition),
                            listChildData.get(listDataHeader.get(groupPosition)).get(childPosition));

                } catch (Exception e) {
                    //TODO show error message
                }
                return false;
            }
        });
    }
}
