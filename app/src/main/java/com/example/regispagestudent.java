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

public class regispagestudent extends AppCompatActivity {
    Button submit;
    EditText studentName,studentfathername,studentmothername,studentdob,studentaddress,studentbirthplace,studentaadharno
            ,studentbloodgroup,studentpassword,studentconpassword,studentdistrict,studentvillage,studentpanchayath,studentward,studentemail,studanganwadiid;
    AlertDialog.Builder builder;
    FirebaseAuth fauth;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regispagestudent);



        studentName=findViewById(R.id.studname);
        studentfathername=findViewById(R.id.studfathername);
        studentemail=findViewById(R.id.studemail);
        studentmothername=findViewById(R.id.studmothername);
        studentdob=findViewById(R.id.studdob);
        studentpassword=findViewById(R.id.studpwd);
        studentconpassword=findViewById(R.id.studconfirmpwd);
        studentaddress=findViewById(R.id.studaddress);
       studentaadharno=findViewById(R.id.studaadharno);
        studentvillage=findViewById(R.id.studvillage);
        studentward=findViewById(R.id.studward);
        studentdistrict=findViewById(R.id.studdistrict);
        studentpanchayath=findViewById(R.id.studpanchayath);
        studentbloodgroup=findViewById(R.id.studbloodgroup);
        studentbirthplace=findViewById(R.id.studbirthplace);
        studanganwadiid=findViewById(R.id.Anganwadiid);



        fauth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

        submit = findViewById(R.id.submit);
        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String studnamestr=studentName.getText().toString();
                String studfathernameStr=studentfathername.getText().toString();
                String studmothernameStr=studentmothername.getText().toString();
                String studdobStr=studentdob.getText().toString();
                String studpwdStr=studentpassword.getText().toString();
                String studconfirmpwdStr=studentconpassword.getText().toString();
                String studaadharnoStr=studentaadharno.getText().toString();
                String studvillageStr=studentvillage.getText().toString();
                String studaddressStr= studentaddress.getText().toString();
                String studwardStr=studentward.getText().toString();
                String studdistrictStr=studentdistrict.getText().toString();
                String studpanchayathStr=studentpanchayath.getText().toString();
                String studbirthplaceStr=studentbirthplace.getText().toString();
                String studbloodgroupStr=studentbloodgroup.getText().toString();
                String studemailStr=studentemail.getText().toString();
                String studanganwadiStr=studanganwadiid.getText().toString();

                boolean check = validateinfo(studemailStr,studaadharnoStr,studpwdStr);
                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Data is vaild", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(regispagestudent.this, "Soory!! check the information aganin", Toast.LENGTH_SHORT).show();
                }


                if (studnamestr.isEmpty()||studfathernameStr.isEmpty()||studmothernameStr.isEmpty()||studdobStr.isEmpty()
                    ||studpwdStr.isEmpty()||studconfirmpwdStr.isEmpty()||studaadharnoStr.isEmpty()||studvillageStr.isEmpty()||
                        studaddressStr.isEmpty()||studwardStr.isEmpty()||studdistrictStr.isEmpty()||studpanchayathStr.isEmpty()||
                        studbirthplaceStr.isEmpty()||studbloodgroupStr.isEmpty()||studemailStr.isEmpty()||studanganwadiStr.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (studpwdStr.equals(studconfirmpwdStr)) {
                        fauth.createUserWithEmailAndPassword( studemailStr,studpwdStr)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    databaseReference.child("STUDENT").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("Name").setValue(studnamestr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("fathername").setValue(studfathernameStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("mothername").setValue(studmothernameStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("email").setValue(studemailStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("dob").setValue(studdobStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("address").setValue(studaddressStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("aadharno").setValue(studaadharnoStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("village").setValue(studvillageStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("ward").setValue(studwardStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("panchayath").setValue(studpanchayathStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("bloodgroup").setValue(studbloodgroupStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("birthplace").setValue(studbirthplaceStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("password").setValue(studpwdStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("confirmpassword").setValue(studconfirmpwdStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("AnganwadiId").setValue(studanganwadiStr);

/*
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("Name").setValue(studnamestr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("fathername").setValue(studfathernameStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("mothername").setValue(studmothernameStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("email").setValue(studemailStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("dob").setValue(studdobStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("address").setValue(studaddressStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("aadharno").setValue(studaadharnoStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("village").setValue(studvillageStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("ward").setValue(studwardStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("panchayath").setValue(studpanchayathStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("bloodgroup").setValue(studbloodgroupStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("birthplace").setValue(studbirthplaceStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("password").setValue(studpwdStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("confirmpassword").setValue(studconfirmpwdStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child(studnamestr).child("AnganwadiId").setValue(studanganwadiStr);
*/
                                            builder.setMessage("Your information successfully registered")
                                                    .setCancelable(false)
                                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            Intent i = new Intent(regispagestudent.this, anganteacherhomepage.class);
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
                                    databaseReference.child("STUDENT1").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                             /*               databaseReference.child("STUDENT").child(studanganwadiStr).child("Name").setValue(studnamestr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("fathername").setValue(studfathernameStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("mothername").setValue(studmothernameStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("email").setValue(studemailStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("dob").setValue(studdobStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("address").setValue(studaddressStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("aadharno").setValue(studaadharnoStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("village").setValue(studvillageStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("ward").setValue(studwardStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("panchayath").setValue(studpanchayathStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("bloodgroup").setValue(studbloodgroupStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("birthplace").setValue(studbirthplaceStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("password").setValue(studpwdStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("confirmpassword").setValue(studconfirmpwdStr);
                                            databaseReference.child("STUDENT").child(studanganwadiStr).child("AnganwadiId").setValue(studanganwadiStr);

*/
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("Name").setValue(studnamestr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("fathername").setValue(studfathernameStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("mothername").setValue(studmothernameStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("email").setValue(studemailStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("dob").setValue(studdobStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("address").setValue(studaddressStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("aadharno").setValue(studaadharnoStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("village").setValue(studvillageStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("ward").setValue(studwardStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("panchayath").setValue(studpanchayathStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("bloodgroup").setValue(studbloodgroupStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("birthplace").setValue(studbirthplaceStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("password").setValue(studpwdStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("confirmpassword").setValue(studconfirmpwdStr);
                                            databaseReference.child("STUDENT1").child(studanganwadiStr).child(studnamestr).child("AnganwadiId").setValue(studanganwadiStr);

                                            builder.setMessage("Your information successfully registered")
                                                    .setCancelable(false)
                                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            Intent i = new Intent(regispagestudent.this, anganteacherhomepage.class);
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

            private boolean validateinfo(String studemailStr, String studaadharnoStr, String studpwdStr) {
                if (studemailStr.length() == 0) {
                    studentemail.requestFocus();
                    studentemail.setError("Field cnanot be empty");
                } else if (!studemailStr.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    studentemail.requestFocus();
                    studentemail.setError("Enter valid EMAIL");
                    return false;

               /* } else if (cdpophonenoStr.length() == 0) {
                    cdpoPhone.requestFocus();
                    cdpoPhone.setError("Field can not be empty");
                    return false;
                } else if (!cdpophonenoStr.matches("^[+][0-9]{10,13}$")) {
                    cdpoPhone.requestFocus();
                    cdpoPhone.setError("correct format: +91xxxxxxxxxx");
                    return false;*/
                } else if (studpwdStr.length() <= 5) {
                    studentpassword.requestFocus();
                    studentpassword.setError("Minimum 6 character required");
                    return false;
                } else if (!studpwdStr.matches("[a-zA-Z0-9]")) {
                    studentpassword.requestFocus();
                    studentpassword.setError("must use one digit,upper_case,lower_case");
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
