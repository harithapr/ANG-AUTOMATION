package com.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class healthinspectornotification extends AppCompatActivity {
    Button submit;
    EditText Hsentby,Htitle,Hdate,Hvenue,Htime;
    AlertDialog.Builder builder;

    DatabaseReference databaseReference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthinspectornotification);

        submit=findViewById(R.id.submitbutton);
        Hsentby=findViewById(R.id.hsentbY);
        Htitle=findViewById(R.id.htype);
        Hdate=findViewById(R.id.hdatE);
        Hvenue=findViewById(R.id.hvenuE);
        Htime=findViewById(R.id.htime);

        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HsentbyStr = Hsentby.getText().toString();
                String HtitleStr = Htitle.getText().toString();
                String HdateStr = Hdate.getText().toString();
                String HvenueStr = Hvenue.getText().toString();
                String HtimeStr = Htime.getText().toString();



                if (HsentbyStr.isEmpty() || HtitleStr.isEmpty()|| HdateStr.isEmpty()||HvenueStr.isEmpty()|| HtimeStr.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();
                }else{
                    databaseReference.child("HEALTH INSPECTOR NOTIFICATION").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("HEALTH INSPECTOR NOTIFICATION").child(HtitleStr).child("Sent by").setValue(HsentbyStr);
                            databaseReference.child("HEALTH INSPECTOR NOTIFICATION").child(HtitleStr).child("Date").setValue(HdateStr);
                            databaseReference.child("HEALTH INSPECTOR NOTIFICATION").child(HtitleStr).child("Venue").setValue(HvenueStr);
                            databaseReference.child("HEALTH INSPECTOR NOTIFICATION").child(HtitleStr).child("Time").setValue(HtimeStr);

                            builder.setMessage("Notification Send to the Users,Teachers,Supervisors ")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(com.example.healthinspectornotification.this,healthinspectorhomepagefrag.class);
                                            startActivity(i);
                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("Notification");
                            alert.show();
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
