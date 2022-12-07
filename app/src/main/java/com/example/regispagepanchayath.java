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

import com.example.anganwadi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class regispagepanchayath extends AppCompatActivity {
    Button submit;
    EditText PanchayathName,PanchayathId,PanchayathEmail,PanchayathPhonenumber,PanchayathPassword,PanchayathConpwd,PanchayathPlace,PanchayathCity,PanchayathDistrict;
    AlertDialog.Builder builder;
    FirebaseAuth fauth;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regispagepanchayath);

        PanchayathName=findViewById(R.id.panchayathname);
        PanchayathId=findViewById(R.id.panchayathid);
        PanchayathEmail=findViewById(R.id.panchayathemail);
        PanchayathPhonenumber=findViewById(R.id.panchayathphonenumber);
        PanchayathPassword=findViewById(R.id.panchayathpassword);
        PanchayathConpwd=findViewById(R.id.panchayathconfirmpassword);
        PanchayathPlace=findViewById(R.id.panchayathplace);
        PanchayathCity=findViewById(R.id.panchayathcity);
        PanchayathDistrict=findViewById(R.id.panchayathdistrict);


        fauth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

        submit = findViewById(R.id.submit);
        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String panchayathNameStr=PanchayathName.getText().toString();
                String panchayathidStr=PanchayathId.getText().toString();
                String panchayathemailStr=PanchayathEmail.getText().toString();
                String panchayathphonenumberStr=PanchayathPhonenumber.getText().toString();
                String panchayathpasswordStr=PanchayathPassword.getText().toString();
                String panchayathconfirmpwdStr=PanchayathConpwd.getText().toString();
                String panchayathplaceStr=PanchayathPlace.getText().toString();
                String panchayathcityStr=PanchayathCity.getText().toString();
                String panchayathdistrictStr= PanchayathDistrict.getText().toString();

                boolean check = validateinfo(panchayathemailStr,panchayathphonenumberStr,panchayathpasswordStr);
                if (check == true){
                    Toast.makeText(getApplicationContext(), "Data is vaild", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(regispagepanchayath.this, "Soory!! check the information aganin", Toast.LENGTH_SHORT).show();
                }



                if (panchayathNameStr.isEmpty()||panchayathidStr.isEmpty()||panchayathemailStr.isEmpty()
                        ||panchayathphonenumberStr.isEmpty()||panchayathpasswordStr.isEmpty()||
                        panchayathconfirmpwdStr.isEmpty()||panchayathplaceStr.isEmpty()||
                        panchayathcityStr.isEmpty()||panchayathdistrictStr.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter all details",Toast.LENGTH_SHORT).show();
                }else {
                    if (panchayathpasswordStr.equals(panchayathconfirmpwdStr)) {
                             /*fauth.createUserWithEmailAndPassword(panchayathemailStr,panchayathpasswordStr)
                                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                         @Override
                                         public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()){
                                */                databaseReference.child("PANCHAYATH").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        databaseReference.child("PANCHAYATH").child(panchayathidStr).child("Panchayath name").setValue(panchayathNameStr);
                                                        databaseReference.child("PANCHAYATH").child(panchayathidStr).child("Panchayath id").setValue(panchayathidStr);
                                                        databaseReference.child("PANCHAYATH").child(panchayathidStr).child("Panchayath Email").setValue(panchayathemailStr);
                                                        databaseReference.child("PANCHAYATH").child(panchayathidStr).child("Panchayath phonenumber").setValue(panchayathphonenumberStr);
                                                        databaseReference.child("PANCHAYATH").child(panchayathidStr).child("Panchayath Password").setValue(panchayathpasswordStr);
                                                        databaseReference.child("PANCHAYATH").child(panchayathidStr).child("Panchayath Place").setValue(panchayathplaceStr);
                                                        databaseReference.child("PANCHAYATH").child(panchayathidStr).child("Panchayath City").setValue(panchayathcityStr);
                                                        databaseReference.child("PANCHAYATH").child(panchayathidStr).child("Panchayath District").setValue(panchayathdistrictStr);
                                                        Toast.makeText(getApplicationContext(),"User created",Toast.LENGTH_SHORT).show();
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                            }

                                         }
















                builder.setMessage("Your Information Successfully Registered")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(regispagepanchayath.this, hometeacher.class);
                                i.putExtra("Module", "PANCHAYATH");
                                startActivity(i);

                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Registration information");
                alert.show();

            }

            private boolean validateinfo(String panchayathemailStr, String panchayathphonenumberStr, String panchayathpasswordStr) {
                if (panchayathemailStr.length() == 0) {
                    PanchayathEmail.requestFocus();
                    PanchayathEmail.setError("Field cnanot be empty");
                } else if (!panchayathemailStr.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    PanchayathEmail.requestFocus();
                    PanchayathEmail.setError("Enter valid EMAIL");
                    return false;

                } else if (panchayathphonenumberStr.length() == 0) {
                    PanchayathPhonenumber.requestFocus();
                    PanchayathPhonenumber.setError("Field can not be empty");
                    return false;
                } else if (!panchayathphonenumberStr.matches("^[+][0-9]{10,13}$")) {
                    PanchayathPhonenumber.requestFocus();
                    PanchayathPhonenumber.setError("correct format: +91xxxxxxxxxx");
                    return false;
                } else if (panchayathpasswordStr.length() <= 5) {
                    PanchayathPassword.requestFocus();
                    PanchayathPassword.setError("Minimum 6 character required");
                    return false;
                } else if (!panchayathpasswordStr.matches("[a-zA-Z0-9]")) {
                    PanchayathPassword.requestFocus();
                    PanchayathPassword.setError("must use one digit,upper_case,lower_case");
                    return false;
                }
                else{
                    return true;

                }
                return false;
            }


        });
            }
        }

