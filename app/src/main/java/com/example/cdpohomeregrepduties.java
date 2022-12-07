package com.example;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.anganwadi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cdpohomeregrepduties#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cdpohomeregrepduties extends Fragment {
Button panchayath,supervisor,healthInsp;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public cdpohomeregrepduties() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cdpohomeregrepduties.
     */
    // TODO: Rename and change types and number of parameters
    public static cdpohomeregrepduties newInstance(String param1, String param2) {
        cdpohomeregrepduties fragment = new cdpohomeregrepduties();
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
        View v=inflater.inflate(R.layout.fragment_cdpohomeregrepduties, container, false);

        panchayath=v.findViewById(R.id.panchayath);
        supervisor=v.findViewById(R.id.supervisor);
        healthInsp=v.findViewById(R.id.healthinspector);

        panchayath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getActivity(),regispagepanchayath.class);
                startActivity(i);
            }
        });

        supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getActivity(),regispageSupervisor.class);
                startActivity(i);
            }
        });
        healthInsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getActivity(),regispagehealthinspector.class);
                startActivity(i);
            }
        });
        return v;
    }
}