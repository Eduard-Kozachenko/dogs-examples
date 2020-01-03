package com.eduard.dogs;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Singleton  @Component (modules = {AppModule.class} )public
interface AppComponent {

    Application getApplication();

    Context getContext();

    void inject(DogsApp application);

}


