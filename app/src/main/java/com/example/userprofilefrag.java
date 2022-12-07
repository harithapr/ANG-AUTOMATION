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
 * Use the {@link userprofilefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userprofilefrag extends Fragment {
    DatabaseReference databaseReference;
    TextView name,fathername,mothername,email,dob,address,aadharno,village,ward,anganwadiid,panchayath,bloodgroup,birthplace;
    Button editprofile;
    String nameStr,dobStr;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userprofilefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userprofilefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static userprofilefrag newInstance(String param1, String param2) {
        userprofilefrag fragment = new userprofilefrag();
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
        View v= inflater.inflate(R.layout.fragment_userprofilefrag, container, false);


        Toast.makeText(getActivity(),"hello1",Toast.LENGTH_SHORT).show();
        editprofile=v.findViewById(R.id.editprofile);
        name=v.findViewById(R.id.uname);
        fathername=v.findViewById(R.id.ufathername);
        mothername=v.findViewById(R.id.umothername);
        email=v.findViewById(R.id.uemail);
        dob=v.findViewById(R.id.udob);
        address=v.findViewById(R.id.uaddress);
        aadharno=v.findViewById(R.id.uaadharno);
        village=v.findViewById(R.id.uvillage);
        ward=v.findViewById(R.id.uward);
        panchayath=v.findViewById(R.id.upanchayath);
        bloodgroup=v.findViewById(R.id.ubloodgroup);
        birthplace=v.findViewById(R.id.ubirthplace);
        anganwadiid=v.findViewById(R.id.anganwadiid);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fra= usereditprofile.newInstance(nameStr,dobStr);
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction tns= fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout,fra);
                tns.commit();
            }
        });

        databaseReference= FirebaseDatabase.getInstance().getReference().child("STUDENT");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String nameStr = snapshot1.child("Name").getValue().toString();
                    String fathernameStr = snapshot1.child("fathername").getValue().toString();
                    String mothernameStr = snapshot1.child("mothername").getValue().toString();
                    String emailStr = snapshot1.child("email").getValue().toString();
                    String dobStr = snapshot1.child("dob").getValue().toString();
                    String addressStr = snapshot1.child("address").getValue().toString();
                    String aadharnoStr = snapshot1.child("aadharno").getValue().toString();
                    String villageStr = snapshot1.child("village").getValue().toString();
                    String wardStr = snapshot1.child("ward").getValue().toString();
                    String panchayathStr = snapshot1.child("panchayath").getValue().toString();
                    String bloodgroupStr = snapshot1.child("bloodgroup").getValue().toString();
                    String birthplaceStr = snapshot1.child("birthplace").getValue().toString();
                    String anganwadiStr = snapshot1.child("AnganwadiId").getValue().toString();

                    name.setText(nameStr);
                    fathername.setText(fathernameStr);
                    mothername.setText(mothernameStr);
                    email.setText(emailStr);
                    dob.setText(dobStr);
                    address.setText(addressStr);
                    aadharno.setText(aadharnoStr);
                    village.setText(villageStr);
                    ward.setText(wardStr);
                    panchayath.setText(panchayathStr);
                    bloodgroup.setText(bloodgroupStr);
                    birthplace.setText(birthplaceStr);
                    anganwadiid.setText(anganwadiStr);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


            });

        return v;
    }
}