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
 * Use the {@link editprofilefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class editprofilefrag extends Fragment {
    EditText emailid,phonenumber,address,block,district,enteraadharid;
    AlertDialog.Builder builder;
    Button save;

DatabaseReference databaseReference,databaseReference1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String StringName;
    private String Stringdob;

    public editprofilefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment editprofilefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static editprofilefrag newInstance(String nameStr, String dobStr) {
        editprofilefrag fragment = new editprofilefrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, nameStr);
        args.putString(ARG_PARAM2, dobStr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            StringName = getArguments().getString(ARG_PARAM1);
            Stringdob  = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_editprofilefrag, container, false);
       save=v.findViewById(R.id.save);
       emailid=v.findViewById(R.id.e1);
       phonenumber=v.findViewById(R.id.e2);
       address=v.findViewById(R.id.e3);
       block=v.findViewById(R.id.e4);
       district=v.findViewById(R.id.e5);
       enteraadharid=v.findViewById(R.id.e6);


       //  Toast.makeText(getActivity(), StringName, Toast.LENGTH_SHORT).show();
      save.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              String emailidStr = emailid.getText().toString();
              String phonenoStr = phonenumber.getText().toString();
              String AddressStr = address.getText().toString();
              String BlocknameStr = block.getText().toString();
              String DistrictStr = district.getText().toString();
              String enteraadharidStr = enteraadharid.getText().toString();
             databaseReference1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

              Query applesQuery = databaseReference.child("CDPO").child(enteraadharidStr).orderByChild("EmailID").equalTo(emailidStr);
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
                      databaseReference1.child("CDPO").child(enteraadharidStr).child("EmailID").setValue(emailidStr);
                      databaseReference1.child("CDPO").child(enteraadharidStr).child("Phone Number").setValue(phonenoStr);
                      databaseReference1.child("CDPO").child(enteraadharidStr).child("Address").setValue(AddressStr);
                      databaseReference1.child("CDPO").child(enteraadharidStr).child("Block Name").setValue(BlocknameStr);
                      databaseReference1.child("CDPO").child(enteraadharidStr).child("EmailID").setValue(emailidStr);
                      databaseReference1.child("CDPO").child(enteraadharidStr).child("District").setValue(DistrictStr);
                      databaseReference1.child("CDPO").child(enteraadharidStr).child("Name").setValue(StringName);
                      databaseReference1.child("CDPO").child(enteraadharidStr).child("DOB").setValue(Stringdob);
                      Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();

                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {
                      Toast.makeText(getActivity(), "Error"+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                  }
              });

              Fragment fra= homepagefrag.newInstance("dg","gfgyg");
              FragmentManager fragmentManager=getFragmentManager();
              FragmentTransaction tns= fragmentManager.beginTransaction();
              tns.replace(R.id.frame_layout,fra);
              tns.commit();
          }
      });



       databaseReference= FirebaseDatabase.getInstance().getReference("CDPO");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String addressStr = snapshot1.child("Address").getValue().toString();
                    String blocknameStr = snapshot1.child("Block Name").getValue().toString();
                   String Stringdob = snapshot1.child("DOB").getValue().toString();
                    String districtStr = snapshot1.child("District").getValue().toString();
                    String emailStr = snapshot1.child("EmailID").getValue().toString();
                    String StringName = snapshot1.child("Name").getValue().toString();
                    String phonenumberStr = snapshot1.child("Phone Number").getValue().toString();


               address.setText(addressStr);
               block.setText(blocknameStr);
               district.setText(districtStr);
               emailid.setText(emailStr);
               phonenumber.setText(phonenumberStr);


                }


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Enter all details", Toast.LENGTH_SHORT).show();
            }

            });
        return  v;
    }


    }
