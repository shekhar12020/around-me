package com.shekhar.app.aroundme.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shekhar.app.aroundme.R;
import com.shekhar.app.aroundme.activity.PlaceSearchResultActivity;
import com.shekhar.app.aroundme.custom.PlaceOptionViewHolder;
import com.shekhar.app.aroundme.model.PlaceOptionItem;

import java.util.ArrayList;

public class PlaceOptionAdapter extends RecyclerView.Adapter<PlaceOptionViewHolder> {

    private String TAG="PlaceOptionAdapter";
    private ArrayList<PlaceOptionItem> optionItems;
    private Context mContext;

    public PlaceOptionAdapter(Context context, ArrayList<PlaceOptionItem> optionItems) {
        this.optionItems = optionItems;
        this.mContext = context;

    }

    @Override
    public PlaceOptionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_list_row_place_option,viewGroup, false);

        return new PlaceOptionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceOptionViewHolder feedListRowHolder, int i) {
        final PlaceOptionItem feedItem = optionItems.get(i);

        feedListRowHolder.thumbnail.setImageResource(feedItem.getIcon());
        feedListRowHolder.title.setText(feedItem.getTitle());

        feedListRowHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, PlaceSearchResultActivity.class);
                i.putExtra("type",feedItem.getValue());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != optionItems ? optionItems.size() : 0);
    }

}