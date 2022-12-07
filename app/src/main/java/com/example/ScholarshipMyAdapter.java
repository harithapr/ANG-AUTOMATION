package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class ScholarshipMyAdapter extends RecyclerView.Adapter<ScholarshipMyAdapter.MyViewholder> {
    scholarshipschemeviewdetails context;
    ArrayList<MyModelScholarship> list;

    public ScholarshipMyAdapter(scholarshipschemeviewdetails context, ArrayList<MyModelScholarship> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ScholarshipMyAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.scholarshipschemenew, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ScholarshipMyAdapter.MyViewholder holder, int position) {
        MyModelScholarship myModelScholarship=list.get(position);
        holder. Schemeid.setText(myModelScholarship.getSchemeid());
        holder. Schemename.setText(myModelScholarship.getSchemename());
        holder. Schemetype.setText(myModelScholarship.getSchemetype());
        holder. Schemegrade.setText(myModelScholarship.getSchemegrade());
        holder. Requireddoc.setText(myModelScholarship.getRequireddoc());
        holder. Timelimit.setText(myModelScholarship.getTimelimit());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<MyModelScholarship> filteredlist) {
        list = filteredlist;
        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView Schemeid,Schemename,Schemetype,Schemegrade,Requireddoc,Timelimit;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            Schemeid=itemView.findViewById(R.id.sid);
            Schemename=itemView.findViewById(R.id.sscheme);
            Schemetype=itemView.findViewById(R.id.stype);
            Schemegrade=itemView.findViewById(R.id.sgrade);
            Requireddoc=itemView.findViewById(R.id.sdocs);
            Timelimit=itemView.findViewById(R.id.stimelimit);

        }
    }
}
