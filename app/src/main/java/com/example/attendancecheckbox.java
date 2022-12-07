package com.example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class attendancecheckbox extends AppCompatActivity implements AttendanceMyAdapter.AdapterCallback{

    RecyclerView recyclerView;
    DatabaseReference databaseReference,databaseReference1,databaseReference2;
    AttendanceMyAdapter attendanceMyAdapter;
    ArrayList<AttendanceMyModelClass> list;
    ArrayList<String> presentList,absentList;
    Button Submit;

    //FINAL VARIABLES
    public static final String DATE_FORMAT = "yyyy-MM-dd";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendancecheckbox);
        Submit = findViewById(R.id.submit);

        recyclerView = findViewById(R.id.recycleattendance);
        databaseReference = FirebaseDatabase.getInstance().getReference("STUDENT");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Attendance");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        attendanceMyAdapter = new AttendanceMyAdapter(attendancecheckbox.this, list, this);
        recyclerView.setAdapter(attendanceMyAdapter);

        presentList = new ArrayList<>();
        absentList = new ArrayList<>();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference1.child("Attendence").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference1.child("Attendence").child(dateToString()).child("Present").setValue(presentList);
                        Toast.makeText(attendancecheckbox.this, "Attendance id recorded", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Error present List" + error.getMessage().toString(), Toast.LENGTH_SHORT);

                    }
                });


            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    AttendanceMyModelClass attendanceMyModelClass = dataSnapshot.getValue(AttendanceMyModelClass.class);
                    list.add(attendanceMyModelClass);


                }
                attendanceMyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error loading data" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
       /* databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    AttendanceMyModelClass attendanceMyModelClass = dataSnapshot.getValue(AttendanceMyModelClass.class);
                    list.add(attendanceMyModelClass);


                }
                attendanceMyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error"+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });*/
    }




    public String dateToString() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateToString = dateFormat.format( calendar.getTime());
        return dateToString;
    }


    @Override
    public void addToPresentList(String name) {
        presentList.add(name);
        Log.d("presentList",presentList.get(0));
    }
}