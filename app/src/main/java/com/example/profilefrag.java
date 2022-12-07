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
 * Use the {@link profilefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profilefrag extends Fragment {
    DatabaseReference databaseReference;
    TextView Address,Blockname,Dob,District,Emailid,Name,Phonenumber,Aadharid;
    Button editprofile;
    String nameStr,dobStr;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profilefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profilefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static profilefrag newInstance(String param1, String param2) {
        profilefrag fragment = new profilefrag();
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
        View v=inflater.inflate(R.layout.fragment_profilefrag, container, false);

        Toast.makeText(getActivity(),"hello1",Toast.LENGTH_SHORT).show();
        editprofile = v.findViewById(R.id.editprofile);
        Address = v.findViewById(R.id.proaddress);
        Blockname = v.findViewById(R.id.problock);
        Dob = v.findViewById(R.id.prodob);
        District = v.findViewById(R.id.prodistrict);
        Emailid = v.findViewById(R.id.proemailid);
        Name = v.findViewById(R.id.proname);
       Phonenumber = v.findViewById(R.id.promobileno);
        Aadharid = v.findViewById(R.id.proaadharid);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fra= editprofilefrag.newInstance(nameStr,dobStr);
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction tns= fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout,fra);
                tns.commit();
            }

        });
        databaseReference= FirebaseDatabase.getInstance().getReference().child("CDPO");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String addressStr = snapshot1.child("Address").getValue().toString();
                    String blocknameStr = snapshot1.child("Block Name").getValue().toString();
                    dobStr = snapshot1.child("DOB").getValue().toString();
                    String districtStr = snapshot1.child("District").getValue().toString();
                    String emailStr = snapshot1.child("EmailID").getValue().toString();
                    nameStr = snapshot1.child("Name").getValue().toString();
                    String phonenumberStr = snapshot1.child("Phone Number").getValue().toString();
                    String aadharidStr = snapshot1.child("Aadharid").getValue().toString();



                    Address.setText(addressStr);
                    Blockname.setText(blocknameStr);
                    Dob.setText(dobStr);
                    District.setText(districtStr);
                    Emailid.setText(emailStr);
                    Name.setText(nameStr);
                    Phonenumber.setText(phonenumberStr);
                    Aadharid.setText(aadharidStr);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }
}