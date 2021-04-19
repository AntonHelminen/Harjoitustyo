package com.example.harjoitustyo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NavigationHeader extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_header);

        User user = User.getInstance();
        text = (TextView) findViewById(R.id.textView);
        text.setText(user.getPerson().getUsername());
        //TODO add username when logged in
    }

}
