package com.mappers.campus101.http;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import static com.mappers.campus101.models.MD5Creator.createHash;


/**
 * Holds methods for frequently used requests to the server
 * @author Kadir Can Çelik
 * @date 9.4.2016
 */
public class VolleyManager {
    // Instance variables
    private VolleySingleton mVolleyInstance;
    private String mAddress;

    // Constructor sets the VolleySingleton object and address of the server
    public VolleyManager(String address) {
        mVolleyInstance = VolleySingleton.getInstance();
        mAddress = address;
    }

    // Adds a request to the requestqueue of Volley
    public void addRequest(Request request) {
        mVolleyInstance.getRequestQueue().add(request);
    }

    // Sends a sign-up request to the server
    // Sends the password hashed as md5
    public void sendSignUpRequest(int id, String password, String name, String email, String department) {
        // Hash the password with md5
        String password_md5 = createHash(Integer.toString());

        // Create full address for the request
        // Since php code uses get requests, send the parameters to php code directly
        String signUpRequestAddress = mAddress + "/signup.php?password_md5=" + password_md5 + "&id="
                + id + "&name=" + name + "&email=" + email + "&department=" + department;

        // Create the request with the address and response listener
        // Since volley is working asynchronous, a new method has to be called
        // when a response from server comes
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

        // Add the request to the queue
        addRequest(signUpRequest);
    }

    // Send a login request to the server
    // Only id and hashed password is needed for this
    public void sendLoginRequest(int id, String password_md5) {
        // Create the address for login request
        String loginRequestAddress = mAddress + "/login.php?password_md5=" + password_md5
                + "&id=" + id;

        // Create the request with address and response listener
        StringRequest loginRequest = new StringRequest(Request.Method.GET,
                loginRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loggedIn(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to queue
        addRequest(loginRequest);
    }

    // TO-DO : Implement later
    public boolean loggedIn (String response) {
        return true;
    }

    // Send an addqueue request to server
    // addqueue request is for grouping
    // When students need a group, for example they finish the solitary task
    // or they finish all the necessary tasks with their already assigned
    // group and they want to play again, an addqueue request will be sent to server
    public void sendAddQueueRequest(int id) {
        // Address for addqueue request
        String addQueueRequestAddress = mAddress + "/addqueue.php?" + "&id=" + id;

        // Create the request with the address and response listener
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

        // Add the request to queue
        addRequest(addQueueRequest);
    }

    // Sends a gettask request to server
    // When students finish a task and if they did not complete the turn
    // a gettask request will be sent to server to get a new task
    public void sendGetTaskRequest (int id) {
        // Address for gettask request
        String getTaskRequestAddress = mAddress + "/gettask.php?" + "&id=" + id;

        // Create the request with address and response listener
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

        // Add the request to queue
        addRequest(getTaskRequest);
    }

    // TO-DO : Implement later
    public boolean processTask (String response) {
        return true;
    }

    // Send a finishtask request to server
    // When a student reads a qr code, a finishtask request will be sent to server
    // to understand whether the student went to the correct place or not
    public void sendFinishTaskRequest (int id, String QRString) {
        // Address for finishtask request
        String finishTaskRequestAddress = mAddress + "/finishtask.php?" + "&id=" + id + "&qrstring="
                + QRString;

        // Create the request with address and response listener
        StringRequest getTaskRequest = new StringRequest(Request.Method.GET,
                finishTaskRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        finishTaskResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to queue
        addRequest(getTaskRequest);
    }

    // TO-DO : Implement later
    public void finishTaskResponse(String response) {

    }
}
