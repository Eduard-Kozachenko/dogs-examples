package com.eduard.dogs.dogs.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.eduard.dogs.dogs.base.BasePresenter;

public class BaseFragment extends Fragment {

    private BasePresenter basePresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onInjection();
        onPrepareFragment(view);
        onPreparePresenter();
    }

    public void onPreparePresenter() {
    }

    public void onPrepareFragment(View view) {

    }

    protected void onInjection() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePresenter();
    }

    public void attachPresenter(BasePresenter basePresenter, BasePresenter.View view) {
        this.basePresenter = basePresenter;
        this.basePresenter.attachView(view);
    }

    private void releasePresenter() {
        basePresenter.release();
    }
}
