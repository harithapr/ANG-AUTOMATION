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

import com.example.anganwadi.MainActivity;
import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Foodstockdetailviewuser extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FoodStockMyAdapter foodStockMyAdapter;
    ArrayList<FoodStockMyModel> list;
    ImageView foodback;
    SearchView searchView;
    EditText editsuperid;
    Button find;
    String editsuperidStr;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodstockdetailviewuser);

        foodback=findViewById(R.id.fback);

        recyclerView=findViewById(R.id.foodstockrecycle);
        //searchView=findViewById(R.id.searchView);
        find=findViewById(R.id.find);
        editsuperid=findViewById(R.id.anganid);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //   Toast.makeText(getApplicationContext(), "1"+editsuperidStr, Toast.LENGTH_SHORT).show();
                editsuperidStr = editsuperid.getText().toString();
                databaseReference.child("FOOD STOCK DETAILS").child(editsuperidStr).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       String str= databaseReference.child("date").get().toString();
                       Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
                     /*   for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Log.e("#123", String.valueOf(dataSnapshot));
                            // String qwe=databaseReference.child("sup300").child("Supervisor Duties").get().toString();

                           /*  String qwe=modelClassSupervisior.getSupervisor_Duties().toString();
                             Toast.makeText(getApplicationContext(),qwe,Toast.LENGTH_SHORT).show();
                            list.add(modelClassSupervisior);
                     }*/
                      //  Toast.makeText(getApplicationContext(), snapshot.child("date").getValue().toString(),Toast.LENGTH_SHORT).show();
                        list.add(new FoodStockMyModel((String) databaseReference.child("FOOD STOCK DETAILS").child(editsuperidStr).child("date").get().toString(),(String) databaseReference.child("grains").get().toString(),
                                (String) databaseReference.child("otherfood").get().toString(),(String) databaseReference.child("rice").get().toString()));

                        foodStockMyAdapter.notifyDataSetChanged();

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
                Intent intent2 = new Intent(Foodstockdetailviewuser.this, MainActivity.class);
                startActivity(intent2);
            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        foodStockMyAdapter= new FoodStockMyAdapter(this,list);
        recyclerView.setAdapter(foodStockMyAdapter);

    }
}
