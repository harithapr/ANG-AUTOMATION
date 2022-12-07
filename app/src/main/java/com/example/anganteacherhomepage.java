package com.example;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.anganwadi.R;
import com.example.anganwadi.cdporeports;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link anganteacherhomepage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class anganteacherhomepage extends Fragment {
    Button dailytracking, beneficiaries, studentreg, memberdetails;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public anganteacherhomepage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment anganteacherhomepage.
     */
    // TODO: Rename and change types and number of parameters
    public static anganteacherhomepage newInstance(String param1, String param2) {
        anganteacherhomepage fragment = new anganteacherhomepage();
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
        View v = inflater.inflate(R.layout.fragment_anganteacherhomepage, container, false);
      dailytracking = v.findViewById(R.id.dailytracking);
        beneficiaries = v.findViewById(R.id.beneficiaries);
        studentreg = v.findViewById(R.id.studreg);
        memberdetails = v.findViewById(R.id.memberdetails);


        dailytracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fra= com.example.dailytracking.newInstance("diff","sidd");
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction tns= fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout,fra);
                tns.commit();

            }
        });
        beneficiaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fra= com.example.beneficiaries.newInstance("diff","sidd");
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction tns= fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout,fra);
                tns.commit();

            }
        });
        studentreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), regispagestudent.class);
                startActivity(i);
            }
        });
        memberdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), memberdetails.class);
                startActivity(i);
            }
        });

        return v;
    }
}