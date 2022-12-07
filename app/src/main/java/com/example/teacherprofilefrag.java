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
 * Use the {@link teacherprofilefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class teacherprofilefrag extends Fragment {
    DatabaseReference databaseReference;
    TextView Name,Emailid,Phonenumber,Dob,Address,Anganwadiname,Aadharno,Ward,Village,District,Panchayath;
    Button editprofile;
    String nameStr,dobStr;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public teacherprofilefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment teacherprofilefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static teacherprofilefrag newInstance(String param1, String param2) {
        teacherprofilefrag fragment = new teacherprofilefrag();
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
        View v=inflater.inflate(R.layout.fragment_teacherprofilefrag, container, false);

        Toast.makeText(getActivity(),"hello1",Toast.LENGTH_SHORT).show();
        editprofile = v.findViewById(R.id.editprofile);
        Name=v.findViewById(R.id.tname);
        Emailid=v.findViewById(R.id.temailid);
        Phonenumber=v.findViewById(R.id.tmobileno);
        Dob=v.findViewById(R.id.tdob);
        Aadharno=v.findViewById(R.id.taadharno);
        Address=v.findViewById(R.id.taddress);
        Anganwadiname=v.findViewById(R.id.tanganwadiname);
        Ward=v.findViewById(R.id.tward);
        Village=v.findViewById(R.id.tvillage);
        District=v.findViewById(R.id.tdistrict);
        Panchayath=v.findViewById(R.id.tpanchayath);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fra= teachereditprofile.newInstance(nameStr,dobStr);
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction tns= fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout,fra);
                tns.commit();
            }
        });
        databaseReference= FirebaseDatabase.getInstance().getReference("TEACHER");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String nameStr = snapshot1.child("Name").getValue().toString();
                    String emailStr = snapshot1.child("Email").getValue().toString();
                    String phnoStr = snapshot1.child("Phoneno").getValue().toString();
                    String dobStr = snapshot1.child("Dob").getValue().toString();
                    String addressStr = snapshot1.child("Address").getValue().toString();
                    String aadharidStr = snapshot1.child("Aadharid").getValue().toString();
                    String anganwadinameStr = snapshot1.child("Anganwadiname").getValue().toString();
                    String wardStr = snapshot1.child("Ward").getValue().toString();
                    String villageStr = snapshot1.child("Village").getValue().toString();
                    String districtStr = snapshot1.child("District").getValue().toString();
                    String panchayathStr = snapshot1.child("Panchayath").getValue().toString();

                    Name.setText(nameStr);
                    Emailid.setText(emailStr);
                    Phonenumber.setText(phnoStr);
                    Dob.setText(dobStr);
                    Address.setText(addressStr);
                    Aadharno.setText(aadharidStr);
                    Anganwadiname.setText(anganwadinameStr);
                    Ward.setText(wardStr);
                    Village.setText(villageStr);
                    District.setText(districtStr);
                    Panchayath.setText(panchayathStr);


            }
        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            });


        return  v;

    }
}