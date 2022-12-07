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

public class regipageteachernew extends AppCompatActivity {

    Button submit;
    EditText name,emailid,phoneno,dob,address,password,confirmpassword,aadharid,anganwadiname,ward,village,district,panchayath;
    AlertDialog.Builder builder;
    FirebaseAuth fauth;
    DatabaseReference databaseReference;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regipageteachernew);


        name=findViewById(R.id.teachername);
        emailid=findViewById(R.id.teacheremail);
        phoneno=findViewById(R.id.teacherphno);
        dob=findViewById(R.id.teacherdob);
        address=findViewById(R.id.teacheraddress);
        password=findViewById(R.id.teacherpwd);
        confirmpassword=findViewById(R.id.teacherconfirmpwd);
        aadharid=findViewById(R.id.teacheraadharno);
        anganwadiname=findViewById(R.id.teacheranganwadiname);
        ward=findViewById(R.id.teacherward);
        village=findViewById(R.id.teachervillage);
        district=findViewById(R.id.teacherdistrict);
        panchayath=findViewById(R.id.teacherpanchayath);

        fauth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");

        submit=findViewById(R.id.submitT);
        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namestr = name.getText().toString();
                String emailstr = emailid.getText().toString();
                String phnostr = phoneno.getText().toString();
                String dobstr = dob.getText().toString();
                String addressstr = address.getText().toString();
                String pwdstr = password.getText().toString();
                String confirmpwdstr = confirmpassword.getText().toString();
                String aadharidstr = aadharid.getText().toString();
                String anganwadinamestr = anganwadiname.getText().toString();
                String wardstr = ward.getText().toString();
                String villagestr = village.getText().toString();
                String districtstr = district.getText().toString();
                String panchayathstr = panchayath.getText().toString();

                boolean check = validateinfo(emailstr, aadharidstr, phnostr, pwdstr);
                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Data is vaild", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(regipageteachernew.this, "Soory!! check the information aganin", Toast.LENGTH_SHORT).show();
                }

            if (emailstr.isEmpty()||aadharidstr.isEmpty()||phnostr.isEmpty()||pwdstr.isEmpty()){
                Toast.makeText(getApplicationContext(),"Enter all details",Toast.LENGTH_SHORT).show();
            }else {
                if (pwdstr.equals(confirmpwdstr)) {
                    databaseReference.child("TEACHERREG").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" name").setValue(namestr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" emailid").setValue(emailstr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" phoneno").setValue(phnostr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" dob").setValue(dobstr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" address").setValue(addressstr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" password").setValue(pwdstr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" confirmpassword").setValue(confirmpwdstr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" aadharid").setValue(namestr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" anganwadiname").setValue(aadharidstr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" ward").setValue(wardstr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" village").setValue(villagestr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" district").setValue(districtstr);
                            databaseReference.child("TEACHERREG").child(aadharidstr).child(" panchayath").setValue(panchayathstr);
                            Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_SHORT).show();

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
                                Intent i = new Intent(regipageteachernew.this, hometeacher.class);
                                i.putExtra("Module", "PANCHAYATH");
                                startActivity(i);

                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Registration information");
                alert.show();

            }




            private boolean validateinfo(String emailStr, String phnostr, String pwdstr, String s) {
                if (emailStr.length() == 0) {
                    emailid.requestFocus();
                    emailid.setError("Field cnanot be empty");
                } else if (!emailStr.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    emailid.requestFocus();
                    emailid.setError("Enter valid EMAIL");
                    return false;

                } else if (phnostr.length() == 0) {
                    phoneno.requestFocus();
                    phoneno.setError("Field can not be empty");
                    return false;
                } else if (!phnostr.matches("^[+][0-9]{10,13}$")) {
                    phoneno.requestFocus();
                    phoneno.setError("correct format: +91xxxxxxxxxx");
                    return false;
                } else if (pwdstr.length() <= 5) {
                    password.requestFocus();
                    password.setError("Minimum 6 character required");
                    return false;
                } else if (!pwdstr.matches("[a-zA-Z0-9]")) {
                    password.requestFocus();
                    password.setError("must use one digit,upper_case,lower_case");
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

