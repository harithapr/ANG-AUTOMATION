package com.example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.anganwadi.R;
import com.example.anganwadi.cdporeports;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link beneficiaries#newInstance} factory method to
 * create an instance of this fragment.
 */
public class beneficiaries extends Fragment {
    Button pregnentwomen,children;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public beneficiaries() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment beneficiaries.
     */
    // TODO: Rename and change types and number of parameters
    public static beneficiaries newInstance(String param1, String param2) {
        beneficiaries fragment = new beneficiaries();
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
        View v =inflater.inflate(R.layout.fragment_beneficiaries, container, false);

       pregnentwomen=v.findViewById(R.id.pregnentwomen);
       children=v.findViewById(R.id.children0m3y);

       pregnentwomen.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(), pregnentwomen.class);
               startActivity(i);
           }
       });
       children.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(), children0to3y.class);
               startActivity(i);
           }
       });


        return v;
    }
}