package com.dorin.smartravel.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dorin.smartravel.Adapters.PlacesAdapter;
import com.dorin.smartravel.CallBackListPlaces;
import com.dorin.smartravel.R;
import com.dorin.smartravel.Util;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPlacesFragment extends Fragment {

    private AppCompatActivity activity;
    private CallBackListPlaces callBackListPlaces;


    private AutocompleteSupportFragment autocompleteFragment;
    private RecyclerView Favorites_RecyclerView_favPlaces;
    private PlacesAdapter placesAdapter;
    private List<Place> placesList;


    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public MyPlacesFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the SDK
        Places.initialize(getActivity(), "AIzaSyC1ceioN4zpyYHA2Tp1DshESpJob8ife84");


        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_my_places, container, false);
        findViews(view);
        initPlaceFragment();
        preparePlaces();



        return view;
    }

    private void initPlaceFragment() {

       autocompleteFragment = (AutocompleteSupportFragment)
                getParentFragmentManager().findFragmentById(R.id.MyPlaces_autocomplete_fragment);


        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                Log.d("TAG", "Place: " + place.getName() + ", " + place.getId());
            }


            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });


    }

    private void preparePlaces() {




        placesAdapter.notifyDataSetChanged();

    }


    private void findViews(View view) {
        Favorites_RecyclerView_favPlaces = view.findViewById(R.id.MyPlaces_RecyclerView_Places);
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




//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                Place place = Autocomplete.getPlaceFromIntent(data);
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
//            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
//                // TODO: Handle the error.
//                Status status = Autocomplete.getStatusFromIntent(data);
//                Log.i(TAG, status.getStatusMessage());
//            } else if (resultCode == RESULT_CANCELED) {
//                // The user canceled the operation.
//            }
//            return;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

}
