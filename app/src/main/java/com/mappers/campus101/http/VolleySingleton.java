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
    // A VolleySingleton object and its RequestQueue
    private static VolleySingleton singleInstance;
    private RequestQueue requestQueue;

    // Returns the only instance of VolleySingleton class
    public static VolleySingleton getInstance() {
        // If the instance was not created beforehand, create it
        // and return the single instance
        if ( singleInstance == null ) {
            singleInstance = new VolleySingleton();
        }
        return singleInstance;
    }

    // Constructor is private because of Singleton design pattern
    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(App.getAppContext());
    }

    // Returns the requestqueue belonging to the single Volley object
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
