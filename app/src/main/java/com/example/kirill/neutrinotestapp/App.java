package com.example.kirill.neutrinotestapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by iiopok on 10.04.2015.
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getContext(){
        return App.context;
    }
}
