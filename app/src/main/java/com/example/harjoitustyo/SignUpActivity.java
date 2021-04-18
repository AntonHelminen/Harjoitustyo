package com.example.harjoitustyo;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText editName, editAge, editUsername, editPassword, editConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName = (EditText) findViewById(R.id.input_name);
        editAge = (EditText) findViewById(R.id.input_age);
        editUsername = (EditText) findViewById(R.id.input_username);
        editPassword = (EditText) findViewById(R.id.input_password);
        editConfirm = (EditText) findViewById(R.id.input_confirm);

    }
}
