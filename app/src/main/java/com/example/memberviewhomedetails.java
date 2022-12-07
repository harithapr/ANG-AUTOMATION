package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
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

import java.util.ArrayList;

public class memberviewhomedetails extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
   MemberMyAdapter myAdapter;
    ArrayList<memberModelclass> list;
    ImageView Memberback;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberviewhomedetails);

        Memberback=findViewById(R.id.rback);
        recyclerView=findViewById(R.id.memberecycle);
        searchView=findViewById(R.id.searchView);

        Memberback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(memberviewhomedetails.this,anganteacherhomepage.class);
                startActivity(intent2);
            }
        });

        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        myAdapter= new MemberMyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();
        databaseReference.child("MEMBER DETAILS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){


                    memberModelclass memberModelclass2=dataSnapshot.getValue(memberModelclass.class);
                    list.add(memberModelclass2);

                }

                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "error"+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }



        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // creating a new array list to filter our data.
                ArrayList<memberModelclass> filteredlist = new ArrayList<>();

                // running a for loop to compare elements.
                for (memberModelclass item : list) {
                    // checking if the entered string matched with any item of our recycler view.
                    if (item.getName().toLowerCase().contains(newText.toLowerCase())) {
                        // if the item is matched we are
                        // adding it to our filtered list.
                        filteredlist.add(item);
                    }
                }
                if (filteredlist.isEmpty()) {
                    // if no item is added in filtered list we are
                    // displaying a toast message as no data found.
                    Toast.makeText(getApplicationContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
                } else {
                    // at last we are passing that filtered
                    // list to our adapter class.
                    myAdapter.filterList(filteredlist);
                }
                return false;
            }
        });
    }
}