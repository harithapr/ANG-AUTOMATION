package com.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anganwadi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpasswd extends AppCompatActivity {
    EditText emailforgotpwd;
    Button buttonforgot;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpasswd);
        emailforgotpwd = findViewById(R.id.emailforgot);
        buttonforgot = findViewById(R.id.forgotbutton);
        auth=FirebaseAuth.getInstance();

        buttonforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailstr=emailforgotpwd.getText().toString();
                Toast.makeText(forgotpasswd.this,mailstr ,Toast.LENGTH_SHORT).show();
                auth.sendPasswordResetEmail(mailstr).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(forgotpasswd.this, "Resend link send to the mail", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(forgotpasswd.this, "Error!"+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
}
