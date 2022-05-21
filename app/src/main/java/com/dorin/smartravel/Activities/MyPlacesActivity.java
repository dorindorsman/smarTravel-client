package com.dorin.smartravel.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dorin.smartravel.Adapters.PlacesAdapter;
import com.dorin.smartravel.CallBacks.CallBackListPlaces;
import com.dorin.smartravel.R;
import com.dorin.smartravel.Util;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPlacesActivity extends AppCompatActivity {


    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;
    private CallBackListPlaces callBackListPlaces;


    //private EditText myPlaces_Address;
    private AutocompleteSupportFragment autocompleteFragment;
    private RecyclerView myPlaces_RecyclerView;
    private PlacesAdapter placesAdapter;
    private List<Place> placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);


        // Initialize the SDK
        Places.initialize(getApplicationContext(), "AIzaSyC1ceioN4zpyYHA2Tp1DshESpJob8ife84");


        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);


        findViews();
        initPlaceFragment();
    }

    private void findViews() {
       // myPlaces_Address = findViewById(R.id.myPlaces_Address);
        myPlaces_RecyclerView = findViewById(R.id.myPlaces_RecyclerView);
        placesList = new ArrayList<>();
        placesAdapter = new PlacesAdapter(this, placesList,callBackListPlaces);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        myPlaces_RecyclerView.setLayoutManager(mLayoutManager);
        myPlaces_RecyclerView.addItemDecoration(new Util(1, Util.dpToPx(10,getResources()), true));
        myPlaces_RecyclerView.setItemAnimator(new DefaultItemAnimator());
        myPlaces_RecyclerView.setAdapter(placesAdapter);
    }

    private void initPlaceFragment() {

        autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.myPlaces_autocomplete_fragment);




        List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,Place.Field.LAT_LNG,Place.Field.NAME);
        List<Place.Field> fieldList2 = Arrays.asList(Place.Field.NAME);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(fieldList2);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("TAG", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}