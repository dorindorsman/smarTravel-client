package com.dorin.smartravel.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.dorin.smartravel.Adapters.PlacesAdapter;
import com.dorin.smartravel.CallBacks.CallBackListPlaces;
import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.Objects.DayTrip;
import com.dorin.smartravel.Objects.Place;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.R;
import com.dorin.smartravel.Util;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;

public class MyPlacesActivity extends AppCompatActivity {


    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;
    private CallBackListPlaces callBackListPlaces= new CallBackListPlaces() {
        @Override
        public void rowSelected(double longitude, double latitude) {

        }
    };
    private MaterialToolbar MyPlaces_toolBar;
    private RecyclerView myPlaces_RecyclerView;
    private PlacesAdapter placesAdapter;
    private List<Place> placesList;
    private DataManger dataManger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);

        dataManger = DataManger.getInstance();
        findViews();
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

    private void findViews() {
        MyPlaces_toolBar = findViewById(R.id.MyPlaces_toolBar);
        MyPlaces_toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        myPlaces_RecyclerView = findViewById(R.id.myPlaces_RecyclerView);
        placesList = new ArrayList<>();
        initPlaces();
        placesAdapter = new PlacesAdapter(this, placesList,callBackListPlaces);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        myPlaces_RecyclerView.setLayoutManager(mLayoutManager);
        myPlaces_RecyclerView.addItemDecoration(new Util(1, Util.dpToPx(10,getResources()), true));
        myPlaces_RecyclerView.setItemAnimator(new DefaultItemAnimator());
        myPlaces_RecyclerView.setAdapter(placesAdapter);
    }



}