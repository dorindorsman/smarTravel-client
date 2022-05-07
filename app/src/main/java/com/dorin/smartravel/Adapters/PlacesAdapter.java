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

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dorin.smartravel.CallBackListPlaces;
import com.dorin.smartravel.Fragments.PlacesListFragment;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.R;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;


public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Place> placesList;
    private CallBackListPlaces callBackListPlaces;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Place place = getItemPosition(getAdapterPosition());
                    callBackListPlaces.rowSelected(place.getLatLng().longitude,place.getLatLng().latitude);
                }
            });
        }
    }


    public PlacesAdapter(Context mContext, List<Place> placesList, CallBackListPlaces callBackListPlaces) {
        this.mContext = mContext;
        this.placesList = placesList;
        this.callBackListPlaces = callBackListPlaces;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_place, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Place place = placesList.get(position);
        holder.title.setText(place.getName());
    }

    public Place getItemPosition(int position){
       return placesList.get(position);
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }
}