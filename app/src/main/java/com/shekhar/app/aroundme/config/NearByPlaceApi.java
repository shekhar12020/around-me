package com.shekhar.app.aroundme.config;

import com.shekhar.app.aroundme.model.detail.PlaceDetailResponsee;
import com.shekhar.app.aroundme.model.list.PlaceSearchResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by shekhar on 29/12/15.
 */
public interface NearByPlaceApi {

    //GET NEAR BY PLACES
    @GET("/maps/api/place/nearbysearch/json")
    void getNearByPlaces(
            @Query("key") String key,
            @Query("location") String lat_long,
            @Query("types") String type,
            @Query("radius") String radius,
            @Query("rankBy") String rankBy,
            Callback<PlaceSearchResponse> callback);


    //GET PLACE DETAILS BY PLACE_ID
    @GET("/maps/api/place/details/json?placeid=ChIJdYv6_9G-wjsRqd6fCFJH1oc&key=AIzaSyAhLwqfjhbZrBVApG-0OwmrojqnC1mP66w")
    void getPlaceDetails(
            Callback<PlaceDetailResponsee> callback);


}
