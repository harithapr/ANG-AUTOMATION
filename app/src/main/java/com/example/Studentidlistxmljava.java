package com.example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Studentidlistxmljava extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    List<StudentViewModelClass> uploads;
    String nameStr,anganvadiidStr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentidlistxmljava);
        listView = findViewById(R.id.listviews);
        uploads = new ArrayList<>();

        Bundle bundle=getIntent().getExtras();
        anganvadiidStr=bundle.get("AnganwadiId").toString();

        viewAllFiles();
        Toast.makeText(getApplicationContext(),"phase1",Toast.LENGTH_SHORT).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nameStr = (String)listView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),viewstudentnamedetails.class);
                intent.putExtra("Name",nameStr);
                intent.putExtra("AnganwadiId",anganvadiidStr);
                startActivity(intent);
            }
        });
    }

    private void viewAllFiles() {
        databaseReference= FirebaseDatabase.getInstance().getReference("STUDENT1");
        databaseReference.child(anganvadiidStr).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postsnapshot1 : snapshot.getChildren()) {
                    Toast.makeText(getApplicationContext(), postsnapshot1.getValue().toString(), Toast.LENGTH_LONG);
                    StudentViewModelClass studentViewModelClass = postsnapshot1.getValue(StudentViewModelClass.class);
                    uploads.add(studentViewModelClass);
                }
                String[] Uploads = new String[uploads.size()];
                for (int i = 0; i < Uploads.length; i++) {
                    Uploads[i] = uploads.get(i).getName();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, Uploads) {
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
                        Toast.makeText(getApplicationContext(), "Error" + error.getMessage().toString(), Toast.LENGTH_LONG);
                    }


            });
    }
}