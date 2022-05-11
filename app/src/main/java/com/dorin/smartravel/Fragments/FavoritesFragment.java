package com.dorin.smartravel.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dorin.smartravel.Adapters.PlacesAdapter;
import com.dorin.smartravel.CallBackListPlaces;
import com.dorin.smartravel.R;
import com.dorin.smartravel.Util;
import com.google.android.libraries.places.api.model.Place;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private AppCompatActivity activity;
    private CallBackListPlaces callBackListPlaces;


    private RecyclerView Favorites_RecyclerView_favPlaces;
    private PlacesAdapter placesAdapter;
    private List<Place> placesList;


    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public FavoritesFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_favorites, container, false);
        findViews(view);
        preparePlaces();



        return view;
    }

    private void preparePlaces() {




        placesAdapter.notifyDataSetChanged();

    }


    private void findViews(View view) {
        Favorites_RecyclerView_favPlaces = view.findViewById(R.id.Favorites_RecyclerView_favPlaces);
        placesList = new ArrayList<>();
        placesAdapter = new PlacesAdapter(this.activity, placesList,callBackListPlaces);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.activity, 1);
        Favorites_RecyclerView_favPlaces.setLayoutManager(mLayoutManager);
        Favorites_RecyclerView_favPlaces.addItemDecoration(new Util(1, Util.dpToPx(10,getResources()), true));
        Favorites_RecyclerView_favPlaces.setItemAnimator(new DefaultItemAnimator());
        Favorites_RecyclerView_favPlaces.setAdapter(placesAdapter);
    }


    public void setCallBackListPlaces (CallBackListPlaces callBackListPlaces){
        this.callBackListPlaces=callBackListPlaces;
    }

    public CallBackListPlaces getCallBackListPlaces() {
        return callBackListPlaces;
    }

    public List<Place> getPlacesList() {
        return placesList;
    }

}
