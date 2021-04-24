package com.example.harjoitustyo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    TextView personal_use;
    TextView total_use;
    User user = User.getInstance();
    Data_Manager data_manager = Data_Manager.getInstance();
    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Textfields
        personal_use = (TextView) getView().findViewById(R.id.Personal_Usage);
        total_use = (TextView) getView().findViewById(R.id.Total_Usage);
        Person person = user.getPerson();

        personal_use.setText("You have used this app " + person.getTimes_used() + " times.");
        total_use.setText("In total people have used this app " + data_manager.totalUses() + " times.");
    }
}
