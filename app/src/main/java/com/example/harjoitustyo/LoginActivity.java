package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView username;
    TextView password;
    TextView Errors;
    private Button SignUp;
    private Button LogIn;
    private Button Bypass;
    Login_Manager login_manager = Login_Manager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (TextView) findViewById(R.id.editTextTextPersonName);
        password = (TextView) findViewById(R.id.editTextTextPassword);
        Errors = (TextView) findViewById(R.id.ErrorField);

        //Button actions
        SignUp = (Button) findViewById(R.id.SignUp_button);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUp();
            }
        });
        LogIn = (Button) findViewById(R.id.Login_button);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        Bypass = (Button) findViewById(R.id.Bypass);
        Bypass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BypassAll();
            }
        });


    }
    public void goToSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    public void Login() {
        Intent intent = new Intent(this, MainActivity.class);
        Boolean worked = login_manager.login(password.getText().toString(), username.getText().toString());
        if (worked) {
            startActivity(intent);
        }
        else {
            Errors.setText("Wrong username or password. Sign up if you haven't created an account yet.");
        }
    }
    public void BypassAll() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}