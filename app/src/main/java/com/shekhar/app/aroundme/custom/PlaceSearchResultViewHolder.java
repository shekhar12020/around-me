package com.shekhar.app.aroundme.custom;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.shekhar.app.aroundme.R;

public class PlaceSearchResultViewHolder extends RecyclerView.ViewHolder {
    public ImageView thumbnail;
    public TextView title, address;
    public TextView distance_val;

    public LinearLayout ratingLayout;
    public RatingBar ratingBar;

    public PlaceSearchResultViewHolder(View view) {
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        this.title = (TextView) view.findViewById(R.id.title);
        this.address = (TextView) view.findViewById(R.id.address);
        this.distance_val = (TextView) view.findViewById(R.id.distance_val);
        this.ratingLayout = (LinearLayout) view.findViewById(R.id.rating_layout);
        this.ratingBar = (RatingBar) view.findViewById(R.id.ratingBarValue);
    }
}