package com.dorin.smartravel.Fragments;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
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
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.Adapters.TripAdapter;
import com.dorin.smartravel.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private AppCompatActivity activity;


    private RecyclerView History_RecyclerView_Trips;
    private TripAdapter tripAdapter;
    private List<Trip> tripsList;

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
        findViews(view);

        return view;
    }

    private void findViews(View view) {
        History_RecyclerView_Trips = view.findViewById(R.id.History_RecyclerView_Trips);
        tripsList = new ArrayList<>();
        tripAdapter = new TripAdapter(this.activity, tripsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.activity, 2);
        History_RecyclerView_Trips.setLayoutManager(mLayoutManager);
        History_RecyclerView_Trips.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        History_RecyclerView_Trips.setItemAnimator(new DefaultItemAnimator());
        History_RecyclerView_Trips.setAdapter(tripAdapter);

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


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
