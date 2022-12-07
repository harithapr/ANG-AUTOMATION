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

public class regispageteacher extends AppCompatActivity {
    Button submit;
    EditText TeacherName,TeacherEmail,TeacherPhno,TeacherDob,TeacherAddress,TeacherPwd,TeacherConfirmpwd
            ,TeacherAadharno,TeacherAnganwadiname,
            TeacherWard,TeacherVillage,TeacherDistrict,TeacherPanchayath;
    AlertDialog.Builder builder;
    FirebaseAuth fauth;
    DatabaseReference databaseReference;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regispageteacher);

        TeacherName=findViewById(R.id.teachername);
        TeacherEmail=findViewById(R.id.teacheremail);
        TeacherPhno=findViewById(R.id.teacherphno);
        TeacherDob=findViewById(R.id.teacherdob);
        TeacherAddress=findViewById(R.id.teacheraddress);
        TeacherPwd=findViewById(R.id.teacherpwd);
        TeacherConfirmpwd=findViewById(R.id.teacherconfirmpwd);
        TeacherAadharno=findViewById(R.id.teacheraadharno);
       TeacherAnganwadiname=findViewById(R.id.teacheranganwadiname);
       TeacherWard=findViewById(R.id.teacherward);
        TeacherVillage=findViewById(R.id.teachervillage);
        TeacherDistrict=findViewById(R.id.teacherdistrict);
        TeacherPanchayath=findViewById(R.id.teacherpanchayath);
        submit=findViewById(R.id.submitT);

        fauth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");


        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teachernameStr = TeacherName.getText().toString();
                String teacheremailidStr = TeacherEmail.getText().toString();
                String teacherphonenoStr = TeacherPhno.getText().toString();
                String teacherDobStr = TeacherDob.getText().toString();
                String teacherAddressStr = TeacherAddress.getText().toString();
                String teacherPasswordStr = TeacherPwd.getText().toString();
                String teacherConfirmpasswordStr = TeacherConfirmpwd.getText().toString();
                String teacherAdharnumberStr = TeacherAadharno.getText().toString();
                String teacheranganwadinameStr = TeacherAnganwadiname.getText().toString();
                String teacherwardStr = TeacherWard.getText().toString();
                String teachervillageStr = TeacherVillage.getText().toString();
                String teacherDistrictStr = TeacherDistrict.getText().toString();
                String teacherpanchayathStr = TeacherPanchayath.getText().toString();

                boolean check = validateinfo(teacheremailidStr,teacherphonenoStr,teacherPasswordStr,teacherAdharnumberStr);
                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Data is vaild", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(regispageteacher.this, "Soory!! check the information aganin", Toast.LENGTH_SHORT).show();
                }
                if (teachernameStr.isEmpty() ||teacheremailidStr.isEmpty()||teacherphonenoStr.isEmpty()||teacherDobStr.isEmpty()||
                        teacherAddressStr.isEmpty()||teacherPasswordStr.isEmpty()||teacherConfirmpasswordStr.isEmpty()||
                        teacherAdharnumberStr.isEmpty()||teacheranganwadinameStr.isEmpty()||teacherwardStr.isEmpty()||
                        teachervillageStr.isEmpty()||teacherDistrictStr.isEmpty()||teacherpanchayathStr.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (teacherPasswordStr.equals(teacherConfirmpasswordStr)) {

                        fauth.createUserWithEmailAndPassword(teacheremailidStr, teacherPasswordStr)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            databaseReference.child("TEACHER").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Name").setValue(teachernameStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Email").setValue(teacheremailidStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Phoneno").setValue(teacherphonenoStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Dob").setValue(teacherDobStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Address").setValue(teacherAddressStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Password").setValue(teacherPasswordStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Confirmpassword").setValue(teacherConfirmpasswordStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Aadharid").setValue(teacherAdharnumberStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Anganwadiname").setValue(teacheranganwadinameStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Ward").setValue(teacherwardStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Village").setValue(teachervillageStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("District").setValue(teacherDistrictStr);
                                                    databaseReference.child("TEACHER").child(teacherAdharnumberStr).child("Panchayath").setValue(teacherpanchayathStr);
                                                    builder.setMessage("Your information successfully registered")
                                                            .setCancelable(false)
                                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    Intent i = new Intent(com.example.regispageteacher.this, MainActivity.class);
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

            private boolean validateinfo(String teacheremailidStr, String teacherphonenoStr, String teacherPasswordStr, String teacherAdharnumberStr) {
                if (teacheremailidStr.length() == 0) {
                    TeacherEmail.requestFocus();
                    TeacherEmail.setError("Field cnanot be empty");
                } else if (!teacheremailidStr.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    TeacherEmail.requestFocus();
                    TeacherEmail.setError("Enter valid EMAIL");
                    return false;

                } else if (teacherphonenoStr.length() == 0) {
                    TeacherPhno.requestFocus();
                    TeacherPhno.setError("Field can not be empty");
                    return false;
                } else if (!teacherphonenoStr.matches("^[+][0-9]{10,13}$")) {
                    TeacherPhno.requestFocus();
                    TeacherPhno.setError("correct format: +91xxxxxxxxxx");
                    return false;
                } else if (teacherPasswordStr.length() <= 5) {
                    TeacherPwd.requestFocus();
                    TeacherPwd.setError("Minimum 6 character required");
                    return false;
                } else if (!teacherPasswordStr.matches("[a-zA-Z0-9]")) {
                    TeacherPwd.requestFocus();
                    TeacherPwd.setError("must use one digit,upper_case,lower_case");
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