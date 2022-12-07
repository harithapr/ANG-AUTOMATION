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
import com.example.anganwadi.supervisorsduties;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homepagefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homepagefrag extends Fragment {
    //declare button
    Button Register,Duties,Reports;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homepagefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homepagefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static homepagefrag newInstance(String param1, String param2) {
        homepagefrag fragment = new homepagefrag();
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
        View v=inflater.inflate(R.layout.fragment_homepagefrag, container, false);
        //initialise button=v.findViewById(R.id.dfsfs);

        Register = v.findViewById(R.id.reg);
        Duties = v.findViewById(R.id.sup);
        Reports = v.findViewById(R.id.rep);
        //butt.setOnclicklistene onclick
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loaDFRAGMent cdpo homeregduties
                Fragment fra=cdpohomeregrepduties.newInstance("diff","sidd");
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction tns= fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout,fra);
                tns.commit();

            }
        });
        Duties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load supervisorduties fragment
                Fragment fra= supervisorsduties.newInstance("diff","sidd");
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction tns= fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout,fra);
                tns.commit();
            }
        });
        Reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to activity from fragment cdpoReports activity
                Intent i = new Intent(getActivity(), cdporeports.class);
                startActivity(i);
            }
        });
       // Intent intent = new Intent(getActivity(), MainActivity.class);
        //startActivity(intent);
        return v;
    }
}