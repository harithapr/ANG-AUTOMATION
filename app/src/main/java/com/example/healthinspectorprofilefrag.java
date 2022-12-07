package com.example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link healthinspectorprofilefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class healthinspectorprofilefrag extends Fragment {
    DatabaseReference databaseReference;
    TextView Aadharno, Address, District, Dob, Email, Name, Phoneno, State, Taluk;
    Button editprofile;
    String NameStr ,DistrictStr;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public healthinspectorprofilefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment healthinspectorprofilefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static healthinspectorprofilefrag newInstance(String param1, String param2) {
        healthinspectorprofilefrag fragment = new healthinspectorprofilefrag();
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
        View v = inflater.inflate(R.layout.fragment_healthinspectorprofilefrag, container, false);
        Toast.makeText(getActivity(), "hello1", Toast.LENGTH_SHORT).show();
       editprofile = v.findViewById(R.id.editprofile);
        Aadharno = v.findViewById(R.id.helaadharid);
        Address = v.findViewById(R.id.heladdress);
        Dob = v.findViewById(R.id.heldob);
        District = v.findViewById(R.id.heldistrict);
        Email = v.findViewById(R.id.helemailid);
        Name = v.findViewById(R.id.helname);
        Phoneno = v.findViewById(R.id.helphno);
        State = v.findViewById(R.id.helstate);
        Taluk = v.findViewById(R.id.heltaluk);

       editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fra = healtheditprofile.newInstance(NameStr,DistrictStr);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction tns = fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout, fra);
                tns.commit();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference().child("HEALTH INSPECTOR");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String aadharnoStr = snapshot1.child("Health inspector aadharno").getValue().toString();
                    String addressStr = snapshot1.child("Health inspector address").getValue().toString();
                    String dobStr = snapshot1.child("Health inspector dob").getValue().toString();
                    String emailStr = snapshot1.child("Health inspector email").getValue().toString();
                    String phonoStr = snapshot1.child("Health inspector phnumber").getValue().toString();
                    String stateStr = snapshot1.child("Health inspector state").getValue().toString();
                    String talukStr = snapshot1.child("Health inspector taluk").getValue().toString();

                    NameStr = snapshot1.child("Health inspector name").getValue().toString();
                    DistrictStr = snapshot1.child("Health inspector district").getValue().toString();


                    Aadharno.setText(aadharnoStr);
                    Address.setText(addressStr);
                    Dob.setText(dobStr);
                    Email.setText(emailStr);
                    Phoneno.setText(phonoStr);
                    State.setText(stateStr);
                    Taluk.setText(talukStr);
                    Name.setText(NameStr);
                    District.setText(DistrictStr);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }
}






