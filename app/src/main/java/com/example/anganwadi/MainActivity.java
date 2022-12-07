package com.example.anganwadi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity implements
AdapterView.OnItemSelectedListener{
    String[] standard= {"Click here to select user","CDPO", "PANCHAYATH", "TEACHER", "SUPERVISOR", "HEALTH INSPECTOR", "USER"};
    TextView user,cdpoRegistration,forgotPassword;
Button login;
    Spinner spin;
    DatabaseReference databaseReference;
    String loginString;
    EditText userName,passWord,idNumber;
    FirebaseAuth fAuth;
    String stringp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        cdpoRegistration=findViewById(R.id.cdporegistration);
        forgotPassword=findViewById(R.id.forgotpassword);
        userName=findViewById(R.id.username);
        passWord=findViewById(R.id.password);
       idNumber=findViewById(R.id.id);

        fAuth=FirebaseAuth.getInstance();

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,standard);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        login=findViewById(R.id.login);
        user=findViewById(R.id.user);

        cdpoRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,com.example.regispagecdpo.class);
                startActivity(i);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,com.example.forgotpasswd.class);
                startActivity(i);
            }
        });




         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String userNameStr = userName.getText().toString();
                 String passwordStr = passWord.getText().toString();
                 String idStr = idNumber.getText().toString();

               /*  if(userNameStr.isEmpty()||passwordStr.isEmpty()||idStr.isEmpty()){
                     Toast.makeText(MainActivity.this,"Please enter the all details",Toast.LENGTH_SHORT).show();
                 }else {
                     if (stringp.equals("PANCHAYATH")) {


                         databaseReference.child("PANCHAYATH").addListenerForSingleValueEvent(new ValueEventListener() {
                             @Override
                             public void onDataChange(@NonNull DataSnapshot snapshot) {
                                 if (snapshot.hasChild(userNameStr)) {
                                     final String getPassword = snapshot.child(userNameStr).child("").getValue(String.class);

                                 }
                             }

                             @Override
                             public void onCancelled(@NonNull DatabaseError error) {

                             }
                         });
                     }
                 }*/

                 fAuth.signInWithEmailAndPassword(userNameStr,passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                       Intent i = new Intent(MainActivity.this, com.example.hometeacher.class);
                         i.putExtra("Modules",loginString);
                         i.putExtra("id",idStr);
                         startActivity(i);
                         Toast.makeText(getApplicationContext(),"success".toString(),Toast.LENGTH_SHORT).show();
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                       Toast.makeText(getApplicationContext(),"Error loging in"+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                     }
                 });

             }
         });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerSelectedItem = spin.getSelectedItem().toString();
        user.setText(spinnerSelectedItem);
        loginString = spinnerSelectedItem;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}