package com.mappers.campus101;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mappers.campus101.http.VolleyManager;
import com.mappers.campus101.http.VolleySingleton;

/**
 * A class to handle application contexts
 * @author Kadir Can Çelik
 * @date 22.03.2016
 */
public class App extends Application {
    private static App singleInstance;
    private static VolleyManager requestManager;

    public static App getInstance() {
        return singleInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleInstance = this;

        requestManager = new VolleyManager();
    }

    public static Context getAppContext() {
        return singleInstance.getApplicationContext();
    }

    public static VolleyManager getRequestManager() {
        return requestManager;
    }
}
