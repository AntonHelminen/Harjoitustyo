package com.example.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        User user = User.getInstance();

        BarChart barChart = (BarChart) findViewById(R.id.barChart);
        if (user.getPerson().getC02().size() == 0)   {
            Toast.makeText(this, "Add data first!", Toast.LENGTH_SHORT).show();
        }
        ArrayList<BarEntry> evaluations = new ArrayList<>();
        for (int i = 1; i <= user.getPerson().getC02().size(); i++) {
            evaluations.add(new BarEntry(i, Math.round(user.getPerson().getC02().get(i-1))));
        }
        BarDataSet barDataSet = new BarDataSet(evaluations, "Users evaluations");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("CO2 value (kg/year)");
        barChart.animateY(2000);
    }

    public void GoBack(View v)  {
        finish();
    }

}