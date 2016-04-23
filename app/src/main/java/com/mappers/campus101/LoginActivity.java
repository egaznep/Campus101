package com.mappers.campus101;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mappers.campus101.http.VolleyManager;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextstudentID;
    private EditText editTextPassword;
    private Button buttonLogin;

    private VolleyManager LoginManager = new VolleyManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextstudentID = (EditText) findViewById(R.id.editTextstudentID);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }
    public void onClick(View v) {
        //Calling the login function

        LoginManager.sendLoginRequest( String.valueOf(editTextstudentID.getText()), editTextPassword.getText().toString());
        Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}
