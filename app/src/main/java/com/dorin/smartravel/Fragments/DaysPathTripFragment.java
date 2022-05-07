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

import com.dorin.smartravel.Adapters.DaysAdapter;
import com.dorin.smartravel.CallBackItemClick;
import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.Objects.DayTrip;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.R;
import com.dorin.smartravel.Util;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class DaysPathTripFragment extends Fragment {

    private AppCompatActivity activity;
    private MaterialToolbar materialToolbar;

    private RecyclerView daysPathTrip_RecyclerView;
    private DaysAdapter daysAdapter;
    private List<DayTrip> daysList;
    private Trip currentTrip;
    private String dayTitle =  " ";

    CallBackItemClick callBackItemClick = new CallBackItemClick() {
        @Override
        public void itemClick() {
            dayTitle = "Day " + DataManger.getInstance().getCurrentDayTrip().getDayNumber();
            materialToolbar.setTitle(currentTrip.getName()+dayTitle);
            getParentFragmentManager().beginTransaction().replace(R.id.main_fragment,DayTripFragment.class,null).commit();

        }
    };

    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public DaysPathTripFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentTrip = DataManger.getInstance().getCurrentTrip();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_days_path_trip, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {

        materialToolbar = getActivity().findViewById(R.id.main_Toolbar_Top);
        materialToolbar.setTitle(currentTrip.getName()+dayTitle);

        daysPathTrip_RecyclerView = view.findViewById(R.id.daysPathTrip_RecyclerView);
        daysList = new ArrayList<>();
        daysAdapter = new DaysAdapter(this.activity, daysList , callBackItemClick);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.activity, 1);
        daysPathTrip_RecyclerView.setLayoutManager(mLayoutManager);
        daysPathTrip_RecyclerView.addItemDecoration(new Util(1, Util.dpToPx(10,getResources()), true));
        daysPathTrip_RecyclerView.setItemAnimator(new DefaultItemAnimator());
        daysPathTrip_RecyclerView.setAdapter(daysAdapter);


        prepareDaysTrip();
    }

    private void prepareDaysTrip() {
        DayTrip a = new DayTrip(1,"23/4");
        daysList.add(a);

        a = new DayTrip(2,"24/4");
        daysList.add(a);

        a = new DayTrip(3,"25/4");
        daysList.add(a);

        a = new DayTrip(4,"26/4");
        daysList.add(a);

        a = new DayTrip(5,"27/4");
        daysList.add(a);

        daysAdapter.notifyDataSetChanged();
    }


}
