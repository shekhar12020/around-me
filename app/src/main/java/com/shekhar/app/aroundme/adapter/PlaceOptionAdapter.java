package com.shekhar.app.aroundme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shekhar.app.aroundme.custom.PlaceOptionListRowHolder;
import com.shekhar.app.aroundme.R;
import com.shekhar.app.aroundme.model.PlaceOptionItem;

import java.util.ArrayList;

public class PlaceOptionAdapter extends RecyclerView.Adapter<PlaceOptionListRowHolder> {


    private ArrayList<PlaceOptionItem> optionItems;
    private Context mContext;

    public PlaceOptionAdapter(Context context, ArrayList<PlaceOptionItem> optionItems) {
        this.optionItems = optionItems;
        this.mContext = context;

    }

    @Override
    public PlaceOptionListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_list_row_place_option,viewGroup, false);

        return new PlaceOptionListRowHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceOptionListRowHolder feedListRowHolder, int i) {
        PlaceOptionItem feedItem = optionItems.get(i);

        feedListRowHolder.thumbnail.setImageResource(feedItem.getIcon());
        feedListRowHolder.title.setText(feedItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return (null != optionItems ? optionItems.size() : 0);
    }
}