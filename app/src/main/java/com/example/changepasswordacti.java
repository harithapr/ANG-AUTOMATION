package com.example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anganwadi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class changepasswordacti extends AppCompatActivity {
    EditText emailforgotpwd;
    Button buttonchange;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepasswordacti);
        emailforgotpwd = findViewById(R.id.emailforgot);
        buttonchange = findViewById(R.id.changebutton);
        auth = FirebaseAuth.getInstance();


        buttonchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailstr = emailforgotpwd.getText().toString();
                Toast.makeText(changepasswordacti.this, mailstr, Toast.LENGTH_SHORT).show();
                auth.sendPasswordResetEmail(mailstr).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(changepasswordacti.this, "Resend link send to the mail", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(changepasswordacti.this, "Error!" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}
