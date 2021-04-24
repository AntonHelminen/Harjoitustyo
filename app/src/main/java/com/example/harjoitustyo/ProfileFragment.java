package com.example.harjoitustyo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProfileFragment extends Fragment {

    TextView textName;
    TextView textAge;
    CalendarView calender;
    String name, age;
    String formatedDate;
    long dateInMillis, currentInMillis, ageInMillis;
    int ageInt;
    Calendar currentTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        textName = (TextView) getView().findViewById(R.id.textViewName);
        textAge = (TextView) getView().findViewById(R.id.textViewAge);

        User user = User.getInstance();

        name = "Name: " + user.getPerson().getName();
        age = "Age: " + user.getPerson().getAge();

        textName.setText(name);
        textAge.setText(age);

        final Calendar calendar = Calendar.getInstance();

        currentTime = Calendar.getInstance();
        currentTime.getTime();
        currentInMillis = currentTime.getTimeInMillis();
        System.out.println("This is current date in milliseconds: " + currentInMillis);

        calender = (CalendarView) getView().findViewById(R.id.calendarView);
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                long selectedDateInMillis = calendar.getTimeInMillis();
                formatedDate = sdf.format(selectedDateInMillis);
                dateInMillis = selectedDateInMillis;
                System.out.println("This is selected date in milliseconds: " + dateInMillis);
                ageInMillis = currentInMillis - dateInMillis;
                ageInt = Math.round(Math.toIntExact(ageInMillis / 1000 / 60 / 60 / 24 / 365));
                System.out.println("ageInt:" + ageInt);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
