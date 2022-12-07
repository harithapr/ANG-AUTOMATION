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

public class supervisordutiesbutton extends AppCompatActivity {
    Button submit;
    EditText Dutydate, SupervisorId, Duties;
    AlertDialog.Builder builder;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisordutiesbutton);


        Dutydate = findViewById(R.id.dutydate);
        SupervisorId = findViewById(R.id.supervisorid);
        Duties = findViewById(R.id.duties);


        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

        submit = findViewById(R.id.dutiesbutton);
        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String DutydateStr = Dutydate.getText().toString();
                String SupIdStr = SupervisorId.getText().toString();
                String DutiesStr = Duties.getText().toString();

                if (DutydateStr.isEmpty() || SupIdStr.isEmpty() || DutiesStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();
                } else {


                    databaseReference.child("SUPERVISOR DUTIES").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                           // databaseReference.child("SUPERVISOR DUTIES").child(SupIdStr).child("Supervisor ID").setValue(SupIdStr);
                            databaseReference.child("SUPERVISOR DUTIES").child(SupIdStr).child("Supervisor Duty Date").setValue(DutydateStr);
                            databaseReference.child("SUPERVISOR DUTIES").child(SupIdStr).child("Supervisor Duties").setValue(DutiesStr);

                            Toast.makeText(supervisordutiesbutton.this, "Informations are Entered", Toast.LENGTH_SHORT).show();

                            builder.setMessage("Your information successfully registered")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(supervisordutiesbutton.this, homepagefrag.class);
                                            startActivity(i);
                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("Uploading Supervisor Duties");
                            alert.show();
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(supervisordutiesbutton.this, "Error" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    }

