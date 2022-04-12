package com.dorin.smartravel.Fragments;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dorin.smartravel.R;

public class DayTripFragment extends Fragment {

    private AppCompatActivity activity;

    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public DayTripFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
