package com.dorin.smartravel.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dorin.smartravel.Adapters.PlacesAdapter;
import com.dorin.smartravel.CallBacks.CallBackListPlaces;
import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.Objects.DayTrip;
import com.dorin.smartravel.Objects.Place;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.R;
import com.dorin.smartravel.Util;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPlacesFragment extends Fragment {

    private AppCompatActivity activity;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;
    private CallBackListPlaces callBackListPlaces= new CallBackListPlaces() {
        @Override
        public void rowSelected(double longitude, double latitude) {

        }
    };
  //  private MaterialToolbar MyPlaces_toolBar;
    private RecyclerView myPlaces_RecyclerView;
    private PlacesAdapter placesAdapter;
    private List<Place> placesList;
    private DataManger dataManger;


    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public MyPlacesFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataManger = DataManger.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_my_places, container, false);
        findViews(view);




        return view;
    }

    private void initPlaces() {
        for (Trip t:dataManger.fetchDataFromUrl.trips) {
            for (DayTrip day:t.getDayTripList()) {
                for(Place p : day.getPlacesList()){
                    placesList.add(p);
                    Log.d("roman",p.toString()+"");
                }
            }
        }
    }



    private void preparePlaces() {
        placesAdapter.notifyDataSetChanged();

    }


    private void findViews(View view) {
//        MyPlaces_toolBar = view.findViewById(R.id.MyPlaces_toolBar);
//        MyPlaces_toolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });


        myPlaces_RecyclerView = view.findViewById(R.id.MyPlaces_RecyclerView_Places);
        placesList = new ArrayList<>();
        initPlaces();
        placesAdapter = new PlacesAdapter(this.activity, placesList,callBackListPlaces);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.activity, 1);
        myPlaces_RecyclerView.setLayoutManager(mLayoutManager);
        myPlaces_RecyclerView.addItemDecoration(new Util(1, Util.dpToPx(10,getResources()), true));
        myPlaces_RecyclerView.setItemAnimator(new DefaultItemAnimator());
        myPlaces_RecyclerView.setAdapter(placesAdapter);
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
