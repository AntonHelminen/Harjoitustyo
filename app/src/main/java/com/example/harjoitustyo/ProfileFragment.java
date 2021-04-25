package com.example.harjoitustyo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProfileFragment extends Fragment {

    Button button;
    Person updatedUser;
    TextView textName;
    TextView textAge;
    TextView textLog;
    CalendarView calender;
    String name, age;
    String formatedDate;
    String information;
    long dateInMillis, currentInMillis, ageInMillis, ageSetInMillis;
    int ageInt, ageSet, yearSet;
    Calendar currentTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        button = (Button) view.findViewById(R.id.button_log);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Showing user's data*/
                User user = User.getInstance();
                Person_manager person_manager = Person_manager.getInstance();
                information = person_manager.personToString(user.getPerson());

                textLog = (TextView) getView().findViewById(R.id.textLog);
                textLog.setText(information);
            }
        });
        return view;
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

        /* Getting current time in milliseconds */
        currentTime = Calendar.getInstance();
        currentTime.getTime();
        currentInMillis = currentTime.getTimeInMillis();
        System.out.println("This is current date in milliseconds: " + currentInMillis);

        /* Getting user's age and setting calendar view near to correct year */
        ageSet = user.getPerson().getAge();
        System.out.println("ageSet" + ageSet);
        ageSetInMillis = ageSet * 365 * 24 * 60 * 60 * 1000L;
        System.out.println("ageSetInMillis: " + ageSetInMillis);
        calendar.setTimeInMillis(currentInMillis - ageSetInMillis);
        yearSet = calendar.get(Calendar.YEAR);
        System.out.println("yearSet: " + yearSet);
        String date = "1/0/" + yearSet;
        String[] parts = date.split("/");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        Calendar calendarSet = Calendar.getInstance();
        calendarSet.set(Calendar.YEAR, year);
        calendarSet.set(Calendar.MONTH, month);
        calendarSet.set(Calendar.DAY_OF_MONTH, day);

        long milliTime = calendarSet.getTimeInMillis();

        calender = (CalendarView) getView().findViewById(R.id.calendarView);
        calender.setDate(milliTime, true, true);
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                /* Getting selected date and calculating user's age */
                calendar.set(year, month, dayOfMonth);
                long selectedDateInMillis = calendar.getTimeInMillis();
                formatedDate = sdf.format(selectedDateInMillis);
                dateInMillis = selectedDateInMillis;
                System.out.println("This is selected date in milliseconds: " + dateInMillis);
                ageInMillis = currentInMillis - dateInMillis;
                ageInt = Math.round(Math.toIntExact(ageInMillis / 1000 / 60 / 60 / 24 / 365));
                System.out.println("ageInt:" + ageInt);

                updatedUser = user.getPerson();
                updatedUser.setAge(ageInt);
                user.setPerson(updatedUser);

                Person_manager person_manager = Person_manager.getInstance();
                person_manager.removePerson(user.getPerson().getPassword(), user.getPerson().getUsername());
                person_manager.addPerson(user.getPerson());

                age = "Age: " + ageInt;
                textAge.setText(age);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
