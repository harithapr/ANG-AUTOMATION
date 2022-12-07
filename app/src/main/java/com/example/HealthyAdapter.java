package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class HealthyAdapter extends RecyclerView.Adapter<HealthyAdapter.MyViewholder> {
    Healthnotifyxmljava context;
    ArrayList<HealthyModelClass> list;

    public HealthyAdapter(Healthnotifyxmljava context, ArrayList<HealthyModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HealthyAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.healthylayout, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthyAdapter.MyViewholder holder, int position) {
        HealthyModelClass healthyModelClass=list.get(position);
        holder.sentby.setText(healthyModelClass.getSentby());
        holder.subject.setText(healthyModelClass.getSubject());
        holder.date.setText(healthyModelClass.getDate());
        holder.venue.setText(healthyModelClass.getVenue());
        holder.time.setText(healthyModelClass.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(ArrayList<HealthyModelClass> filteredlist) {

        list = filteredlist;

        notifyDataSetChanged();
    }


    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView sentby,date,time,subject,venue;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
           sentby =itemView.findViewById(R.id.sentby);
          date =itemView.findViewById(R.id.subject);
           time =itemView.findViewById(R.id.date);
          subject =itemView.findViewById(R.id.time);
           venue =itemView.findViewById(R.id.venue);
        }
    }
}
