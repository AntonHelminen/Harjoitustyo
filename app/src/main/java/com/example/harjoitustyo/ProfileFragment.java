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

public class ProfileFragment extends Fragment {
    TextView textName;
    TextView textAge;
    CalendarView calender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        textName = (TextView) getView().findViewById(R.id.textViewName);
        textAge = (TextView) getView().findViewById(R.id.textViewAge);

        User user = User.getInstance();

        textName.setText(user.getPerson().getName());
        textAge.setText(user.getPerson().getAge());

        calender = (CalendarView) getView().findViewById(R.id.calendarView);
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
