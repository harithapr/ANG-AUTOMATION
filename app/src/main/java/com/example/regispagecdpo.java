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

public class regispagecdpo extends AppCompatActivity {
Button submit;
EditText cdpoName,cdpoEmail,cdpoPhone,cdpoDOB,cdpoAddress,cdpoPassword,cdpoConfPassword,cdpoAadhar,cdpoBlockName,cdpoDistrict;
//alert dialog
AlertDialog.Builder builder;
FirebaseAuth fauth;
DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regispagecdpo);

        cdpoName=findViewById(R.id.nameCDPO);
        cdpoEmail=findViewById(R.id.emailCDPO);
        cdpoPhone=findViewById(R.id.phonenumberCDPO);
        submit=findViewById(R.id.submit);
        cdpoDOB=findViewById(R.id.dobCDPO);
        cdpoAddress=findViewById(R.id.addressCDPO);
        cdpoPassword=findViewById(R.id.passwordCDPO);
        cdpoConfPassword=findViewById(R.id.confPassWordCDPO);
        cdpoAadhar=findViewById(R.id.aadharCDPO);
        cdpoBlockName=findViewById(R.id.blocknameCDPO);
       cdpoDistrict=findViewById(R.id.districtCDPO);

       fauth=FirebaseAuth.getInstance();
       databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");


        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cdpoNameStr = cdpoName.getText().toString();
                String cdpoemailidStr = cdpoEmail.getText().toString();
                String cdpophonenoStr = cdpoPhone.getText().toString();
                String cdpoDobStr = cdpoDOB.getText().toString();
                String cdpoAddressStr = cdpoAddress.getText().toString();
                String cdpoPasswordStr = cdpoPassword.getText().toString();
                String cdpoConfirmpasswordStr = cdpoConfPassword.getText().toString();
                String cdpoAdharnumberStr = cdpoAadhar.getText().toString();
                String cdpoBlocknameStr = cdpoBlockName.getText().toString();
                String cdpoDistrictStr = cdpoDistrict.getText().toString();

              /* boolean check=    validateinfo(emailStr,phonenumberStr,passwordStr,AadharStr,kudumbasreeidStr);


                if(check == true){
                    Toast.makeText(getApplicationContext(),"Data is vaild",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(registrationpage.this, "Soory!! check the information aganin", Toast.LENGTH_SHORT).show();
                }*/

                boolean check = validateinfo(cdpoemailidStr, cdpophonenoStr, cdpoPasswordStr, cdpoAdharnumberStr);

                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Data is vaild", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(regispagecdpo.this, "Soory!! check the information aganin", Toast.LENGTH_SHORT).show();
                }

                if (cdpoAddressStr.isEmpty() || cdpoNameStr.isEmpty() || cdpoemailidStr.isEmpty()
                        || cdpophonenoStr.isEmpty() || cdpoDobStr.isEmpty() || cdpoPasswordStr.isEmpty() || cdpoConfirmpasswordStr.isEmpty()
                        || cdpoBlocknameStr.isEmpty() || cdpoDistrictStr.isEmpty() || cdpoAdharnumberStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (cdpoPasswordStr.equals(cdpoConfirmpasswordStr)) {

                        fauth.createUserWithEmailAndPassword(cdpoemailidStr, cdpoPasswordStr)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            databaseReference.child("CDPO").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    databaseReference.child("CDPO").child(cdpoAdharnumberStr).child("Name").setValue(cdpoNameStr);
                                                    databaseReference.child("CDPO").child(cdpoAdharnumberStr).child("EmailID").setValue(cdpoemailidStr);
                                                    databaseReference.child("CDPO").child(cdpoAdharnumberStr).child("Phone Number").setValue(cdpophonenoStr);
                                                    databaseReference.child("CDPO").child(cdpoAdharnumberStr).child("DOB").setValue(cdpoDobStr);
                                                    databaseReference.child("CDPO").child(cdpoAdharnumberStr).child("Aadharid").setValue(cdpoAdharnumberStr);
                                                    databaseReference.child("CDPO").child(cdpoAdharnumberStr).child("Address").setValue(cdpoAddressStr);
                                                    databaseReference.child("CDPO").child(cdpoAdharnumberStr).child("Password").setValue(cdpoPasswordStr);
                                                    databaseReference.child("CDPO").child(cdpoAdharnumberStr).child("Block Name").setValue(cdpoBlocknameStr);
                                                    databaseReference.child("CDPO").child(cdpoAdharnumberStr).child("District").setValue(cdpoDistrictStr);
                                                    builder.setMessage("Your information successfully registered")
                                                            .setCancelable(false)
                                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    Intent i = new Intent(com.example.regispagecdpo.this, MainActivity.class);
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


                //Setting message manually and performing action on button click
             /*  builder.setMessage("Your information successfully registered")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(com.example.regispagecdpo.this, MainActivity.class);
                                startActivity(i);
                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Registration information");
                alert.show();
              */
            }

            private boolean validateinfo(String cdpoemailidStr, String cdpophonenoStr, String cdpoPasswordStr, String cdpoAdharnumberStr) {

                if (cdpoemailidStr.length() == 0) {
                    cdpoEmail.requestFocus();
                    cdpoEmail.setError("Field cnanot be empty");
                } else if (!cdpoemailidStr.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    cdpoEmail.requestFocus();
                    cdpoEmail.setError("Enter valid EMAIL");
                    return false;

                } else if (cdpophonenoStr.length() == 0) {
                    cdpoPhone.requestFocus();
                    cdpoPhone.setError("Field can not be empty");
                    return false;
                } else if (!cdpophonenoStr.matches("^[+][0-9]{10,13}$")) {
                    cdpoPhone.requestFocus();
                    cdpoPhone.setError("correct format: +91xxxxxxxxxx");
                    return false;
                } else if (cdpoPasswordStr.length() <= 5) {
                    cdpoPassword.requestFocus();
                    cdpoPassword.setError("Minimum 6 character required");
                    return false;
                } else if (!cdpoPasswordStr.matches("[a-zA-Z0-9]")) {
                    cdpoPassword.requestFocus();
                    cdpoPassword.setError("must use one digit,upper_case,lower_case");
                    return false;
                }
                else{
                    return true;

                }
                return false;
            }
         /* private boolean validateinfo(String cd, String phonenumberStr, String passwordStr, String aadharStr) {
                if(emailStr.length()==0){
                    emaiL.requestFocus();
                    emaiL.setError("Field cnanot be empty");
                }
                else if(!emailStr.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                    emaiL.requestFocus();
                    emaiL.setError("Enter valid EMAIL");
                    return false;

                }else if(phonenumberStr.length()==0){
                    phoneNumber.requestFocus();
                    phoneNumber.setError("Field can not be empty");
                    return false;
                }else if(!phonenumberStr.matches("^[+][0-9]{10,13}$")){
                    phoneNumber.requestFocus();
                    phoneNumber.setError("correct format: +91xxxxxxxxxx");
                    return false;
                }
                else if(passwordStr.length()<=5){
                    Password.requestFocus();
                    Password.setError("Minimum 6 character required");
                    return false;
                }
                else if(!passwordStr.matches("[a-zA-Z0-9]")){
                    Password.requestFocus();
                    Password.setError("must use one digit,upper_case,lower_case");
                    return false;
                }
                else{
                    return true;

                }
                return false;
            }*/


        });

    }
}
