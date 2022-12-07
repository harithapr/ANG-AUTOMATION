package com.example;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anganwadi.MainActivity;
import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class memberdetails extends AppCompatActivity {
    Button Submit;
    EditText Membername,Memberid,MemberAadharid,MemberAddress,Memberdob,Memberward,Membermobileno,MemberPanchayath,Memberposition;
    AlertDialog.Builder builder;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberdetails);



        Membername=findViewById(R.id.mname);
        Memberid=findViewById(R.id.mid);
        MemberAadharid=findViewById(R.id.maadharid);
        MemberAddress=findViewById(R.id.mdob);
        Memberdob=findViewById(R.id.mmobileno);
       Memberward=findViewById(R.id.maddress);
        Membermobileno=findViewById(R.id.mmobileno);
        MemberPanchayath=findViewById(R.id.mpanchayath);
        Memberposition=findViewById(R.id.mposition);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

        Submit = findViewById(R.id.msubmit);
        builder = new AlertDialog.Builder(this);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String membernameStr = Membername.getText().toString();
                String memberidStr = Memberid.getText().toString();
                String memberaadharidStr = MemberAadharid.getText().toString();
                String memberaddressStr =MemberAddress.getText().toString();
                String memberdobStr = Memberdob.getText().toString();
                String memberwardStr = Memberward.getText().toString();
                String membermobilenoStr = Membermobileno.getText().toString();
                String memberpanchayathStr = MemberPanchayath.getText().toString();
                String memberpositionStr = Memberposition.getText().toString();


                if (membernameStr.isEmpty() ||memberidStr.isEmpty()||memberaadharidStr.isEmpty()||memberaddressStr.isEmpty()||
                        memberdobStr.isEmpty()||memberwardStr.isEmpty()||membermobilenoStr.isEmpty()||memberpanchayathStr.isEmpty()||
                        memberpositionStr.isEmpty()){

                }else {
                    databaseReference.child("MEMBER DETAILS").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("MEMBER DETAILS").child(memberaadharidStr).child("name").setValue(membernameStr);
                            databaseReference.child("MEMBER DETAILS").child(memberaadharidStr).child("memberid").setValue(memberidStr);
                            databaseReference.child("MEMBER DETAILS").child(memberaadharidStr).child("member address").setValue(memberaddressStr);
                            databaseReference.child("MEMBER DETAILS").child(memberaadharidStr).child("dob").setValue(memberdobStr);
                            databaseReference.child("MEMBER DETAILS").child(memberaadharidStr).child("ward").setValue(memberwardStr);
                            databaseReference.child("MEMBER DETAILS").child(memberaadharidStr).child("mobileno").setValue(membermobilenoStr);
                            databaseReference.child("MEMBER DETAILS").child(memberaadharidStr).child("panchayath").setValue(memberpanchayathStr);
                            databaseReference.child("MEMBER DETAILS").child(memberaadharidStr).child("position").setValue(memberpositionStr);
                            Toast.makeText(memberdetails.this, "Informations are Entered", Toast.LENGTH_SHORT).show();

                            builder.setMessage("Your information successfully registered")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(memberdetails.this, anganteacherhomepage.class);
                                            startActivity(i);
                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("MEMBER DETAILS");
                            alert.show();
                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(memberdetails.this, "Error" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}
