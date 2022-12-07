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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class regispagehealthinspector extends AppCompatActivity {
    Button submit;
    EditText HealthName, HealthEmail, HealthPhno, HealthDob, HealthAddress, HealthPassword, HealthConfirmpwd, HealthAadharno, HealthTaluk, HealthDistrict, HealthState;
    AlertDialog.Builder builder;
    FirebaseAuth fauth;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regispagehealthinspector);

        HealthName = findViewById(R.id.healthname);
        HealthEmail = findViewById(R.id.healthemail);
        HealthPhno = findViewById(R.id.healthphno);
        HealthDob = findViewById(R.id.healthdob);
        HealthAddress = findViewById(R.id.healthaddress);
        HealthPassword = findViewById(R.id.healthpassword);
        HealthConfirmpwd = findViewById(R.id.healthconfirmpwd);
        HealthAadharno = findViewById(R.id.healthaadharno);
        HealthTaluk = findViewById(R.id.healthtaluk);
        HealthDistrict = findViewById(R.id.healthdistrict);
        HealthState = findViewById(R.id.healthstate);


        fauth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

        submit = findViewById(R.id.submit);
        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String healthNameStr = HealthName.getText().toString();
                String healthemailStr = HealthEmail.getText().toString();
                String healthphnoStr = HealthPhno.getText().toString();
                String healthdobStr = HealthDob.getText().toString();
                String healthaddressStr = HealthAddress.getText().toString();
                String healthpasswordStr = HealthPassword.getText().toString();
                String healthconfirmpwdStr = HealthConfirmpwd.getText().toString();
                String healthaadharnoStr = HealthAadharno.getText().toString();
                String healthtalukStr = HealthTaluk.getText().toString();
                String healthdistrictStr = HealthDistrict.getText().toString();
                String healthstateStr = HealthState.getText().toString();

                boolean check = validateinfo(healthemailStr, healthphnoStr, healthpasswordStr, healthaadharnoStr);

                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Data is vaild", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(regispagehealthinspector.this, "Soory!! check the information aganin", Toast.LENGTH_SHORT).show();
                }

                if (healthNameStr.isEmpty() || healthemailStr.isEmpty() || healthphnoStr.isEmpty() || healthdobStr.isEmpty() || healthaddressStr.isEmpty() ||
                        healthpasswordStr.isEmpty() || healthconfirmpwdStr.isEmpty() || healthaadharnoStr.isEmpty() ||
                        healthtalukStr.isEmpty() || healthdistrictStr.isEmpty() || healthstateStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (healthpasswordStr.equals(healthconfirmpwdStr)) {
                        fauth.createUserWithEmailAndPassword(healthemailStr, healthpasswordStr)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            databaseReference.child("HEALTH INSPECTOR").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector name").setValue(healthNameStr);
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector email").setValue(healthemailStr);
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector phnumber").setValue(healthphnoStr);
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector dob").setValue(healthdobStr);
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector address").setValue(healthaddressStr);
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector password").setValue(healthpasswordStr);
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector confirmpwd").setValue(healthconfirmpwdStr);
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector aadharno").setValue(healthaadharnoStr);
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector taluk").setValue(healthtalukStr);
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector district").setValue(healthdistrictStr);
                                                    databaseReference.child("HEALTH INSPECTOR").child(healthaadharnoStr).child("Health inspector state").setValue(healthstateStr);
                                                    builder.setMessage("Your information successfully registered")
                                                            .setCancelable(false)
                                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    Intent i = new Intent(regispagehealthinspector.this, homepagefrag.class);
                                                                    startActivity(i);
                                                                }
                                                            });

                                                    //Creating dialog box
                                                    AlertDialog alert = builder.create();
                                                    //Setting the title manually
                                                    alert.setTitle("Registration information");
                                                    alert.show();
                                                }


                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error Occured" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            private boolean validateinfo(String healthemailStr, String healthphnoStr, String healthpasswordStr, String healthaadharnoStr) {

                if (healthemailStr.length() == 0) {
                    HealthEmail.requestFocus();
                    HealthEmail.setError("Field cnanot be empty");
                } else if (!healthemailStr.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    HealthEmail.requestFocus();
                    HealthEmail.setError("Enter valid EMAIL");
                    return false;

                } else if (healthphnoStr.length() == 0) {
                    HealthPhno.requestFocus();
                    HealthPhno.setError("Field can not be empty");
                    return false;
                } else if (!healthphnoStr.matches("^[+][0-9]{10,13}$")) {
                    HealthPhno.requestFocus();
                    HealthPhno.setError("correct format: +91xxxxxxxxxx");
                    return false;
                } else if (healthpasswordStr.length() <= 5) {
                    HealthPassword.requestFocus();
                    HealthPassword.setError("Minimum 6 character required");
                    return false;
                } else if (!healthpasswordStr.matches("[a-zA-Z0-9]")) {
                    HealthPassword.requestFocus();
                    HealthPassword.setError("must use one digit,upper_case,lower_case");
                    return false;
                } else {
                    return true;

                }
                return false;
            }

        });
    }
}
