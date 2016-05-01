package com.mappers.campus101;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mappers.campus101.http.VolleyManager;

/*
 * Login activity
 * @author Kaan Özkara
 * @date 20.04.2016
 */

public class LoginActivity extends AppCompatActivity {

    private EditText editTextstudentID;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Activity activity;
    private VolleyManager LoginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextstudentID = (EditText) findViewById(R.id.editTextstudentID);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        LoginManager = App.getRequestManager();
        activity = this;
    }

    public void onClick(View v) {
        //Calling the login function, sending the login request

        LoginManager.sendLoginRequest((editTextstudentID.getText()).toString(), editTextPassword.getText().toString(), activity);
    }
}

