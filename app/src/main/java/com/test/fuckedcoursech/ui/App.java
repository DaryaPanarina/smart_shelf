package com.test.fuckedcoursech.ui;

import android.app.Application;

import com.test.fuckedcoursech.di.AppComponent;
import com.test.fuckedcoursech.di.DaggerAppComponent;

public class App extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }
}
