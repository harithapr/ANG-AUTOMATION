package com.example;

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

public class children0to3y extends AppCompatActivity {
    Button Submit;
    EditText name,age,address,mothername,fathername,ward,place,id;
    AlertDialog.Builder builder;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children0to3y);


        name=findViewById(R.id.cname);
        age=findViewById(R.id.cage);
        address=findViewById(R.id.caddress);
        mothername=findViewById(R.id.cmothername);
        fathername=findViewById(R.id.cfathername);
        ward=findViewById(R.id.cward);
        place=findViewById(R.id.cplace);
        id=findViewById(R.id.cid);

        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        Submit = findViewById(R.id.submit);
        builder = new AlertDialog.Builder(this);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String childnameStr = name.getText().toString();
                String childageStr = age.getText().toString();
                String childaddressStr = address.getText().toString();
                String childmothernameStr = mothername.getText().toString();
                String childfathernameStr = fathername.getText().toString();
                String childwardStr = ward.getText().toString();
                String childplaceStr = place.getText().toString();
                String childidStr = id.getText().toString();

                if (childnameStr.isEmpty()||childageStr.isEmpty()||childaddressStr.isEmpty()||childmothernameStr.isEmpty()||
                childfathernameStr.isEmpty()||childwardStr.isEmpty()||childplaceStr.isEmpty()||childidStr.isEmpty()){

                }else {
                    databaseReference.child("CHILDREN0TO3Y").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("CHILDREN0TO3Y").child(childidStr).child("Name").setValue(childnameStr);
                            databaseReference.child("CHILDREN0TO3Y").child(childidStr).child("Age").setValue(childageStr);
                            databaseReference.child("CHILDREN0TO3Y").child(childidStr).child("Address").setValue(childaddressStr);
                            databaseReference.child("CHILDREN0TO3Y").child(childidStr).child("Mothername").setValue(childmothernameStr);
                            databaseReference.child("CHILDREN0TO3Y").child(childidStr).child("Fathername").setValue(childfathernameStr);
                            databaseReference.child("CHILDREN0TO3Y").child(childidStr).child("Ward").setValue(childwardStr);
                            databaseReference.child("CHILDREN0TO3Y").child(childidStr).child("Place").setValue(childplaceStr);
                            databaseReference.child("CHILDREN0TO3Y").child(childidStr).child("Id").setValue(childidStr);
                            Toast.makeText(children0to3y.this, "Informations are Entered", Toast.LENGTH_SHORT).show();

                            builder.setMessage("Your information successfully registered")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(children0to3y.this, anganteacherhomepage.class);
                                            startActivity(i);
                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("CHILDRENOTO3Y");
                            alert.show();
                        }




                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(children0to3y.this, "Error" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}