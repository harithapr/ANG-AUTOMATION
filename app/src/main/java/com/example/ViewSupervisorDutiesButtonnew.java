package com.example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

public class ViewSupervisorDutiesButtonnew extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapterSupervisior myAdapter;
    ArrayList<ModelClassSupervisior> list;
    ImageView Supervisorback;
    SearchView searchView;
    EditText editsuperid;
    Button find;
    String editsuperidStr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_supervisor_duties_buttonnew);


        Supervisorback = findViewById(R.id.rback);
        recyclerView = findViewById(R.id.supervisorrecycle);
        searchView = findViewById(R.id.searchView);
        find = findViewById(R.id.find);
        editsuperid = findViewById(R.id.editsuperid);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editsuperidStr = editsuperid.getText().toString();
                databaseReference.child("SUPERVISOR DUTIES").child(editsuperidStr).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataSnapshot : snapshot.get()) {
//
//                            Log.e("#123", String.valueOf(dataSnapshot));
//                            // Toast.makeText(getApplicationContext(), "data snapshot", Toast.LENGTH_SHORT).show();
//                            // String qwe=databaseReference.child("sup300").child("Supervisor Duties").get().toString();
//
//                            // String qwe=modelClassSupervisior.getSupervisor_Duties().toString();
//                            // Toast.makeText(getApplicationContext(),qwe,Toast.LENGTH_SHORT).show();
//                            //list.add(modelClassSupervisior);
//
//                        }
                        list.add(new ModelClassSupervisior((String) snapshot.child("Supervisor Duties").getValue(), (String) snapshot.child("Supervisor Duty Date").getValue(), ""));

                        myAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Error " + error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }


                });
            }
        });
        Supervisorback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ViewSupervisorDutiesButtonnew.this, homepagefrag.class);
                startActivity(intent2);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapterSupervisior(this, list);
        recyclerView.setAdapter(myAdapter);
        // Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();



      /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               ArrayList<ModelClassSupervisior> filteredList = new ArrayList<>();
               for (ModelClassSupervisior item : list) {
                   if (item.getSupervisor_ID().toLowerCase().contains(newText.toLowerCase())) {
                       // if the item is matched we are
                       // adding it to our filtered list.
                       filteredList.add(item);
                   }
               }
               if (filteredList.isEmpty()) {
                   // if no item is added in filtered list we are
                   // displaying a toast message as no data found.
                   Toast.makeText(getApplicationContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
               } else {
                   // at last we are passing that filtered
                   // list to our adapter class.
                   myAdapter.filterList(filteredList);


return false;
           }
       });



        }*/
    }
}

