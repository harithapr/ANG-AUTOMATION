package com.example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anganwadi.PDFModelClass;
import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewpanchayathreports extends AppCompatActivity {
    Button viewreports;
    ListView listView;
    DatabaseReference databaseReference;
    List<PDFModelClass> uploads;
    EditText panchayathid;
    String panchayathIdStr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpanchayathreports);
        panchayathid = findViewById(R.id.panchayathid);
        viewreports = findViewById(R.id.viewbtn);
        listView = findViewById(R.id.listview);
        uploads = new ArrayList<>();




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                PDFModelClass pdfupload = uploads.get(i);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(pdfupload.getUrl()));
                startActivity(intent);
            }
        });


        viewreports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panchayathIdStr = panchayathid.getText().toString();
                viewAllFiles(panchayathIdStr);
                Toast.makeText(viewpanchayathreports.this, "Button clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }





  private void viewAllFiles(String string){
        databaseReference=FirebaseDatabase.getInstance().getReference("Uploads");
      Toast.makeText(viewpanchayathreports.this,"Instance created",Toast.LENGTH_SHORT).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postsnapshot : snapshot.getChildren()) {
                    PDFModelClass pdfModelClass = postsnapshot.getValue(PDFModelClass.class);
                    uploads.add(pdfModelClass);
                    Toast.makeText(viewpanchayathreports.this,"forloop",Toast.LENGTH_SHORT).show();
                }

                String[] Uploads = new String[uploads.size()];
                for (int i = 0; i < Uploads.length; i++) {
                    Uploads[i] = uploads.get(i).getName();

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Uploads) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(Color.BLACK);
                        text.setTextSize(22);

                        return view;


                    }
                };
                listView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(viewpanchayathreports.this,"error"+error.getMessage().toString() ,Toast.LENGTH_SHORT).show();
            }
        });

  }


    }
