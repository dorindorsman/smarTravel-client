package com.dorin.smartravel.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dorin.smartravel.R;

public class UpcomingFragment extends Fragment {

    private AppCompatActivity activity;

    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public UpcomingFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_upcomings, container, false);
    }
}
