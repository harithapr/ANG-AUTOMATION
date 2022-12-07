package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.anganwadi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userhomepage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userhomepage extends Fragment {
    String[] activities = {"","food stock details"};

    Button complaints, scholashipschemes, anganwadiactivities;
    Spinner spin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userhomepage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userhomepage.
     */
    // TODO: Rename and change types and number of parameters
    public static userhomepage newInstance(String param1, String param2) {
        userhomepage fragment = new userhomepage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_userhomepage, container, false);


        complaints = v.findViewById(R.id.complaints);
        scholashipschemes = v.findViewById(R.id.scholarshipschemes);
        Spinner spin = v.findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, activities);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin.setAdapter(adapter);

        complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fra = addcomplaints.newInstance("diff", "sidd");
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction tns = fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout, fra);
                tns.commit();
            }
        });
        scholashipschemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), scholarshipschemeviewdetails.class);
                startActivity(i);
            }
        });
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerSelectedItem = spin.getSelectedItem().toString();
                if (spinnerSelectedItem.equals("food stock details")) {
                    Intent i = new Intent(getActivity(), com.example.foodstockdetails.class);
                    startActivity(i);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        return v;
    }

}