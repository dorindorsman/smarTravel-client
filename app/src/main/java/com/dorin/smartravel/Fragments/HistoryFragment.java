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

import com.dorin.smartravel.CallBacks.CallBackItemClick;
import com.dorin.smartravel.Helpers.DataManger;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.Adapters.TripAdapter;
import com.dorin.smartravel.R;
import com.dorin.smartravel.Helpers.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryFragment extends Fragment {

    private AppCompatActivity activity;


    private RecyclerView History_RecyclerView_Trips;
    private TripAdapter tripAdapter;
    private List<Trip> tripsList;
    private DataManger dataManger;

    CallBackItemClick callBackItemClick = new CallBackItemClick() {
        @Override
        public void itemClick() {
            getParentFragmentManager().beginTransaction().replace(R.id.main_fragment,DaysPathTripFragment.class,null).commit();

        }

        @Override
        public void itemDelete(int position) {

        }
    };


    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public HistoryFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_history, container, false);
        dataManger=DataManger.getInstance();
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        History_RecyclerView_Trips = view.findViewById(R.id.History_RecyclerView_Trips);
        tripsList = new ArrayList<>();
        initTrips();
        tripAdapter = new TripAdapter(this.activity, tripsList,callBackItemClick);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.activity, 2);
        History_RecyclerView_Trips.setLayoutManager(mLayoutManager);
        History_RecyclerView_Trips.addItemDecoration(new Util(2, Util.dpToPx(10,getResources()), true));
        History_RecyclerView_Trips.setItemAnimator(new DefaultItemAnimator());
        History_RecyclerView_Trips.setAdapter(tripAdapter);
        tripAdapter.notifyDataSetChanged();


    }

    private void initTrips() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        for (Trip t:dataManger.getUpcomingTripList()) {
            try {
                Date tripEndDate = formatter.parse(t.getEndDate());
                if(tripEndDate.before(date)){
                    dataManger.getHistoryTripList().add(t);
                    dataManger.getUpcomingTripList().remove(t);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            }
    }


}
