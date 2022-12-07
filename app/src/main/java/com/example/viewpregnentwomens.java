package com.example;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class viewpregnentwomens extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    PregnentWomenMyAdapter pregnentWomenMyAdapter;
    ArrayList<PregnentWomenMyModel>  list;
    ImageView Womenback;
    SearchView searchView;
    EditText editsuperid;
    Button find;
    String editsuperidStr;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpregnentwomens);

        Womenback = findViewById(R.id.gback);
        recyclerView = findViewById(R.id.pregrecycle);
        //  searchView=findViewById(R.id.pregsearchView);
        find = findViewById(R.id.find);
        editsuperid = findViewById(R.id.editsuperid);


        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editsuperidStr = editsuperid.getText().toString();
                databaseReference.child("PREGNANT WOMEN").child(editsuperidStr).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.add(new PregnentWomenMyModel((String) snapshot.child("aadharid").getValue(),
                                (String) snapshot.child("address").getValue(), (String) snapshot.child("age").getValue(),
                                (String) snapshot.child("dob").getValue(), (String) snapshot.child("height").getValue(),
                                (String) snapshot.child("husbandname").getValue(), (String) snapshot.child("mobileno").getValue(),
                                (String) snapshot.child("month").getValue(), (String) snapshot.child("name").getValue(),(String) snapshot.child("weight").getValue()));
                   pregnentWomenMyAdapter.notifyDataSetChanged();
                        Log.d(TAG, "111"+list.get(0));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Error " + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Womenback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(viewpregnentwomens.this, anganteacherhomepage.class);
                startActivity(intent2);
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        pregnentWomenMyAdapter = new PregnentWomenMyAdapter(this, list);
        recyclerView.setAdapter(pregnentWomenMyAdapter);
       // Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();
        /*databaseReference.child("PREGNENT WOMENS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                    PregnentWomenMyModel pregnentWomenMyModel = dataSnapshot.getValue(PregnentWomenMyModel.class);
                    list.add(pregnentWomenMyModel);

                }
                pregnentWomenMyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "error" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<PregnentWomenMyModel> filteredlist = new ArrayList<>();

                // running a for loop to compare elements.
                for (PregnentWomenMyModel item : list) {
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
                    pregnentWomenMyAdapter.filterList(filteredlist);
                }
                return false;
            }
        });
    }*/
    }
}