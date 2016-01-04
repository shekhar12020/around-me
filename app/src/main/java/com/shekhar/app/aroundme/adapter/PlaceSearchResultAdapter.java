package com.shekhar.app.aroundme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shekhar.app.aroundme.R;
import com.shekhar.app.aroundme.custom.PlaceSearchResultViewHolder;
import com.shekhar.app.aroundme.global.GlobalData;
import com.shekhar.app.aroundme.model.list.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaceSearchResultAdapter extends RecyclerView.Adapter<PlaceSearchResultViewHolder> {

    private String TAG = "PlaceOptionAdapter";
    private ArrayList<Result> resultList;
    private Context mContext;

    public PlaceSearchResultAdapter(Context context, ArrayList<Result> optionItems) {
        this.resultList = optionItems;
        this.mContext = context;

    }

    @Override
    public PlaceSearchResultViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_list_row_place_search_result, viewGroup, false);
        return new PlaceSearchResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceSearchResultViewHolder listRowHolder, int i) {
        Result item = resultList.get(i);


        if (item.getPhotos()!=null && item.getPhotos().size()>0 && item.getPhotos().get(0).getPhotoReference()!=null){
            Picasso.with(mContext).load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=200&photoreference="+item.getPhotos().get(0).getPhotoReference()+"&key="+mContext.getString(R.string.api_key_server))
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(listRowHolder.thumbnail);
        }else{
            Picasso.with(mContext).load(item.getIcon())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(listRowHolder.thumbnail);
        }

        listRowHolder.title.setText(item.getName());
        listRowHolder.address.setText(item.getVicinity());

        if (item.getRating()!=null){
            listRowHolder.ratingLayout.setVisibility(View.VISIBLE);
            listRowHolder.ratingBar.setRating(Float.parseFloat(String.valueOf(item.getRating())));
        }else{
            listRowHolder.ratingLayout.setVisibility(View.GONE);
        }

        listRowHolder.distance_val.setText(String.format("%.2f", distance(
                Double.parseDouble(GlobalData.getInstance().getCurrentLatitude()),
                Double.parseDouble(GlobalData.getInstance().getCurrentLongitude()),
                item.getGeometry().getLocation().getLat(),
                item.getGeometry().getLocation().getLng()))+" km");

    }

    @Override
    public int getItemCount() {
        return (null != resultList ? resultList.size() : 0);
    }

    //method to calculate distance between to location
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}