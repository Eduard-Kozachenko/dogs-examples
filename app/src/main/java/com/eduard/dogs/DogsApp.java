package com.eduard.dogs;

import android.app.Application;

public class DogsApp extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);

    }

    public static AppComponent getComponent() {
        return component;
    }
}
