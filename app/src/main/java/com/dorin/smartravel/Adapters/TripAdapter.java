package com.dorin.smartravel.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.ContentLengthInputStream;
import com.dorin.smartravel.CallBackItemClick;
import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder> {

    private Context mContext;
    private List<Trip> tripList;
    private CallBackItemClick callBackItemClick;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, startDate, endDate;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            startDate = (TextView) view.findViewById(R.id.startDate);
            endDate = (TextView) view.findViewById(R.id.endDate);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public TripAdapter(Context mContext, List<Trip> tripList,CallBackItemClick callBackItemClick) {
        this.mContext = mContext;
        this.tripList = tripList;
        this.callBackItemClick = callBackItemClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_trip, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Trip trip = tripList.get(position);
        holder.title.setText(trip.getName());
        holder.count.setText(trip.getNumOfDays() + " Days");
        holder.startDate.setText(trip.getStartDate());
        holder.endDate.setText(" - " + trip.getEndDate());

        // loading album cover using Glide library
        Glide.with(mContext).load(trip.getThumbnail()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManger.getInstance().setCurrentTrip(trip);
                callBackItemClick.itemClick();

            }
        });

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow, trip);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, Trip trip) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_trip, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(trip));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        Trip trip;

        public MyMenuItemClickListener(Trip trip) {
            this.trip = trip;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_edit_trip:
                    Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_delete_trip:
                    Toast.makeText(mContext, "delete", Toast.LENGTH_SHORT).show();

                    new MaterialAlertDialogBuilder(mContext)
                            .setTitle("Delete Message")
                            .setMessage("Are you sure you want to delete the " + trip.getName() + " trip?")
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    if (myList.getCreatorUid().equals(currentUser.getUid())) {
//                                        removeList(myList);
//                                    } else {
//                                        new MaterialAlertDialogBuilder(currentActivity)
//                                                .setTitle("שגיאה!")
//                                                .setMessage("רק יוצר הרשימה יכול למחוק את הרשימה, אם ברצונך להעלים את הרשימה עלייך לפנות ליוצר")
//                                                .setNeutralButton("קיבלתי", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        dialog.dismiss();
//                                                    }
//                                                }).show();
//                                    }
                                }
                            })
                            .show();



            return true;
            default:
        }
            return false;
    }

}



    @Override
    public int getItemCount() {
        return tripList.size();
    }
}