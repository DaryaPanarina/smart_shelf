package com.test.course.ui;

import android.app.Application;

import com.test.course.di.AppComponent;
//TODO: ОТКУДА ЭТОТ КЛАСС? import com.test.course.di.DaggerAppComponent;

public class App extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }
}
