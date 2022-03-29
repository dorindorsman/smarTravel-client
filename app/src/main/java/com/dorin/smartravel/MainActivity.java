package com.dorin.smartravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();


    }

    private void findViews() {

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
    }
}