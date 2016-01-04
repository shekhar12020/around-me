package com.shekhar.app.aroundme.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.shekhar.app.aroundme.R;
import com.shekhar.app.aroundme.adapter.PlaceSearchResultAdapter;
import com.shekhar.app.aroundme.config.NearByPlaceApi;
import com.shekhar.app.aroundme.config.NetworkUtilities;
import com.shekhar.app.aroundme.config.ServiceGenerator;
import com.shekhar.app.aroundme.global.GlobalData;
import com.shekhar.app.aroundme.model.detail.PlaceDetailResponsee;
import com.shekhar.app.aroundme.model.list.PlaceSearchResponse;
import com.shekhar.app.aroundme.model.list.Result;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class PlaceSearchResultActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static String TAG = "PlaceSearchResultActivity";
    protected GoogleApiClient mGoogleApiClient;

    private RecyclerView mRecyclerView;
    private String type = "";
    private ProgressBar progressBar;
    private TextView no_data_found;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buildGoogleApiClient();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        mContext = PlaceSearchResultActivity.this;

        if (getIntent() != null && getIntent().getStringExtra("type") != null) {
            type = getIntent().getStringExtra("type");
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        no_data_found = (TextView) findViewById(R.id.no_data_found);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (NetworkUtilities.isInternet(PlaceSearchResultActivity.this)) {
            callNearByPlacesRequest();
        } else {
            progressBar.setVisibility(View.GONE);
            no_data_found.setVisibility(View.VISIBLE);
            no_data_found.setText(getString(R.string.error_no_internet_connection));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportActionBar().show();
                    getFragmentManager().popBackStack();
                } else {
                    super.onBackPressed();
                }
        }
        return true;
    }

    private void callNearByPlacesRequest() {
        progressBar.setVisibility(View.VISIBLE);
        no_data_found.setVisibility(View.GONE);

        NearByPlaceApi nearByPlaceApi = ServiceGenerator.createService(NearByPlaceApi.class);

        nearByPlaceApi.getNearByPlaces(
                getString(R.string.api_key_server),
                GlobalData.getInstance().getCurrentLatitude() + "," + GlobalData.getInstance().getCurrentLongitude(),
                type,
                "5000",
                "distance",
                new retrofit.Callback<PlaceSearchResponse>() {
                    @Override
                    public void success(PlaceSearchResponse data, Response response) {
                        progressBar.setVisibility(View.GONE);
                        if (data != null && data.getResults().size() > 0) {
                            setPlaceOption(data.getResults());
                        } else {
                            no_data_found.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressBar.setVisibility(View.GONE);
                        no_data_found.setVisibility(View.VISIBLE);
                        Log.d("Success", "Response : Failure " + error.getMessage());
                    }
                });

    }

    public void setPlaceOption(ArrayList<Result> results) {
        mRecyclerView.setAdapter(new PlaceSearchResultAdapter(PlaceSearchResultActivity.this, results));
    }


    private void callPlaceDetailsRequest() {
        NearByPlaceApi nearByPlaceApi = ServiceGenerator.createService(NearByPlaceApi.class);

        nearByPlaceApi.getPlaceDetails(
                new retrofit.Callback<PlaceDetailResponsee>() {
                    @Override
                    public void success(PlaceDetailResponsee data, Response response) {
                        Log.d("Success", "Response : Success " + data.getResult().getFormattedAddress());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("Success", "Response : Failure " + error.getMessage());
                    }
                });

    }

    public  void placePhotosAsync(String placeId, final ImageView placePhoto) {
        Places.GeoDataApi.getPlacePhotos(mGoogleApiClient, placeId)
                .setResultCallback(new ResultCallback<PlacePhotoMetadataResult>() {


                    @Override
                    public void onResult(PlacePhotoMetadataResult photos) {
                        if (!photos.getStatus().isSuccess()) {
                            return;
                        }

                        PlacePhotoMetadataBuffer photoMetadataBuffer = photos.getPhotoMetadata();
                        Log.d(TAG, "SIZE : " + photoMetadataBuffer.getCount());
                        if (photoMetadataBuffer.getCount() > 0) {
                            // Display the first bitmap in an ImageView in the size of the view
                            photoMetadataBuffer.get(0)
                                    .getScaledPhoto(mGoogleApiClient, placePhoto.getWidth(), placePhoto.getHeight())
                                    .setResultCallback(
                                            new ResultCallback<PlacePhotoResult>() {
                                                @Override
                                                public void onResult(PlacePhotoResult placePhotoResult) {
                                                    if (!placePhotoResult.getStatus().isSuccess()) {
                                                        return;
                                                    }
                                                    placePhoto.setImageBitmap(placePhotoResult.getBitmap());
//                                                    Picasso.with(mContext).load(placePhotoResult.getBitmap())
//                                                            .placeholder(R.mipmap.ic_launcher)
//                                                            .error(R.mipmap.ic_launcher)
//                                                            .into(placePhoto);
                                                }
                                            });
                        }
                        photoMetadataBuffer.release();
                    }
                });
    }

    protected synchronized void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, 0, this)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
