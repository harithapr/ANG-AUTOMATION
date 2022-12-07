package com.example;

import android.annotation.SuppressLint;
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

public class viewchildren0to3yearsdetails extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ChildrenMyAdapter childrenMyAdapter;
    ArrayList<ChildrenMyModelClass> list;
    ImageView childrenback;
    SearchView searchView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewchildren0to3yearsdetails);


        childrenback=findViewById(R.id.sback);
        recyclerView=findViewById(R.id.scholarshiprecycle);
        searchView=findViewById(R.id.vaccinesearch);

        childrenback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(viewchildren0to3yearsdetails.this,anganteacherhomepage.class);
                startActivity(intent2);
            }
        });
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        childrenMyAdapter=new ChildrenMyAdapter(this,list);
        recyclerView.setAdapter(childrenMyAdapter);
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();
        databaseReference.child("CHILDRENUPTO0-3Y").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){


                    ChildrenMyModelClass childrenMyModelClass=dataSnapshot.getValue(ChildrenMyModelClass.class);
                    list.add(childrenMyModelClass);

                }

                childrenMyAdapter.notifyDataSetChanged();

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
                ArrayList<ChildrenMyModelClass> filteredlist = new ArrayList<>();

                // running a for loop to compare elements.
                for (ChildrenMyModelClass item : list) {
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
                    childrenMyAdapter.filterList(filteredlist);
                }
                return false;
            }
        });
    }
}