package com.mappers.campus101.http;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mappers.campus101.App;
import com.mappers.campus101.LoginActivity;
import com.mappers.campus101.MapsActivity;
import com.mappers.campus101.R;
import com.mappers.campus101.models.Student;
import com.mappers.campus101.models.Team;

import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Timer;
import java.util.TimerTask;

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
    private boolean loggedIn;
    private static Student currentStudent;
    private String teamMembers;
    private String task;
    private CountDownTimer teamTimer;
    private static Team team;

    // Constructor sets the VolleySingleton object and address of the server
    public VolleyManager() {
        mVolleyInstance = VolleySingleton.getInstance();
        mAddress = "http://46.101.121.195";
        teamMembers = "";
        team = null;
        }


    // Adds a request to the requestqueue of Volley
    public void addRequest(Request request) {
        mVolleyInstance.getRequestQueue().add(request);
    }

    // Sends a sign-up request to the server
    // Sends the password hashed as md5
    public void sendSignUpRequest(String id, String password, String name, String email, String department) {
        // Hash the password with md5
        String password_md5 = createHash(password);

        // Create full address for the request
        // Since php code uses get requests, send the parameters to php code directly
        String signUpRequestAddress = mAddress + "/signup.php?password_md5=" + password_md5 + "&id="
                + id + "&name=" + name + "&email=" + email + "&department=" + department;

        // Create the request with the address and response listener
        // Since volley is working asynchronous, a new method has to be called
        // when a response from server comes
        StringRequest signUpRequest = new StringRequest(signUpRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i ("Response", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e ("Error", error.toString());
            }
        });

        // Add the request to the queue
        addRequest(signUpRequest);

    }

    // Send a login request to the server
    // Only id and hashed password is needed for this
    public void sendLoginRequest(String id, String password, final Activity activity) {
        String password_md5 = createHash(password);

        // Create the address for login request
        String loginRequestAddress = mAddress + "/login.php?password_md5=" + password_md5
                + "&id=" + id;

        // Create the request with address and response listener
        StringRequest loginRequest = new StringRequest(Request.Method.GET,
                loginRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loggedIn(response, activity);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to queue
        addRequest(loginRequest);
        currentStudent = new Student(id);
    }

    // TO-DO : Implement later
    public void loggedIn (String response, Activity activity) {
        Log.i ("Logged in", response);
        response.replaceAll("\t", "");
        if ( response.equals ("Success") ) {
            Intent intent = new Intent(activity, MapsActivity.class);
            activity.startActivity(intent);
            App.getRequestManager().sendGetTaskRequest(App.getRequestManager().getCurrentStudentID());
        }
    }

    public boolean getLoggedIn()
    {
        return loggedIn;
    }

    // Send an addqueue request to server
    // addqueue request is for grouping
    // When students need a group, for example they finish the solitary task
    // or they finish all the necessary tasks with their already assigned
    // group and they want to play again, an addqueue request will be sent to server
    public void sendAddQueueRequest(String id, String location) {
        // Address for addqueue request
        String addQueueRequestAddress = mAddress + "/addqueue.php?" + "&id=" + id + "&placeid="
                + currentStudent.getCurrentTask();
        Log.i ("Add Queue", addQueueRequestAddress);
        // Create the request with the address and response listener
        StringRequest addQueueRequest = new StringRequest(addQueueRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i ("Add Queue", response);
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
    public void sendGetTaskRequest (String id) {
        // Address for gettask request
        String getTaskRequestAddress = mAddress + "/gettask.php?" + "id=" + id + "&placeid" + currentStudent.getCurrentTask();

        // Create the request with address and response listener
        StringRequest getTaskRequest = new StringRequest(Request.Method.GET,
                getTaskRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Get Task", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to queue
        addRequest(getTaskRequest);
    }

    public String sendTaskRequest (String id, final Activity activity) {
        // Address for gettask request
        String getTaskRequestAddress = mAddress + "/getcurrenttask.php?" + "id=" + id;

        // Create the request with address and response listener
        StringRequest getTaskRequest = new StringRequest(Request.Method.GET,
                getTaskRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        task = response;
                        processTask(response, activity);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to queue
        addRequest(getTaskRequest);
        return task;
    }

    // TO-DO : Implement later
    public void processTask(String response, Activity activity) {
        AlertDialog alert = new AlertDialog.Builder(activity).create();
        Log.i ("Process Task", response);
        if(response.indexOf("-")>0)
            currentStudent.setTask(response.substring(0,response.indexOf("-")));
        alert.setTitle("Task:");
        alert.setMessage(response.substring(response.indexOf("-") + 1));

        alert.setButton(AlertDialog.BUTTON_NEUTRAL,"Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });
        alert.show();
    }

    // Send a finishtask request to server
    // When a student reads a qr code, a finishtask request will be sent to server
    // to understand whether the student went to the correct place or not
    public void sendFinishTaskRequest (String id, String QRString, final Activity activity) {
        // Address for finishtask request
        String finishTaskRequestAddress = mAddress + "/finishtask.php?" + "id=" + id + "&qrstring="
                + QRString;

        Log.i ("Finish Task Address", finishTaskRequestAddress);

        // Create the request with address and response listener
        StringRequest getFinishTaskRequest = new StringRequest(Request.Method.GET,
                finishTaskRequestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Finish Task", response);
                        finishTaskResponse(response, activity);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to queue
        addRequest(getFinishTaskRequest);
    }

    // TO-DO : Implement later
    public void finishTaskResponse(String response, final Activity activity)
    {
        AlertDialog alert = new AlertDialog.Builder(activity).create();
        alert.setTitle("Task:");

        if(response.equals("Updated.")) {
            alert.setMessage("Task is completed successfully.");
            Log.i ("Team", Boolean.toString(team == null));

            teamTimer = new CountDownTimer(300000, 15000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.i("Timer", "Timer");
                    String requestAddress = mAddress + "/trygenerating.php" + "?placeid="
                            + currentStudent.getCurrentTask();

                    StringRequest generateTeamRequest = new StringRequest(requestAddress,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.i("Generate", response);
                                    Log.i("Did generate", Boolean.toString(response.equals("Created.")));
                                    if ( response.equals("Created.") ) {
                                        team = new Team();
                                        String members = getTeamMembers();
                                        Log.i ("Members", members);
                                        teamTimer.cancel();

                                        while ( members.indexOf("2") > 0 ) {
                                            Student student = new Student(members.substring(0, 8));
                                            members = members.substring(8);
                                            members = members.substring(members.indexOf("2"));
                                            team.addStudent(student);
                                        }

                                        Log.i ("Team", Boolean.toString(team == null));
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    addRequest(generateTeamRequest);
                }

                @Override
                public void onFinish() {
                    String enforceQueueAddress = mAddress + "/enforcequeue.php" + "?currentplace="
                            + currentStudent.getCurrentTask() + "&id" + currentStudent.getId();

                    StringRequest enforceQueue = new StringRequest(enforceQueueAddress,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.i ("Enforce Queue", response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                }
            };
            if ( team == null ) {
                sendAddQueueRequest(currentStudent.getId(), currentStudent.getCurrentTask());
                teamTimer.start();
            }
        } else {
            alert.setMessage("Task is not completed.");
        }
        alert.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        activity.finish();
                    }

                });
        alert.show();
    }


    public String getTeamMembers () {
        String requestAddress = mAddress + "/getteammembers.php" + "?id=" + currentStudent.getId();
        Log.i ("TAG:", "Get Team Members");
        Log.i ("Address", requestAddress);
        final StringRequest request = new StringRequest(requestAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        teamMembers = "";
                        Log.i ("Get Team Members", response);
                        try {
                            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            factory.setNamespaceAware(true);
                            XmlPullParser xpp = factory.newPullParser();
                            xpp.setInput( new StringReader ( response ) );
                            int eventType = xpp.getEventType();
                            int counter = 0;
                            while(eventType != XmlPullParser.END_DOCUMENT)
                            {
                                if (eventType == XmlPullParser.TEXT)
                                {
                                    teamMembers =  teamMembers + xpp.getText() +"               ";
                                    if(counter %2 == 1)
                                        teamMembers = teamMembers + "\n" ;
                                    Log.i("TEAM", teamMembers);
                                    counter++;
                                }
                                eventType = xpp.next();
                            }
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        {
                    };
                    }
                    }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i ("Error", error.toString());
            }
        });
        addRequest(request);
        return teamMembers;
    }

    public String getCurrentStudentID()
    {
        return currentStudent.getId();
    }
}
