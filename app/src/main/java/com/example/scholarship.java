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

import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class scholarship extends AppCompatActivity {
    Button create;
    EditText Schemeid,Schemename,Schemetype,Schemegrade,Requireddoc,timelimit;
    AlertDialog.Builder builder;
    DatabaseReference databaseReference;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship);

        Schemeid=findViewById(R.id.shid);
        Schemename=findViewById(R.id.shname);
        Schemetype=findViewById(R.id.shtype);
        Schemegrade=findViewById(R.id.shgrade);
        Requireddoc=findViewById(R.id.shdoc);
        timelimit=findViewById(R.id.shlimit);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        create= findViewById(R.id.shcreate);
        builder = new AlertDialog.Builder(this);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String schemeidStr = Schemeid.getText().toString();
                String schemenameStr = Schemename.getText().toString();
                String schemetypeStr = Schemetype.getText().toString();
                String schemegradeStr = Schemegrade.getText().toString();
                String requireddocStr = Requireddoc.getText().toString();
                String timelimitStr = timelimit.getText().toString();

                if (schemeidStr.isEmpty()||schemenameStr.isEmpty()||schemetypeStr.isEmpty()||
                schemegradeStr.isEmpty()||requireddocStr.isEmpty()||timelimitStr.isEmpty()){

                }else {
                    databaseReference.child("SCHOLARSHIP DETAILS").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("SCHOLARSHIP DETAILS").child(schemeidStr).child("Schemeid").setValue(schemeidStr);
                            databaseReference.child("SCHOLARSHIP DETAILS").child(schemeidStr).child("Schemename").setValue(schemenameStr);
                            databaseReference.child("SCHOLARSHIP DETAILS").child(schemeidStr).child("Schemetype").setValue(schemetypeStr);
                            databaseReference.child("SCHOLARSHIP DETAILS").child(schemeidStr).child("Schemegrade").setValue(schemegradeStr);
                            databaseReference.child("SCHOLARSHIP DETAILS").child(schemeidStr).child("Requireddoc").setValue(requireddocStr);
                            databaseReference.child("SCHOLARSHIP DETAILS").child(schemeidStr).child("Timelimit").setValue(timelimitStr);
                            Toast.makeText(scholarship.this, "Informations are Entered", Toast.LENGTH_SHORT).show();
                            builder.setMessage("Scholarship Details Successfully Entered")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(scholarship.this, panchayathhomepage.class);
                                            startActivity(i);
                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("SCHOLARSHIP DETAILS");
                            alert.show();
                        }




                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(scholarship.this, "Error" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }
}