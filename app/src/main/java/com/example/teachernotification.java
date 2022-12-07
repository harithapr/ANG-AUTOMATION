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

public class teachernotification extends AppCompatActivity {
    Button submit;
    EditText Tsentby,Ttitle,Tdate,Tvenue,Ttime;
    AlertDialog.Builder builder;

    DatabaseReference databaseReference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachernotification);

        submit=findViewById(R.id.submitbutton);
        Tsentby=findViewById(R.id.tsentbY);
        Ttitle=findViewById(R.id.ttitlE);
        Tdate=findViewById(R.id.tdatE);
        Tvenue=findViewById(R.id.tvenuE);
        Ttime=findViewById(R.id.ttime);

        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TsentbyStr = Tsentby.getText().toString();
                String TtitleStr = Ttitle.getText().toString();
                String TdateStr = Tdate.getText().toString();
                String TvenueStr = Tvenue.getText().toString();
                String TtimeStr = Ttime.getText().toString();



                if (TsentbyStr.isEmpty() || TtitleStr.isEmpty()|| TdateStr.isEmpty()||TvenueStr.isEmpty()|| TtimeStr.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();
                }else{
                    databaseReference.child("TEACHER NOTIFICATION").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("TEACHER NOTIFICATION").child(TtitleStr).child("Sent by").setValue(TsentbyStr);
                            databaseReference.child("TEACHER NOTIFICATION").child(TtitleStr).child("Date").setValue(TdateStr);
                            databaseReference.child("TEACHER NOTIFICATION").child(TtitleStr).child("Venue").setValue(TvenueStr);
                            databaseReference.child("TEACHER NOTIFICATION").child(TtitleStr).child("Time").setValue(TtimeStr);

                            builder.setMessage("Notification Send to the Users ")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(com.example.teachernotification.this,anganteacherhomepage.class);
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
