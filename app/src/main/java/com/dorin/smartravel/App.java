package com.dorin.smartravel;

import android.app.Application;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataManger.initHelper();





    }


}
