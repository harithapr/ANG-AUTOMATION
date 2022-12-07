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

public class regispageSupervisor extends AppCompatActivity {
    Button submit;
    EditText SupName, SupEmail, SupPhno, SupDob, SupAddress, SupPwd, SupConfirmpwd, SupAadharno, SupPlace, SupDistrict, SupState, SupPanchayathname, Supid;
    AlertDialog.Builder builder;
    FirebaseAuth fauth;
    DatabaseReference databaseReference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regispage_supervisor);

        SupName = findViewById(R.id.supname);
        SupEmail = findViewById(R.id.supemail);
        SupPhno = findViewById(R.id.supphno);
        SupDob = findViewById(R.id.supdob);
        SupAddress = findViewById(R.id.supaddress);
        SupPwd = findViewById(R.id.suppwd);
        SupConfirmpwd = findViewById(R.id.supconfirmpwd);
        SupAadharno = findViewById(R.id.supaadharno);
        SupPlace = findViewById(R.id.supplace);
        SupDistrict = findViewById(R.id.supdistrict);
        SupState = findViewById(R.id.supstate);
        SupPanchayathname = findViewById(R.id.suppanchayathname);
        Supid = findViewById(R.id.supid);


        fauth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");


        submit = findViewById(R.id.submit);
        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String SupervisorNameStr = SupName.getText().toString();
                String SupervisoremailStr = SupEmail.getText().toString();
                String SupervisorphnoStr = SupPhno.getText().toString();
                String SupervisordobrStr = SupDob.getText().toString();
                String SupervisoraddressStr = SupAddress.getText().toString();
                String SupervisorpwdStr = SupPwd.getText().toString();
                String SupervisorconfirmpwdStr = SupConfirmpwd.getText().toString();
                String SupervisoraadharnoStr = SupAadharno.getText().toString();
                String SupervisorplaceStr = SupPlace.getText().toString();
                String SupervisordistrictStr = SupDistrict.getText().toString();
                String SupervisorstateStr = SupState.getText().toString();
                String SupervisorpanchayathnameStr = SupPanchayathname.getText().toString();
                String SupervisoridStr = Supid.getText().toString();


                boolean check = validateinfo(SupervisoremailStr,SupervisorphnoStr,SupervisorpwdStr,SupervisoraadharnoStr);
                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Data is vaild", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(regispageSupervisor.this, "Soory!! check the information aganin", Toast.LENGTH_SHORT).show();
                }


                if (SupervisorNameStr.isEmpty() || SupervisoremailStr.isEmpty() || SupervisorphnoStr.isEmpty() ||
                        SupervisordobrStr.isEmpty() || SupervisoraddressStr.isEmpty() || SupervisorpwdStr.isEmpty() ||
                        SupervisorconfirmpwdStr.isEmpty() || SupervisoraadharnoStr.isEmpty() ||
                        SupervisorplaceStr.isEmpty() || SupervisordistrictStr.isEmpty() ||
                        SupervisorstateStr.isEmpty() || SupervisorpanchayathnameStr.isEmpty() || SupervisoridStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (SupervisorpwdStr.equals(SupervisorconfirmpwdStr)) {
                        fauth.createUserWithEmailAndPassword(SupervisoremailStr, SupervisorpwdStr)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {


                                            databaseReference.child("SUPERVISOR").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor name").setValue(SupervisorNameStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor email").setValue(SupervisoremailStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor phno").setValue(SupervisorphnoStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor dob").setValue(SupervisordobrStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor address").setValue(SupervisoraddressStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor pwd").setValue(SupervisorpwdStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor confirmpwd").setValue(SupervisorconfirmpwdStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor aadharno").setValue(SupervisoraadharnoStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor place").setValue(SupervisorplaceStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor district").setValue(SupervisordistrictStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor state").setValue(SupervisorstateStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor panchayathname").setValue(SupervisorpanchayathnameStr);
                                                    databaseReference.child("SUPERVISOR").child(SupervisoraadharnoStr).child("Supervisor id").setValue(SupervisoridStr);
                                                    builder.setMessage("Your information successfully registered")
                                                            .setCancelable(false)
                                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    Intent i = new Intent(regispageSupervisor.this, homepagefrag.class);
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

            private boolean validateinfo(String supervisoremailStr, String supervisorphnoStr, String supervisorpwdStr, String supervisoraadharnoStr) {
                if (supervisoremailStr.length() == 0) {
                    SupEmail.requestFocus();
                    SupEmail.setError("Field cnanot be empty");
                } else if (!supervisoremailStr.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    SupEmail.requestFocus();
                    SupEmail.setError("Enter valid EMAIL");
                    return false;

                } else if (supervisorphnoStr.length() == 0) {
                    SupPhno.requestFocus();
                    SupPhno.setError("Field can not be empty");
                    return false;
                } else if (!supervisorphnoStr.matches("^[+][0-9]{10,13}$")) {
                    SupPhno.requestFocus();
                    SupPhno.setError("correct format: +91xxxxxxxxxx");
                    return false;
                } else if (supervisorpwdStr.length() <= 5) {
                    SupPwd.requestFocus();
                    SupPwd.setError("Minimum 6 character required");
                    return false;
                } else if (!supervisorpwdStr.matches("[a-zA-Z0-9]")) {
                      SupPwd.requestFocus();
                    SupPwd.setError("must use one digit,upper_case,lower_case");

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


