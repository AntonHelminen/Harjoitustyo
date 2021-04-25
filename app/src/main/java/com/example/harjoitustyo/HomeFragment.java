package com.example.harjoitustyo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
/*HomeFragment is the first screen you see when logging in for the first time. It also displays
* a rating system as well as all visit times.*/
public class HomeFragment extends Fragment {

    int numberOfRatings = 0;
    float average = 0;
    float ratingSum = 0;
    String averageRate;
    String userRate;
    RatingBar ratingBar;
    TextView personal_use;
    TextView total_use;
    TextView userRating;
    TextView averageRating;
    User user = User.getInstance();
    Data_Manager data_manager = Data_Manager.getInstance();
    Person_manager person_manager = Person_manager.getInstance();
    HashMap<String, Person>people = person_manager.getPeopleMap();

    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ratingBar = (RatingBar) getView().findViewById(R.id.ratingBar);

        //Textfields
        personal_use = (TextView) getView().findViewById(R.id.Personal_Usage);
        total_use = (TextView) getView().findViewById(R.id.Total_Usage);
        Person person = user.getPerson();

        personal_use.setText("You have used this app " + person.getTimes_used() + " times.");
        total_use.setText("In total people have used this app " + data_manager.totalUses() + " times.");

        userRating = (TextView) getView().findViewById(R.id.userRating);
        averageRating = (TextView) getView().findViewById(R.id.averageRating);

        /* Showing ratings if user has submitted one before */
        if (!(user.getPerson().getRating() == 0.0))    {

            for (String key: people.keySet())   {
                Person person2 = people.get(key);
                if (!(person2.getRating() == 0.0))   {
                    numberOfRatings++;
                    ratingSum += person2.getRating();
                }
            }
            /* Calculating average rating */
            average = ratingSum / numberOfRatings;
            numberOfRatings = 0;
            ratingSum = 0;

            userRate = "Your rating: " + user.getPerson().getRating() + "/5.0";
            averageRate = "Average rating: " + average + "/5.0";
            userRating.setText(userRate);
            averageRating.setText(averageRate);

            ratingBar.setRating(user.getPerson().getRating());
        }

        ratingBar = (RatingBar) getView().findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                /* Setting and getting ratings */
                Person person = user.getPerson();
                userRate = "Your rating: " + rating + "/5.0";
                userRating.setText(userRate);
                person.setRating(rating);
                user.setPerson(person);
                person_manager.removePerson(user.getPerson().getPassword(), user.getPerson().getUsername());
                person_manager.addPerson(person);

                for (String key: people.keySet())   {
                    Person person2 = people.get(key);
                    if (!(person2.getRating() == 0.0))   {
                        numberOfRatings++;
                        ratingSum += person2.getRating();
                    }
                }
                /* Calculating average rating */
                average = ratingSum / numberOfRatings;

                averageRate = "Average rating: " + average + "/5.0";
                averageRating.setText(averageRate);
                numberOfRatings = 0;
                ratingSum = 0;
            }
        });


    }
}
