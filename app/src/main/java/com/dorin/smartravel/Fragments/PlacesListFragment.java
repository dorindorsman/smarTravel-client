package com.dorin.smartravel.Fragments;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dorin.smartravel.Adapters.PlacesAdapter;
import com.dorin.smartravel.CallBackListPlaces;
import com.dorin.smartravel.R;
import com.dorin.smartravel.Util;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;


import java.util.ArrayList;
import java.util.List;


public class PlacesListFragment extends Fragment {

    private AppCompatActivity activity;
    private CallBackListPlaces callBackListPlaces;

    private RecyclerView placesList_RecyclerView;
    private PlacesAdapter placesAdapter;
    private List<Place> placesList;

    public PlacesListFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_places_list, container, false);
        findViews(view);
        preparePlaces();


        return view;
    }

    private void preparePlaces() {


        Place a = Place.builder().setName("Los Angeles").setAddress("1").setLatLng(new LatLng(10,10)).build();
        placesList.add(a);

        a = Place.builder().setName("Chicago").setAddress("2").setLatLng(new LatLng(11,11)).build();
        placesList.add(a);

        a = Place.builder().setName("Miami").setAddress("3").setLatLng(new LatLng(12,12)).build();
        placesList.add(a);

        a = Place.builder().setName("Tokyo").setAddress("4").setLatLng(new LatLng(13,13)).build();
        placesList.add(a);

        a = Place.builder().setName("Beijing").setAddress("5").setLatLng(new LatLng(14,14)).build();
        placesList.add(a);


        placesAdapter.notifyDataSetChanged();
    }

    private void findViews(View view) {
        placesList_RecyclerView = view.findViewById(R.id.placesList_RecyclerView);
        placesList = new ArrayList<>();
        placesAdapter = new PlacesAdapter(this.activity, placesList,callBackListPlaces);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.activity, 1);
        placesList_RecyclerView.setLayoutManager(mLayoutManager);
        placesList_RecyclerView.addItemDecoration(new Util(1, Util.dpToPx(10,getResources()), true));
        placesList_RecyclerView.setItemAnimator(new DefaultItemAnimator());
        placesList_RecyclerView.setAdapter(placesAdapter);
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity=activity;
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