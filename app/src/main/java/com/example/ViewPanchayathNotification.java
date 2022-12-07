package com.example;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anganwadi.R;

public class ViewPanchayathNotification extends AppCompatActivity {
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_panchayath_notification);
         textview = findViewById(R.id.textView);
        //getting the notification message
        String message=getIntent().getStringExtra("message");
        textview.setText(message);
    }
}