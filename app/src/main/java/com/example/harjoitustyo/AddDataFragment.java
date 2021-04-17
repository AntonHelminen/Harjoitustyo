package com.example.harjoitustyo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddDataFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    ScrollView scrollview;
    Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scrollview = (ScrollView) getView().findViewById(R.id.scrollView);
        spinner = (Spinner) getView().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        scrollview = (ScrollView) getView().findViewById(R.id.scrollView2);
        spinner = (Spinner) getView().findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(this);

        scrollview = (ScrollView) getView().findViewById(R.id.scrollView3);
        spinner = (Spinner) getView().findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter3);
        spinner.setOnItemSelectedListener(this);

        scrollview = (ScrollView) getView().findViewById(R.id.scrollView4);
        spinner = (Spinner) getView().findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter4);
        spinner.setOnItemSelectedListener(this);

        scrollview = (ScrollView) getView().findViewById(R.id.scrollView5);
        spinner = (Spinner) getView().findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter5);
        spinner.setOnItemSelectedListener(this);

        scrollview = (ScrollView) getView().findViewById(R.id.scrollView6);
        spinner = (Spinner) getView().findViewById(R.id.spinner6);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter6);
        spinner.setOnItemSelectedListener(this);

        scrollview = (ScrollView) getView().findViewById(R.id.scrollView7);
        spinner = (Spinner) getView().findViewById(R.id.spinner7);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter7);
        spinner.setOnItemSelectedListener(this);

        scrollview = (ScrollView) getView().findViewById(R.id.scrollView8);
        spinner = (Spinner) getView().findViewById(R.id.spinner8);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter8);
        spinner.setOnItemSelectedListener(this);

        scrollview = (ScrollView) getView().findViewById(R.id.scrollView9);
        spinner = (Spinner) getView().findViewById(R.id.spinner9);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice_amount, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter9);
        spinner.setOnItemSelectedListener(this);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}