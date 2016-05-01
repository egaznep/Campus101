    package com.mappers.campus101;

    import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mappers.campus101.http.VolleyManager;
import com.mappers.campus101.models.Department;

    /*
     * Sign up activity
     * @autor Yılmaz Korkmaz
     * @date 20.04.2016
     */

    public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

        private EditText editTextstudentID;
        private EditText editTextName;
        private EditText editTextSurname;
        private EditText editTextPassword;
        private Spinner spinnerDepartment;
        private VolleyManager signUpManager;
        private String department;
        private Button buttonRegister;
        private Button buttonLogin;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);

            //Relating the Text Views in XML with Java code
            editTextstudentID = (EditText) findViewById(R.id.editTextstudentID);
            editTextName = (EditText) findViewById(R.id.editTextName);
            editTextSurname = (EditText) findViewById(R.id.editTextSurname);
            editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            spinnerDepartment = (Spinner) findViewById(R.id.spinnerDepartment);

            //Initializing the necessary views/variables
            signUpManager = App.getRequestManager();
            buttonRegister = (Button) findViewById(R.id.buttonRegister);
            buttonLogin = (Button) findViewById(R.id.buttonLogin1);
            buttonLogin.setOnClickListener(this);
            buttonRegister.setOnClickListener(this);
            spinnerDepartment.setOnItemSelectedListener(this);
            spinnerDepartment.setAdapter(new ArrayAdapter<Department>(this, android.R.layout.simple_spinner_item, Department.values()));
        }


        @Override
        public void onClick(View view)
        {
            //If it is the first time that user signs up the user will click on Sign Up button ( Register)
            if(view == buttonRegister)
            {
                signUpManager.sendSignUpRequest((editTextstudentID.getText().toString()) ,(editTextPassword.getText()).toString(), (editTextName.getText()).toString() + (editTextSurname.getText()).toString(), "",  department);
                Intent intent = new Intent( SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            //If user has already Signed up then he will choose login button to log in.
            else if( view == buttonLogin)
            {
                Intent intent = new Intent( SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
        {
            department = adapterView.getItemAtPosition(i).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
