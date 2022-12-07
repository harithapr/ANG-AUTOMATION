package com.example.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.anganwadi.MainActivity;
import com.example.anganwadi.R;

public class splasactivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasactivity);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splasactivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}