package com.example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.MainActivity;
import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Notificationxmljava extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
   NotifyAdapter notifyAdapter;
    ArrayList<NotifyModelClass> list;
    ImageView notifyback;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationxmljava);

        notifyback=findViewById(R.id.nback);
        recyclerView=findViewById(R.id.notifyrecyle);


        notifyback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Notificationxmljava.this, MainActivity.class);
                startActivity(intent2);
            }
        });

        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        notifyAdapter= new NotifyAdapter(this,list);
        recyclerView.setAdapter(notifyAdapter);
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();
        databaseReference.child("PANCHAYATH NOTIFICATION").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){


                    NotifyModelClass notifyModelClass=dataSnapshot.getValue(NotifyModelClass.class);
                    list.add(notifyModelClass);

                }

                notifyAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "error" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }



        });


    }
}