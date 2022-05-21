package com.dorin.smartravel.Fragments;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dorin.smartravel.CallBacks.CallBackListPlaces;
import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.Objects.DayTrip;
import com.dorin.smartravel.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.model.Place;

import java.util.List;

public class DayTripFragment extends Fragment implements OnMapReadyCallback {

    private AppCompatActivity activity;
    private PlacesListFragment placesListFragment;
    private GoogleMap mMap;

    private DayTrip currentDayTrip;



    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public DayTripFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDayTrip = DataManger.getInstance().getCurrentDayTrip();

        findViews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_daytrip, container, false);

        findViews();


        return view;
    }

    CallBackListPlaces callBackListPlaces = new CallBackListPlaces() {
        @Override
        public void rowSelected(double longitude, double latitude) {
            LatLng point = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions()
                    .position(point)
                    .title("* Crash Site * | Pilot Name: " ));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 13.0f));
        }
    };

    private void findViews() {


        placesListFragment = new PlacesListFragment();
        placesListFragment.setActivity(this.activity);
        placesListFragment.setCallBackListPlaces(callBackListPlaces);
        getParentFragmentManager().beginTransaction().add(R.id.framePlaces, placesListFragment).commit();


        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        getParentFragmentManager().beginTransaction().add(R.id.frameMap,supportMapFragment).commit();
        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        List<Place> placeList=placesListFragment.getPlacesList();
        for (Place place:placeList) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(place.getLatLng().latitude, place.getLatLng().longitude))
                    .title(place.getName()));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeList.get(0).getLatLng(),14.0f));
    }
}
