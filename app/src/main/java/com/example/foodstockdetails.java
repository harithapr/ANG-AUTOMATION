package com.example;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anganwadi.MainActivity;
import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class foodstockdetails extends AppCompatActivity {
    Button Submit;
    EditText date,anganwadiid, rice, grains, otherfoods;
    AlertDialog.Builder builder;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodstockdetails);

        date = findViewById(R.id.fdate);
        anganwadiid = findViewById(R.id.fanganid);
        rice = findViewById(R.id.frice);
        grains = findViewById(R.id.fgrains);
        otherfoods = findViewById(R.id.fotherfoods);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        Submit = findViewById(R.id.fsubmit);
        builder = new AlertDialog.Builder(this);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateStr = date.getText().toString();
                String anganwadiidStr = anganwadiid.getText().toString();
                String riceidStr = rice.getText().toString();
                String grainsStr = grains.getText().toString();
                String otherfoodsStr = otherfoods.getText().toString();

                if (dateStr.isEmpty() ||anganwadiidStr.isEmpty()|| riceidStr.isEmpty() || grainsStr.isEmpty() || otherfoodsStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();

                } else {
                    databaseReference.child("FOODSTOCKDETAILS").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            databaseReference.child("FOODSTOCKDETAILS ").child(anganwadiidStr).child("date").setValue(dateStr);
                            //databaseReference.child("FOOD STOCK DETAILS ").child(anganwadiidStr).child("Anganwadiid").setValue(anganwadiidStr);
                            databaseReference.child("FOODSTOCKDETAILS ").child(anganwadiidStr).child("rice").setValue(riceidStr);
                            databaseReference.child("FOODSTOCKDETAILS ").child(anganwadiidStr).child("grains").setValue(grainsStr);
                            databaseReference.child("FOODSTOCKDETAILS ").child(anganwadiidStr).child("otherfood").setValue(otherfoodsStr);
                            Toast.makeText(foodstockdetails.this, "Informations are Entered", Toast.LENGTH_SHORT).show();

                            builder.setMessage("Your Food Details Added")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(foodstockdetails.this, anganteacherhomepage.class);
                                            startActivity(i);
                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("FOOD STOCK DETAILS");
                            alert.show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(foodstockdetails.this, "Error" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();


                        }
                    });
                }
            }
        });
    }
}