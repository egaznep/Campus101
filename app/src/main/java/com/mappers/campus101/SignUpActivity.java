    package com.mappers.campus101;

    import android.content.Intent;
    import android.support.v7.app.ActionBarActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;

    import com.mappers.campus101.http.VolleyManager;
    import com.mappers.campus101.models.Department;

    public class SignUpActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

        private EditText editTextstudentID;
        private EditText editTextName;
        private EditText editTextSurname;
        private EditText editTextPassword;
        private Spinner spinnerDepartment;
        private VolleyManager signUpManager = new VolleyManager();
        private String department;
        private Button buttonRegister;
        private Button buttonLogin;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);

            editTextstudentID = (EditText) findViewById(R.id.editTextstudentID);
            editTextName = (EditText) findViewById(R.id.editTextName);
            editTextSurname = (EditText) findViewById(R.id.editTextSurname);
            editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            spinnerDepartment = (Spinner) findViewById(R.id.spinnerDepartment);

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

            if(view == buttonRegister)
            {
                signUpManager.sendSignUpRequest(String.valueOf(editTextstudentID.getText()) +  String.valueOf(editTextSurname.getText()),  editTextPassword.getText().toString(), editTextName.getText().toString(), "xx@hotmail.com",  department);
                Intent intent = new Intent( SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
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
