package com.shekhar.app.aroundme;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceOptionListRowHolder extends RecyclerView.ViewHolder {
    public ImageView thumbnail;
    public TextView title;

    public PlaceOptionListRowHolder(View view) {
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        this.title = (TextView) view.findViewById(R.id.title);
    }

}