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
 * Use the {@link supervisorprofilefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class supervisorprofilefrag extends Fragment {
    DatabaseReference databaseReference;
    TextView aadharno,address,district,dob,email,supervisorid,name,panchayathname,phoneno,place,state;
    Button editprofile;
    String nameStr,dobStr;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public supervisorprofilefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment supervisorprofilefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static supervisorprofilefrag newInstance(String param1, String param2) {
        supervisorprofilefrag fragment = new supervisorprofilefrag();
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
        View v= inflater.inflate(R.layout.fragment_supervisorprofilefrag, container, false);


        Toast.makeText(getActivity(),"hello1",Toast.LENGTH_SHORT).show();
        editprofile = v.findViewById(R.id.editprofile);
        name = v.findViewById(R.id.ssname);
        email = v.findViewById(R.id.ssemail);
        phoneno = v.findViewById(R.id.ssphno);
        dob = v.findViewById(R.id.ssdob);
        aadharno = v.findViewById(R.id.ssaadharid);
        address = v.findViewById(R.id.ssaddress);
        place = v.findViewById(R.id.ssplace);
        district = v.findViewById(R.id.ssdistrict);
        state = v.findViewById(R.id.ssstate);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fra= Supervisoreditprofilefrag.newInstance(nameStr,dobStr);
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction tns= fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout,fra);
                tns.commit();
            }

        });
        databaseReference= FirebaseDatabase.getInstance().getReference().child("SUPERVISOR");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                     nameStr = snapshot1.child("Supervisor name").getValue().toString();
                    String emailidStr = snapshot1.child("Supervisor email").getValue().toString();
                    String phnoStr = snapshot1.child("Supervisor phno").getValue().toString();
                     dobStr = snapshot1.child("Supervisor dob").getValue().toString();
                    String aadharidStr = snapshot1.child("Supervisor aadharno").getValue().toString();
                    String addressStr = snapshot1.child("Supervisor address").getValue().toString();
                    String placeStr = snapshot1.child("Supervisor place").getValue().toString();
                    String districtStr = snapshot1.child("Supervisor district").getValue().toString();
                    String stateStr = snapshot1.child("Supervisor state").getValue().toString();
                    address.setText(addressStr);
                    name.setText(nameStr);
                    email.setText(emailidStr);
                    phoneno.setText(phnoStr);
                    dob.setText(dobStr);
                    aadharno.setText(aadharidStr);
                    address.setText(addressStr);
                    place.setText(placeStr);
                    district.setText(districtStr);
                    state.setText(stateStr);


            }


        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }


            });
        return  v;
    }
}