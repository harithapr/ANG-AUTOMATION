package com.example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.anganwadi.R;
import com.example.anganwadi.cdporeports;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link panchayathhomepage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class panchayathhomepage extends Fragment{
            String[] audit={"select","student details","View food stock details"};

    Button supduties,supreports,scholarship,auditing;
    Spinner spin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public panchayathhomepage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment panchayathhomepage.
     */
    // TODO: Rename and change types and number of parameters
    public static panchayathhomepage newInstance(String param1, String param2) {
        panchayathhomepage fragment = new panchayathhomepage();
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


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_panchayathhomepage, container, false);

        supreports = v.findViewById(R.id.rep);
        supduties = v.findViewById(R.id.sup);
        scholarship = v.findViewById(R.id.scholar);
        spin =  v.findViewById(R.id.spinner);
       // auditing = v.findViewById(R.id.audit);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item,audit);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin.setAdapter(adapter);



        supreports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), cdporeports.class);
                startActivity(i);
            }
        });
        scholarship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), scholarship.class);
                startActivity(i);
            }
        });
        supduties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),ViewSupervisorDutiesButtonnew.class);
                startActivity(i);
            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerSelectedItem = spin.getSelectedItem().toString();
                Toast.makeText(getActivity() ,spinnerSelectedItem,Toast.LENGTH_SHORT).show();
                if (spinnerSelectedItem.equals("student details")){
                    Intent i = new Intent(getActivity(),com.example.Studentdeatailsxmljava.class);
                   // Toast.makeText(getActivity() ,"fdgfhhj",Toast.LENGTH_SHORT).show();
                    startActivity(i);


                }else if (spinnerSelectedItem.equals("View food stock details")){
                    Intent i = new Intent(getActivity(),Foodstockdetailviewuser.class);
                    //Toast.makeText(getActivity() ,"fdgfhhj",Toast.LENGTH_SHORT).show();
                    startActivity(i);

                }/*else if (spinnerSelectedItem.equals("student attendance")){
                    Intent i = new Intent(getActivity(),studentattendance.class);
                    startActivity(i);

                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }



        }