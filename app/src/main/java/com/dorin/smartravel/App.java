package com.dorin.smartravel;

import android.app.Application;

import com.dorin.smartravel.Helpers.DataManger;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataManger.initHelper();





    }


}
