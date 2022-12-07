package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anganwadi.MainActivity;
import com.example.anganwadi.R;

public class Standard extends AppCompatActivity {
Button cdpo,panchayath,teacher,supervisor,healthInspector,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        cdpo=findViewById(R.id.CDPOlogin);
        panchayath=findViewById(R.id.panchayathlogin);
        teacher=findViewById(R.id.teacherlogin);
        supervisor=findViewById(R.id.supervisorlogin);
        healthInspector=findViewById(R.id.healthlogin);
        user=findViewById(R.id.userlogin);

        cdpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=cdpo.getText().toString();
                Intent i = new Intent(Standard.this, MainActivity.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });


    }
}