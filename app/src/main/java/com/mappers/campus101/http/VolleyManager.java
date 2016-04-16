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
    private String mAddress;

    public VolleyManager(String addres) {
        mVolleyInstance = VolleySingleton.getInstance();
    }

    public void addRequest(Request request) {
        mVolleyInstance.getRequestQueue().add(request);
    }

    public void sendSignUpRequest(int id, int password, String name, String email, String department) {
        String signUpRequestAddress = mAddress + "/signup.php?password_md5=" + password + "&id="
                + id + "&name=" + name + "&email=" + email + "&department=" + department;
        StringRequest signUpRequest = new StringRequest(Request.Method.GET,
                signUpRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        addRequest(signUpRequest);
    }

    public void sendLoginRequest(int id, int password) {
        String loginRequestAddress = mAddress + "/login.php?password_md5=" + password + "&id=" + id;
        StringRequest loginRequest = new StringRequest(Request.Method.GET,
                loginRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        login(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        addRequest(loginRequest);
    }

    public boolean login (String response) {
        return true;
    }

    public void sendAddQueueRequest(int id) {
        String addQueueRequestAddress = mAddress + "/addqueue.php?" + "&id=" + id;
        StringRequest addQueueRequest = new StringRequest(Request.Method.GET,
                addQueueRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        addRequest(addQueueRequest);
    }

    public void sendGetTaskRequest (int id) {
        String getTaskRequestAddress = mAddress + "/gettask.php?" + "&id=" + id;
        StringRequest getTaskRequest = new StringRequest(Request.Method.GET,
                getTaskRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processTask (response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        addRequest(getTaskRequest);
    }

    public boolean processTask (String response) {
        return true;
    }

    public void sendFinishTaskRequest (int id, String QRString) {
        String finishTaskRequestAddress = mAddress + "/finishtask.php?" + "&id=" + id + "&qrstring="
                + QRString;
        StringRequest getTaskRequest = new StringRequest(Request.Method.GET,
                finishTaskRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processTask (response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        addRequest(getTaskRequest);
    }
}
