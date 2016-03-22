package com.mappers.campus101.http;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mappers.campus101.*;

/**
 * Volley class using singleton design pattern, contains request queue
 * @author Kadir Can Çelik
 * @date 22.03.2016
 */
public class VolleySingleton {
    private static VolleySingleton singleInstance;
    private RequestQueue requestQueue;

    public static VolleySingleton getInstance() {
        if ( singleInstance == null ) {
            singleInstance = new VolleySingleton();
        }
        return singleInstance;
    }

    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(App.getAppContext());
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
