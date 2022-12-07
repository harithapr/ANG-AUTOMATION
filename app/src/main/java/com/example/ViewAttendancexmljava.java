package com.example;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ViewAttendancexmljava extends AppCompatActivity {
    ListView Attendancelist;
    DatabaseReference databaseReference;
    List<AttendanceMyModelClass> uploads;
   // ImageView Searchbk;
    ViewAttendancemodelclass viewAttendancemodelclass;
    ArrayList<String> Present;
    public static final String DATE_FORMAT = "yyyy-MM-dd";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendancexmljava);

        Attendancelist = findViewById(R.id.attlistview);

        Present=new ArrayList<String>();


        viewAllFiles();
    }
    public String dateToString() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateToString = dateFormat.format(calendar.getTime());
        return dateToString;
    }

    private void viewAllFiles() {

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/Attendance");
        String dateStr=this.dateToString();
        databaseReference.child("Attendence").child(dateStr).child("Present").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Present= (ArrayList<String>) snapshot.getValue();



                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, Present) {
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
                Attendancelist.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }
}