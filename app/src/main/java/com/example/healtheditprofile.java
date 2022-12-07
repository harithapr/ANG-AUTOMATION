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
 * Use the {@link healtheditprofile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class healtheditprofile extends Fragment {
    EditText emailid,mobileno,address,taluk;
    AlertDialog.Builder builder;
    Button save;
    DatabaseReference databaseReference,databaseReference1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String StringName;
    private String  StringDistrict;

    public healtheditprofile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment healtheditprofile.
     */
    // TODO: Rename and change types and number of parameters
    public static healtheditprofile newInstance(String nameStr, String districtStr) {
        healtheditprofile fragment = new healtheditprofile();
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
        View v= inflater.inflate(R.layout.fragment_healtheditprofile, container, false);

        save =v.findViewById(R.id.pasave);
        emailid=v.findViewById(R.id.pa1);
        mobileno=v.findViewById(R.id.pa2);
        address=v.findViewById(R.id.pa3);
        taluk=v.findViewById(R.id.pa4);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailidStr = emailid.getText().toString();
                String mobilenoStr = mobileno.getText().toString();
                String addressStr = address.getText().toString();
                String talukStr = taluk.getText().toString();

                databaseReference1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

                Query applesQuery = databaseReference.child("HEALTH INSPECTOR").child(talukStr).orderByChild("Health inspector email").equalTo(emailidStr);
                ((Query) applesQuery).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot appleSnapshot : snapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                        Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(), "Error" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference1.child("HEALTH INSPECTOR").child(talukStr).child("Health inspector email").setValue(emailidStr);
                        databaseReference1.child("HEALTH INSPECTOR").child(talukStr).child("Health inspector address").setValue(addressStr);
                        databaseReference1.child("HEALTH INSPECTOR").child(talukStr).child("Health inspector phnumber").setValue(mobilenoStr);
                        databaseReference1.child("HEALTH INSPECTOR").child(talukStr).child("Health inspector taluk").setValue(talukStr);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(), "Error"+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                Fragment fra= healthinspectorhomepagefrag.newInstance("dg","gfgyg");
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction tns= fragmentManager.beginTransaction();
                tns.replace(R.id.frame_layout,fra);
                tns.commit();
            }
        });
        databaseReference=FirebaseDatabase.getInstance().getReference("HEALTH INSPECTOR");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String emailStr = snapshot1.child("Health inspector email").getValue().toString();
                    String addressStr = snapshot1.child("Health inspector address").getValue().toString();
                    String mobilenoStr = snapshot1.child("Health inspector phnumber").getValue().toString();


                    emailid.setText(emailStr);
                    address.setText(addressStr);
                    mobileno.setText(mobilenoStr);
            }


        }
        @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                return v;
    }
}