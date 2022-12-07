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

import com.example.anganwadi.MainActivity;
import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodStockDetailsViewNew extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FoodAdapter foodAdapter;
    ArrayList<FoodMyModel> list;
    ImageView foodback;
    SearchView searchView;
    EditText editsuperid;
    Button find;
    String editsuperidStr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_stock_details_view_new);


        foodback = findViewById(R.id.rback);
        recyclerView = findViewById(R.id.foodierecycle);
        find = findViewById(R.id.find);
        editsuperid = findViewById(R.id.editsuperid);


        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editsuperidStr = editsuperid.getText().toString();
                databaseReference.child("FOODSTOCKDETAILS").child(editsuperidStr).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.add(new FoodMyModel((String) snapshot.child("date").getValue(), (String) snapshot.child("grains").getValue(),
                                (String) snapshot.child("otherfood").getValue(), (String) snapshot.child("rice").getValue()));
                        foodAdapter.notifyDataSetChanged();
                        Log.d(TAG, "111"+list.get(0));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Error " + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

       foodback.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent2 = new Intent(FoodStockDetailsViewNew.this, MainActivity.class);
               startActivity(intent2);
           }
       });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        foodAdapter = new FoodAdapter(this, list);
        recyclerView.setAdapter(foodAdapter);
    }
}