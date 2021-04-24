package com.example.harjoitustyo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView username;
    TextView password;
    private Button SignUp;
    private Button LogIn;
    private Button Bypass;
    Login_Manager login_manager = Login_Manager.getInstance();
    Person_manager person_manager = Person_manager.getInstance();
    User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //First file-read
        Context context = LoginActivity.this;

        person_manager.readFile(context);


        username = (TextView) findViewById(R.id.editTextTextPersonName);
        password = (TextView) findViewById(R.id.editTextTextPassword);

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



    }
    public void goToSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    public void Login() {
        Intent intent = new Intent(this, MainActivity.class);
        Boolean worked = login_manager.login(password.getText().toString(), username.getText().toString());
        if (worked) {
            if (user.getPerson().getTimes_used() == 0) {
                Toast.makeText(this, "Welcome, " + user.getPerson().getName() + "!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Welcome back, "  + user.getPerson().getName() + "!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }

        }
        else {
            Toast.makeText(this, "Wrong username or password. Sign up if you haven't created an account yet.", Toast.LENGTH_LONG).show();
        }
    }
}