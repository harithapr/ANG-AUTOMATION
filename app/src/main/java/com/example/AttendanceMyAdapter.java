package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AttendanceMyAdapter extends RecyclerView.Adapter<AttendanceMyAdapter.MyViewholder> {
    attendancecheckbox context;
    ArrayList<AttendanceMyModelClass> list;
    ArrayList<String> presentList;
    ArrayList<String> absentList;
    ArrayList<String> members;
    DatabaseReference databaseReference;

    private final AdapterCallback adapterCallback;

    public AttendanceMyAdapter(attendancecheckbox context, ArrayList<AttendanceMyModelClass> list, AdapterCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.attendanceitemlayout, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        AttendanceMyModelClass attendanceMyModelClass = list.get(position);
        holder.Name.setText(attendanceMyModelClass.getName());
        holder.attendenceMark.setText(attendanceMyModelClass.getDate());


        presentList = new ArrayList<>();
        absentList = new ArrayList<>();
        members = new ArrayList<>();

        holder.attendenceMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.attendenceMark.isChecked()) {

                    adapterCallback.addToPresentList(attendanceMyModelClass.getName());

                } else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView Name;
        Button Submit;
        CheckBox attendenceMark;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.itemviewuname);
            attendenceMark = itemView.findViewById(R.id.checkbox);
            Submit = itemView.findViewById(R.id.submit);
        }
    }

    public interface AdapterCallback {
       // void addToPresentList(String name);
        void addToPresentList(String name);
    }
}
