package com.mappers.campus101.http;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * @author Kadir Can Çelik
 * @date 9.4.2016
 */
public class VolleyManager {
    private VolleySingleton mVolleyInstance;
    private String mSignUpAddress;

    public VolleyManager(String addres) {
        mVolleyInstance = VolleySingleton.getInstance();
    }

    public void addRequest(Request request) {
        mVolleyInstance.getRequestQueue().add(request);
    }

    public void sendSignUpRequest(int id, int password) {
        StringRequest signUpRequest = new StringRequest(Request.Method.GET,
                mSignUpAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        signUp(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        addRequest(signUpRequest);
    }

    // TO-DO: Implement later
    public boolean signUp (String response) {
        return true;
    }

    public void sendLoginRequest(int id, int password) {
        StringRequest loginRequest = new StringRequest(Request.Method.GET,
                mSignUpAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        signUp(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        addRequest(loginRequest);
    }
}
