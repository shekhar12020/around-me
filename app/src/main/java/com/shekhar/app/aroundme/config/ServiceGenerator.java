package com.shekhar.app.aroundme.config;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;


/**
 * Created by shekhar on 11/12/15.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "https://maps.googleapis.com";

    private static RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
//                .setClient(setOkClient())
                .setLogLevel(RestAdapter.LogLevel.FULL);

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String authToken) {  
      if (authToken != null) {
          builder.setRequestInterceptor(new RequestInterceptor() {
              @Override
              public void intercept(RequestFacade request) {
//                  request.addHeader("Authorization", "Bearer " + authToken);
              }
          });
      }else{
          Log.e("ServiceGenerator","BEVY HEADER ERROR");
      }
      RestAdapter adapter = builder.build();
      return adapter.create(serviceClass);
    }

    private static OkClient setOkClient(){
        OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.setAuthenticator(null);
        return new OkClient(okHttpClient);
    }

}