package com.dorin.smartravel;

import android.util.Log;

import com.dorin.smartravel.Objects.Trip;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetchDataFromUrl extends Thread {

    public String data = "";
    public static final String URL_DATA = "https://pastebin.com/raw/gxEhWeew";
    public  Trip[] trips;

    @Override
    public void run(){
        try {
            URL url = new URL(URL_DATA);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line = bufferedReader.readLine()) != null){
                data = data + line;
            }
            if (!data.isEmpty()){
                Gson g = new Gson();
                trips = g.fromJson(data,Trip[].class);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
