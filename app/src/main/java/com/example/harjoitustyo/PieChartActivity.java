package com.example.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
/*Forms a pieChart from data given by all user's. */
public class PieChartActivity extends AppCompatActivity {
    Data_Manager data_manager = Data_Manager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        /* Creating PieChart for sorting by age */
        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        Double[] data = data_manager.sorting_by_age();

        /* Adding data */
        ArrayList<PieEntry> age_groups = new ArrayList<>();
        age_groups.add(new PieEntry(Math.round(data[0]), "0-18"));
        age_groups.add(new PieEntry(Math.round(data[1]), "18-35"));
        age_groups.add(new PieEntry(Math.round(data[2]), "35-50"));
        age_groups.add(new PieEntry(Math.round(data[3]), "50-65"));
        age_groups.add(new PieEntry(Math.round(data[4]), "65+"));

        PieDataSet pieDataSet = new PieDataSet(age_groups, "Age groups");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Age groups' sorting comparison");
        pieChart.animate();

    }

    public void GoBack(View v)  {
        finish();
    }

}