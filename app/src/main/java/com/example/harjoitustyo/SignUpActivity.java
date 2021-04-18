package com.example.harjoitustyo;

import android.content.Intent;
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
    /* Validating inputs */
    private boolean validateName()  {
        String nameInput = editName.getText().toString();

        if (nameInput.isEmpty())    {
            editName.setError("Field can't be empty!");
            return false;
        }
        else    {
            editName.setError(null);
            return true;
        }
    }

    private boolean validateAge()  {
        String ageInput = editAge.getText().toString();

        if (ageInput.isEmpty())    {
            editAge.setError("Field can't be empty!");
            return false;
        }
        else    {
            editAge.setError(null);
            return true;
        }
    }

    private boolean validateUsername()  {
        String usernameInput = editUsername.getText().toString();

        if (usernameInput.isEmpty())    {
            editUsername.setError("Field can't be empty!");
            return false;
        }
        else    {
            editUsername.setError(null);
            return true;
        }
    }

    public void SignUp(View v)  {
        Login_Manager loginManager = Login_Manager.getInstance();
        /* Checking inputs */
        if (!validateName() | !validateAge() | !validateUsername())   {
            return;
        }
        /* If none of them is empty */
        loginManager.createPerson(editUsername.toString(), editPassword.toString(), editName.toString(), Integer.valueOf(editAge.toString()));
        notification = "Welcome!";
        Toast.makeText(this, notification, Toast.LENGTH_SHORT).show();
    }

    public void GoBack(View v)  {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
