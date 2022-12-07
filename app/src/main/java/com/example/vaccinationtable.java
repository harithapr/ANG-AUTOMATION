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

public class vaccinationtable extends AppCompatActivity {
    Button Submit;
    EditText name,address,vaccinename,age,anganwadiname,panchayath,Id;
    AlertDialog.Builder builder;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinationtable);

        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        vaccinename=findViewById(R.id.vaccine);
        age=findViewById(R.id.age);
        anganwadiname=findViewById(R.id.anganwadiname);
        panchayath=findViewById(R.id.panchayath);
        Id=findViewById(R.id.id);


        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

        Submit=findViewById(R.id.submit);
        builder = new AlertDialog.Builder(this);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString();
                String addressStr = address.getText().toString();
                String vaccinenameStr = vaccinename.getText().toString();
                String ageStr = age.getText().toString();
                String anganwadinameStr = anganwadiname.getText().toString();
                String panchayathStr = panchayath.getText().toString();
                String idStr = Id.getText().toString();


                if (nameStr.isEmpty()||addressStr.isEmpty()||vaccinenameStr.isEmpty()||ageStr.isEmpty()||
                        anganwadinameStr.isEmpty()||panchayathStr.isEmpty()||idStr.isEmpty()){

                }else {
                    databaseReference.child("VACCINATION").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("VACCINATION").child(idStr).child("name").setValue(nameStr);
                            databaseReference.child("VACCINATION").child(idStr).child("address").setValue(addressStr);
                            databaseReference.child("VACCINATION").child(idStr).child("vaccinename").setValue(vaccinenameStr);
                            databaseReference.child("VACCINATION").child(idStr).child("age").setValue(ageStr);
                            databaseReference.child("VACCINATION").child(idStr).child("anganwadiname").setValue(anganwadinameStr);
                            databaseReference.child("VACCINATION").child(idStr).child("panchayath").setValue(panchayathStr);
                            databaseReference.child("VACCINATION").child(idStr).child("id").setValue(idStr);
                            Toast.makeText(vaccinationtable.this, "Informations are Entered", Toast.LENGTH_SHORT).show();

                            builder.setMessage("Vaccine Details Registered")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(vaccinationtable.this, healthinspectorhomepagefrag.class);
                                            startActivity(i);
                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("Vaccine Registration");
                            alert.show();
                        }





                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(vaccinationtable.this, "Error" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}