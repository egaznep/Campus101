    package com.mappers.campus101;

    import android.content.Intent;
    import android.support.v7.app.ActionBarActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;

    import com.mappers.campus101.http.VolleyManager;

    public class SignUpActivity extends ActionBarActivity implements View.OnClickListener{

        private EditText editTextstudentID;
        private EditText editTextName;
        private EditText editTextSurname;
        private EditText editTextPassword;
        private EditText editTextDepartment;
        private  VolleyManager signUpManager = new VolleyManager("asdsaf");

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
            editTextDepartment = (EditText) findViewById(R.id.editTextDepartment);

            buttonRegister = (Button) findViewById(R.id.buttonRegister);
            buttonLogin = (Button) findViewById(R.id.buttonLogin1);
            buttonLogin.setOnClickListener(this);
            buttonRegister.setOnClickListener(this);
        }


        @Override
        public void onClick(View view)
        {

            if(view == buttonRegister)
            {
                //signUpManager.sendSignUpRequest(Integer.parseInt(String.valueOf(editTextstudentID)),  editTextPassword.getText().toString(), editTextName.getText().toString(), "xx@hotmail.com",  editTextDepartment.getText().toString());
                Intent intent = new Intent( SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            else if( view == buttonLogin)
            {
                //signUpManager.sendLoginRequest( Integer.parseInt(String.valueOf(editTextstudentID)), editTextPassword.getText().toString());
                Intent intent = new Intent( SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }


        }


    }
