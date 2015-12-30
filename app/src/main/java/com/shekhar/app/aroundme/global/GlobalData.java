package com.shekhar.app.aroundme.global;

import android.app.Application;

/**
 * Created by shekhar on 30/12/15.
 */
public class GlobalData extends Application {

    public static GlobalData mGlobalData;
    private String currentLatitude;
    private String currentLongitude;

    public String getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(String currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public static GlobalData getInstance() {

        if (mGlobalData == null) {
            mGlobalData = new GlobalData();
        }
        return mGlobalData;
    }

}
