package com.example.anganwadi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class cdporeports extends AppCompatActivity {
Button panchayathReport,supervisorReport,teacherReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdporeports);

        panchayathReport=findViewById(R.id.panchayathreports);
        supervisorReport=findViewById(R.id.supervisorreports);
        teacherReport=findViewById(R.id.teacherreports);

        panchayathReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cdporeports.this,com.example.Viewpanchayathreportsnew.class);
                startActivity(i);
            }
        });

        supervisorReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cdporeports.this, com.example.ViewSupervisorreportsnew.class);
                startActivity(i);
            }
        });

        teacherReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cdporeports.this, com.example.ViewTeacherreportsnew.class);
                startActivity(i);

            }
        });

    }
}