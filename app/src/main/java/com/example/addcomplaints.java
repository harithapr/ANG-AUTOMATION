package com.example;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.anganwadi.MainActivity;
import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addcomplaints#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addcomplaints extends Fragment {
    Button ok;
    EditText complaints,id,name,address;
    AlertDialog.Builder builder;
    DatabaseReference databaseReference;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addcomplaints() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addcomplaints.
     */
    // TODO: Rename and change types and number of parameters
    public static addcomplaints newInstance(String param1, String param2) {
        addcomplaints fragment = new addcomplaints();
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
       View v= inflater.inflate(R.layout.fragment_addcomplaints, container, false);

       complaints=v.findViewById(R.id.complaints);
       name=v.findViewById(R.id.idcomplaintname);
        address=v.findViewById(R.id.idcomplaintaddress);
       id=v.findViewById(R.id.idcomplaint);
       ok=v.findViewById(R.id.okbutton);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        builder = new AlertDialog.Builder(getActivity());
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String complaintStr = complaints.getText().toString();
                String idStr=id.getText().toString();
                String nameStr=name.getText().toString();
                String addressStr=address.getText().toString();

                if (complaintStr.isEmpty()||idStr.isEmpty()||nameStr.isEmpty()||addressStr.isEmpty()){

                }else{
                    databaseReference.child("STUDENT COMPLAINTS").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("STUDENT COMPLAINTS").child(idStr).child("Complaintid").setValue(idStr);
                            databaseReference.child("STUDENT COMPLAINTS").child(idStr).child("Complaintername").setValue(nameStr);
                            databaseReference.child("STUDENT COMPLAINTS").child(idStr).child("Complainteraddress").setValue(addressStr);
                            databaseReference.child("STUDENT COMPLAINTS").child(idStr).child("Complaints").setValue(complaintStr);
                            Toast.makeText(getActivity(), "complaints are added", Toast.LENGTH_SHORT).show();
                            builder.setMessage("Your complaints succesfully added")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(getActivity(), userhomepage.class);
                                            startActivity(i);
                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("Complaints");
                            alert.show();
                        }




                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        return v;
    }
}