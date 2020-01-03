package com.eduard.dogs.dogs.di;


import com.eduard.dogs.ActivityScope;
import com.eduard.dogs.AppComponent;
import com.eduard.dogs.dogs.fragment.DogsDetailFragment;
import com.eduard.dogs.dogs.fragment.DogsListFragment;

import dagger.Component;

@ActivityScope
@Component(modules = {PresenterModul.class, ApiModul.class}, dependencies = AppComponent.class)
public interface DogsComponent {

    void inject(DogsListFragment fragment);

    void inject(DogsDetailFragment fragment);

}