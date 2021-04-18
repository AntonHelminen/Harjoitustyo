package com.example.harjoitustyo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    private EditText editName, editAge, editUsername, editPassword, editConfirm;
    String notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName = (EditText) findViewById(R.id.edit_name);
        editAge = (EditText) findViewById(R.id.edit_age);
        editUsername = (EditText) findViewById(R.id.edit_username);
        editPassword = (EditText) findViewById(R.id.edit_password);
        editConfirm = (EditText) findViewById(R.id.edit_confirm);

    }

    public void SignUp(View v)  {
        notification = "Welcome!";
        Toast.makeText(SignUpActivity.this, notification, Toast.LENGTH_SHORT).show();
    }

    public void GoBack(View v)  {

    }
}
