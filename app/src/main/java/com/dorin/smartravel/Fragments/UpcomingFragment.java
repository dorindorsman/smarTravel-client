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

import com.dorin.smartravel.Adapters.TripAdapter;
import com.dorin.smartravel.CallBacks.CallBackItemClick;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.R;
import com.dorin.smartravel.Util;
import com.dorin.smartravel.ViewDialogRating;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpcomingFragment extends Fragment {

    private AppCompatActivity activity;

    private RecyclerView Upcoming_RecyclerView_Trips;
    private TripAdapter tripAdapter;
    private List<Trip> tripsList;

    CallBackItemClick callBackItemClick = new CallBackItemClick() {
        @Override
        public void itemClick() {
            getParentFragmentManager().beginTransaction().replace(R.id.main_fragment,DaysPathTripFragment.class,null).commit();
        }
    };



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
        checkTripForRate();

        return view;
    }

    private void checkTripForRate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        if(!tripsList.isEmpty()){
            for (Trip trip:tripsList) {
                if(!trip.getIsRate() && trip.getEndDate().equals(formatter.format(date))){
                    ViewDialogRating dialogRating=new ViewDialogRating();
                    dialogRating.showDialog(activity,trip,trip.getRates(),callback_viewDialog);

                }
            }
        }
    }

    private void findViews(View view) {
        Upcoming_RecyclerView_Trips = view.findViewById(R.id.Upcoming_RecyclerView_Trips);
        tripsList = new ArrayList<>();
        tripAdapter = new TripAdapter(this.activity, tripsList,callBackItemClick);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.activity, 2);
        Upcoming_RecyclerView_Trips.setLayoutManager(mLayoutManager);
        Upcoming_RecyclerView_Trips.addItemDecoration(new Util(2, Util.dpToPx(10,getResources()), true));
        Upcoming_RecyclerView_Trips.setItemAnimator(new DefaultItemAnimator());
        Upcoming_RecyclerView_Trips.setAdapter(tripAdapter);

        prepareTrips();

//        try {
//            Glide.with(this).load(R.drawable.ic_logo).into((ImageView)view.findViewById(R.id.thumbnail));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

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

        Trip a = new Trip("New York", 13, coversTrips[0],"01/05","13/05");
        tripsList.add(a);

        a = new Trip("Los Angeles", 8, coversTrips[1],"10/03","18/03");
        tripsList.add(a);

        a = new Trip("Chicago", 11, coversTrips[2],"01/04","12/04");
        tripsList.add(a);

        a = new Trip("Miami", 12, coversTrips[3],"12/12","24/12");
        tripsList.add(a);

        a = new Trip("Tokyo", 14, coversTrips[4],"02/10","16/10");
        tripsList.add(a);

        a = new Trip("Beijing", 1, coversTrips[5],"","22/05");
        tripsList.add(a);

        a = new Trip("Manila", 11, coversTrips[6],"01/04","12/04");
        tripsList.add(a);

        a = new Trip("Bangkok", 14, coversTrips[7],"02/10","16/10");
        tripsList.add(a);

        a = new Trip("Mexico City", 11, coversTrips[8],"01/04","12/04");
        tripsList.add(a);

        a = new Trip("Istanbul", 17, coversTrips[9],"07/07","24/07");
        tripsList.add(a);

        tripAdapter.notifyDataSetChanged();
    }

        ViewDialogRating.Callback_ViewDialog callback_viewDialog = new ViewDialogRating.Callback_ViewDialog() {

            @Override
            public void timeToRate(Trip trip) {

            }
        };


}
