package com.dorin.smartravel.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dorin.smartravel.Adapters.TripAdapter;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.R;
import com.dorin.smartravel.Util;

import java.util.ArrayList;
import java.util.List;

public class UpcomingFragment extends Fragment {

    private AppCompatActivity activity;

    private RecyclerView Upcoming_RecyclerView_Trips;
    private TripAdapter tripAdapter;
    private List<Trip> tripsList;

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


        View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_upcomings, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {
        Upcoming_RecyclerView_Trips = view.findViewById(R.id.Upcoming_RecyclerView_Trips);
        tripsList = new ArrayList<>();
        tripAdapter = new TripAdapter(this.activity, tripsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.activity, 2);
        Upcoming_RecyclerView_Trips.setLayoutManager(mLayoutManager);
        Upcoming_RecyclerView_Trips.addItemDecoration(new Util(2, Util.dpToPx(10,getResources()), true));
        Upcoming_RecyclerView_Trips.setItemAnimator(new DefaultItemAnimator());
        Upcoming_RecyclerView_Trips.setAdapter(tripAdapter);

        prepareTrips();

        try {
            Glide.with(this).load(R.drawable.ic_logo).into((ImageView)view.findViewById(R.id.thumbnail));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void prepareTrips() {
        int[] coversTrips = new int[]{
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                R.drawable.ic_logo};

        Trip a = new Trip("New York", 13, coversTrips[0],"10/5","23/5");
        tripsList.add(a);

        a = new Trip("Los Angeles", 8, coversTrips[1],"10/3","18/3");
        tripsList.add(a);

        a = new Trip("Chicago", 11, coversTrips[2],"1/4","12/4");
        tripsList.add(a);

        a = new Trip("Miami", 12, coversTrips[3],"12/12","24/12");
        tripsList.add(a);

        a = new Trip("Tokyo", 14, coversTrips[4],"2/10","16/10");
        tripsList.add(a);

        a = new Trip("Beijing", 1, coversTrips[5],"6/6","");
        tripsList.add(a);

        a = new Trip("Manila", 11, coversTrips[6],"1/4","12/4");
        tripsList.add(a);

        a = new Trip("Bangkok", 14, coversTrips[7],"2/10","16/10");
        tripsList.add(a);

        a = new Trip("Mexico City", 11, coversTrips[8],"1/4","12/4");
        tripsList.add(a);

        a = new Trip("Istanbul", 17, coversTrips[9],"7/7","24/7");
        tripsList.add(a);

        tripAdapter.notifyDataSetChanged();
    }





}
