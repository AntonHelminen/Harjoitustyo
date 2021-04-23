package com.example.harjoitustyo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private EditText editName, editAge, editUsername, editPassword, editConfirm;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName = (EditText) findViewById(R.id.edit_name);
        editAge = (EditText) findViewById(R.id.edit_age);
        editUsername = (EditText) findViewById(R.id.edit_username);
        editPassword = (EditText) findViewById(R.id.edit_password);
        editConfirm = (EditText) findViewById(R.id.edit_confirm);
        context = SignUpActivity.this;

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

    private boolean validatePassword()  {
        String passwordInput = editPassword.getText().toString();
        String confirmInput = editConfirm.getText().toString();

        if (passwordInput.isEmpty())    {
            editPassword.setError("Field can't be empty!");
            editConfirm.setError("Field can't be empty!");
            return false;
        }

        if (!(passwordInput.length() >= 12)) {
            editPassword.setError("Is not long enough. Must be at least 12 characters.");
            return false;
        }

        if (Pattern.matches("[a-zA-Z0-9]+", passwordInput)) {
            editPassword.setError("Doesn't contain a special character.");
            return false;
        }

        char[] chars = passwordInput.toCharArray();
        Boolean hasDigit = false;
        Boolean hasUppercase = false;
        Boolean hasLowercase = false;
        for (char c: chars) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }

        }
        if (hasDigit) {
        }
        else {
            editPassword.setError("Doesn't contain a digit.");
            return false;
        }
        if (hasUppercase) {
        }
        else {
            editPassword.setError("Doesn't contain an uppercase letter.");
            return false;
        }
        if (hasLowercase) {
        }
        else {
            editPassword.setError("Doesn't contain an lowercase letter.");
            return false;
        }

        if (!(passwordInput.equals(confirmInput)))    {
            editPassword.setError("Passwords do not match!");
            editConfirm.setError("Passwords do not match!");
            return false;
        }
        else    {
            editUsername.setError(null);
            return true;
        }
    }

    public void SignUp(View v)  {
        Login_Manager loginManager = Login_Manager.getInstance();
        Person_manager person_manager = Person_manager.getInstance();
        /* Checking inputs */
        if (!validateName() | !validateAge() | !validateUsername() | !validatePassword())   {
            return;
        }
        loginManager.createPerson(editUsername.getText().toString(), editPassword.getText().toString(), editName.getText().toString(), Integer.valueOf(editAge.getText().toString()));
        /* If fields aren't empty and password is good */
        for (String key : person_manager.getPeopleMap().keySet()) {
            System.out.println(person_manager.getPeopleMap().get(key).getBioWaste());
        }
        Toast.makeText(this, "Account created!", Toast.LENGTH_LONG).show();
        person_manager.writeFile(context);
    }

    public void GoBack(View v)  {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
