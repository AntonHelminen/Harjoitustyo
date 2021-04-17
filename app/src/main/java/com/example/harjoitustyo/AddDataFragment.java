package com.example.harjoitustyo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AddDataFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Button button;
    ScrollView scrollview, scrollview2, scrollview3, scrollview4, scrollview5, scrollview6, scrollview7, scrollview8, scrollview9;
    Spinner spinner, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8, spinner9;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* Making ScrollViews and Spinners to create DropDownMenus */
        scrollview = (ScrollView) getView().findViewById(R.id.scrollView);
        spinner = (Spinner) getView().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        scrollview2 = (ScrollView) getView().findViewById(R.id.scrollView2);
        spinner2 = (Spinner) getView().findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        scrollview3 = (ScrollView) getView().findViewById(R.id.scrollView3);
        spinner3 = (Spinner) getView().findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);

        scrollview4 = (ScrollView) getView().findViewById(R.id.scrollView4);
        spinner4 = (Spinner) getView().findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(this);

        scrollview5 = (ScrollView) getView().findViewById(R.id.scrollView5);
        spinner5 = (Spinner) getView().findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(this);

        scrollview6 = (ScrollView) getView().findViewById(R.id.scrollView6);
        spinner6 = (Spinner) getView().findViewById(R.id.spinner6);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);
        spinner6.setOnItemSelectedListener(this);

        scrollview7 = (ScrollView) getView().findViewById(R.id.scrollView7);
        spinner7 = (Spinner) getView().findViewById(R.id.spinner7);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);
        spinner7.setOnItemSelectedListener(this);

        scrollview8 = (ScrollView) getView().findViewById(R.id.scrollView8);
        spinner8 = (Spinner) getView().findViewById(R.id.spinner8);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter8);
        spinner8.setOnItemSelectedListener(this);

        scrollview9 = (ScrollView) getView().findViewById(R.id.scrollView9);
        spinner9 = (Spinner) getView().findViewById(R.id.spinner9);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(getActivity(), R.array.Choice_amount, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner9.setAdapter(adapter9);
        spinner9.setOnItemSelectedListener(this);

        button = (Button) getView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()    {
            @Override
            public void onClick(View v) {

                API_reader api_reader = API_reader.getInstance();

                /* Setting values */
                api_reader.setBioWaste(spinner.getSelectedItem().toString());
                api_reader.setCarton(spinner2.getSelectedItem().toString());
                api_reader.setElectronic(spinner3.getSelectedItem().toString());
                api_reader.setGlass(spinner4.getSelectedItem().toString());
                api_reader.setHazardous(spinner5.getSelectedItem().toString());
                api_reader.setMetal(spinner6.getSelectedItem().toString());
                api_reader.setPaper(spinner7.getSelectedItem().toString());
                api_reader.setPlastic(spinner8.getSelectedItem().toString());
                api_reader.setEstimate(spinner9.getSelectedItem().toString());

                api_reader.calculate();

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentWindow, new ViewDataFragment());
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}