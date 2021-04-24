package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ViewDataFragment extends Fragment {

    TextView text;
    TextView message;
    TextView info;
    TextView waste_type;
    TextView C02_amount;
    TextView units;
    User user = User.getInstance();
    Person person = user.getPerson();
    Data_Manager data_manager = Data_Manager.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        API_reader api_reader = API_reader.getInstance();
        //Some test stuff
        text = (TextView) getView().findViewById(R.id.textView_result);
        text.setText(String.format("%.3f", api_reader.getResult()));
        //The real deal
        message = (TextView) getView().findViewById(R.id.Display_Text);
        info = (TextView) getView().findViewById(R.id.Info_Box);
        waste_type = (TextView) getView().findViewById(R.id.Waste_Type);
        C02_amount = (TextView) getView().findViewById(R.id.C02_Amount);
        units = (TextView) getView().findViewById(R.id.Units);

        if (person.getC02() == null) {
            message.setText("You have no data to see your footprint. Go add some in the Add Data - page.");
            message.setTextSize(15);
            info.setText("");
            waste_type.setText("");
            C02_amount.setText("");
            units.setText("");
        }
        else {
            int i = 0;
            ArrayList<Double> values = person.getC02();
            //String worst_waste = data_manager.worst_waste_type(person);
            while (i < person.getC02().size()-1) {
                i ++;
            }
            Double value = values.get(i);
            C02_amount.setText(String.format("%.0f",value));
            message.setText("Your current carbon footprint");
            info.setText("To reduce your emissions drastically, you should recycle more:");
            //waste_type.setText(worst_waste);
            units.setText("kg of C02/Year");
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