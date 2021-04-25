package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
/*Fragment for displaying some calculated data aswell as having links to two chartActivities.*/
public class ViewDataFragment extends Fragment {

    TextView message;
    TextView info;
    TextView waste_type;
    TextView C02_amount;
    TextView units;
    TextView analysis;
    User user = User.getInstance();
    Person person = user.getPerson();
    Data_Manager data_manager = Data_Manager.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_data, container, false);
    }
    //V
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        API_reader api_reader = API_reader.getInstance();
        //All visual operations
        message = (TextView) getView().findViewById(R.id.Display_Text);
        info = (TextView) getView().findViewById(R.id.Info_Box);
        waste_type = (TextView) getView().findViewById(R.id.Waste_Type);
        C02_amount = (TextView) getView().findViewById(R.id.C02_Amount);
        units = (TextView) getView().findViewById(R.id.Units);
        analysis = (TextView) getView().findViewById(R.id.analysis);

        if (person.getC02().isEmpty()) {
            message.setText("You need data to see your footprint. Go add some in the Add Data - page.");
            message.setTextSize(15);
            info.setText("");
            waste_type.setText("");
            C02_amount.setText("");
            units.setText("");
        }
        else {
            int i = 0;
            ArrayList<Double> values = person.getC02();
            String worst_waste = data_manager.worst_waste_type(person);
            String[] parts = worst_waste.split(";");
            worst_waste = parts[0];
            Double worst_waste_amount = Double.valueOf(parts[1]);
            while (i < person.getC02().size()-1) {
                i ++;
            }
            Double value = values.get(i);
            //Setting the background
            if (value < 50) {
                view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.great));
            }
            else if (value < 75) {
                view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.prettygood));
            }
            else if (value < 100) {
                view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.average));
            }
            else if (value < 125) {
                view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.betterbutnotgood));
            }
            else {
                view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.notsogreat));
            }

            worst_waste_amount = (worst_waste_amount/value)*100;
            //In order
            message.setText("Your current carbon footprint");
            C02_amount.setText(String.format("%.0f",value));
            units.setText("kg of C02/Year");
            //Additional fields
            if (String.format("%.0f", worst_waste_amount).equals("0")) {
                info.setText("You're doing very good! Keep your recycling habits just like this and try to minimize waste production.");
                info.setTextSize(20);
                waste_type.setText("");
                analysis.setText("");
            }
            else {
                info.setText("To reduce your emissions, you should recycle more:");
                if (worst_waste.equals("bioWaste")) {
                    waste_type.setText("biowaste");
                }
                else {
                    waste_type.setText(worst_waste + " waste");
                }

                analysis.setText(String.format("Which is causing %.0f %% of your total waste C02 emissions.", worst_waste_amount));
            }


        }


        getView().findViewById(R.id.buttonBarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BarChartActivity.class));
            }
        });

        getView().findViewById(R.id.buttonPieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PieChartActivity.class));
            }
        });

    }
}