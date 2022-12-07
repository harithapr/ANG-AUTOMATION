package com.example;

import android.annotation.SuppressLint;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link panchayatheditprofilefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class panchayatheditprofilefrag extends Fragment {
    EditText emailid,mobileno,panchayathid,city,place;
    AlertDialog.Builder builder;
    Button save;

    DatabaseReference databaseReference,databaseReference1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String StringName;
    private String StringDistrict;

    public panchayatheditprofilefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment panchayatheditprofilefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static panchayatheditprofilefrag newInstance(String nameStr, String districtStr) {
        panchayatheditprofilefrag fragment = new panchayatheditprofilefrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, nameStr);
        args.putString(ARG_PARAM2, districtStr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            StringName = getArguments().getString(ARG_PARAM1);
            StringDistrict = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_panchayatheditprofilefrag, container, false);


        save =v.findViewById(R.id.pasave);
        emailid=v.findViewById(R.id.pa1);
        mobileno=v.findViewById(R.id.pa2);
        panchayathid=v.findViewById(R.id.pa3);
        city=v.findViewById(R.id.pa4);
        place=v.findViewById(R.id.pa5);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailidStr = emailid.getText().toString();
                String mobilenoStr = mobileno.getText().toString();
                String panchayathidStr = panchayathid.getText().toString();
                String cityStr = city.getText().toString();
                String placeStr = place.getText().toString();

                databaseReference1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

                Query applesQuery = databaseReference.child("PANCHAYATH").child(panchayathidStr).orderByChild("Panchayath Email").equalTo(emailidStr);
                ((Query) applesQuery).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot appleSnapshot: snapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                        Toast.makeText(getActivity(),"Deleted",Toast.LENGTH_SHORT).show();
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(),"Error"+error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
                databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference1.child("PANCHAYATH").child(panchayathidStr).child("Panchayath Email").setValue(emailidStr);
                        databaseReference1.child("PANCHAYATH").child(panchayathidStr).child("Panchayath phonenumber").setValue(mobilenoStr);
                      //  databaseReference1.child("PANCHAYATH").child(panchayathidStr).child("Panchayath id").setValue(panchayathidStr);
                        databaseReference1.child("PANCHAYATH").child(panchayathidStr).child("Panchayath City").setValue(cityStr);
                        databaseReference1.child("PANCHAYATH").child(panchayathidStr).child("Panchayath Place").setValue(placeStr);
                        databaseReference1.child("PANCHAYATH").child(panchayathidStr).child("Panchayath name").setValue(StringName);
                        databaseReference1.child("PANCHAYATH").child(panchayathidStr).child("Panchayath District").setValue(StringDistrict);
                        Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(), "Error"+error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                });

                Fragment fra= panchayathhomepage.newInstance("dg","gfgyg");
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction tns= fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout,fra);
                tns.commit();
            }
        });

        databaseReference=FirebaseDatabase.getInstance().getReference("PANCHAYATH");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String cityStr = snapshot1.child("Panchayath City").getValue().toString();
                    String emailStr = snapshot1.child("Panchayath Email").getValue().toString();
                    String placeStr = snapshot1.child("Panchayath Place").getValue().toString();
                   // String idStr = snapshot1.child("Panchayath id").getValue().toString();
                    String phonenumberStr = snapshot1.child("Panchayath phonenumber").getValue().toString();
                    String StringName = snapshot1.child("Panchayath name").getValue().toString();
                    String StringDistrict = snapshot1.child("Panchayath District").getValue().toString();

                    city.setText(cityStr);
                    place.setText(placeStr);
                  // panchayathid.setText(idStr);
                    emailid.setText(emailStr);
                    mobileno.setText(phonenumberStr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



            });

        return  v;
    }
}