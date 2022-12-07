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

public class pregnentwomen extends AppCompatActivity {
    Button submit;
    EditText Pregname,Preghusbandname,Pregaadharid,PregDob,PregMobileno,PregAddress,Pregheight,Pregweight,Pregage,PregMonths;
    AlertDialog.Builder builder;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnentwomen);


        Pregname=findViewById(R.id.pname);
        Preghusbandname=findViewById(R.id.phusbandname);
        Pregaadharid=findViewById(R.id.paadharid);
        PregDob=findViewById(R.id.pdob);
        PregMobileno=findViewById(R.id.pmobileno);
        PregAddress=findViewById(R.id.paddress);
        Pregheight=findViewById(R.id.pheight);
        Pregweight=findViewById(R.id.pweight);
        Pregage=findViewById(R.id.page);
        PregMonths=findViewById(R.id.months);


        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

        submit = findViewById(R.id.psubmit);
        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pnameStr = Pregname.getText().toString();
                String phusbandnameStr = Preghusbandname.getText().toString();
                String paadharidStr = Pregaadharid.getText().toString();
                String pdobStr = PregDob.getText().toString();
                String pmobilenoStr = PregMobileno.getText().toString();
                String paddressStr = PregAddress.getText().toString();
                String pheightStr = Pregheight.getText().toString();
                String pweightStr = Pregweight.getText().toString();
                String pageStr = Pregage.getText().toString();
                String pmonthStr = PregMonths.getText().toString();


                if (pnameStr.isEmpty() ||phusbandnameStr.isEmpty()||paadharidStr.isEmpty()||pdobStr.isEmpty()||pmobilenoStr.isEmpty()||
                        paddressStr.isEmpty()||pheightStr.isEmpty()||pweightStr.isEmpty()||pageStr.isEmpty()||pmonthStr.isEmpty()){

                }else {
                    databaseReference.child("PREGNANT WOMEN").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("PREGNANT WOMEN").child(paadharidStr).child("name").setValue(pnameStr);
                            databaseReference.child("PREGNANT WOMEN").child(paadharidStr).child("husbandname").setValue(phusbandnameStr);
                            databaseReference.child("PREGNANT WOMEN").child(paadharidStr).child("aadharid").setValue(paadharidStr);
                            databaseReference.child("PREGNANT WOMEN").child(paadharidStr).child("dob").setValue(pdobStr);
                            databaseReference.child("PREGNANT WOMEN").child(paadharidStr).child("mobileno").setValue(pmobilenoStr);
                            databaseReference.child("PREGNANT WOMEN").child(paadharidStr).child("address").setValue(paddressStr);
                            databaseReference.child("PREGNANT WOMEN").child(paadharidStr).child("height").setValue(pheightStr);
                            databaseReference.child("PREGNANT WOMEN").child(paadharidStr).child("weight").setValue(pweightStr);
                            databaseReference.child("PREGNANT WOMEN").child(paadharidStr).child("age").setValue(pageStr);
                            databaseReference.child("PREGNANT WOMEN").child(paadharidStr).child("month").setValue(pmonthStr);

                            Toast.makeText(pregnentwomen.this, "Informations are Entered", Toast.LENGTH_SHORT).show();

                            builder.setMessage("Your information successfully registered")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(pregnentwomen.this, anganteacherhomepage.class);
                                            startActivity(i);
                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("PREGNANT WOMEN");
                            alert.show();
                        }




                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(pregnentwomen.this, "Error" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}