package com.shekhar.app.aroundme.activity;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shekhar.app.aroundme.R;
import com.shekhar.app.aroundme.adapter.PlaceSearchResultAdapter;
import com.shekhar.app.aroundme.config.NearByPlaceApi;
import com.shekhar.app.aroundme.config.NetworkUtilities;
import com.shekhar.app.aroundme.config.ServiceGenerator;
import com.shekhar.app.aroundme.global.GlobalData;
import com.shekhar.app.aroundme.model.list.PlaceSearchResponse;
import com.shekhar.app.aroundme.model.list.Result;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class PlaceSearchResultActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private String type = "";
    private ProgressBar progressBar;
    private TextView no_data_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

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
                GlobalData.getInstance().getCurrentLatitude()+","+GlobalData.getInstance().getCurrentLongitude(),
                type,
                "5000",
                "distance",
                new retrofit.Callback<PlaceSearchResponse>() {
                    @Override
                    public void success(PlaceSearchResponse data, Response response) {
                        progressBar.setVisibility(View.GONE);
                        if (data != null && data.getResults().size()>0){
                            setPlaceOption(data.getResults());
                        }else{
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

}
