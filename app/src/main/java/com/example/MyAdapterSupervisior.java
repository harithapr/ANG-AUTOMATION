package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class MyAdapterSupervisior extends RecyclerView.Adapter<MyAdapterSupervisior.MyViewholder> {
    ViewSupervisorDutiesButtonnew context;
    ArrayList<ModelClassSupervisior> list;


    public MyAdapterSupervisior(ViewSupervisorDutiesButtonnew context, ArrayList<ModelClassSupervisior> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapterSupervisior.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewsupervisiorlayoutfileduties,parent,false);
return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterSupervisior.MyViewholder holder, int position) {
        ModelClassSupervisior modelClassSupervisior=list.get(position);
       // holder.Id.setText(modelClassSupervisior.getSupervisor_ID());
        holder.Dutydate.setText(modelClassSupervisior.getSupervisor_Duty_Date());
        holder.Duties.setText(modelClassSupervisior.getSupervisor_Duties());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(ArrayList<ModelClassSupervisior> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView Id,Dutydate,Duties;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            //Id= itemView.findViewById(R.id.supervisorid);
            Dutydate= itemView.findViewById(R.id.dutydate);
            Duties= itemView.findViewById(R.id.duties);

        }
    }
}
