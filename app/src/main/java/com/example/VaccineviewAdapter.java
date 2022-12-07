package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class VaccineviewAdapter extends RecyclerView.Adapter<VaccineviewAdapter.MyViewholder> {
    vaccineviewstudents context;
    ArrayList<VaccineviewMyModel> list;

    public VaccineviewAdapter(vaccineviewstudents context, ArrayList<VaccineviewMyModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VaccineviewAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.vaccineschemelayout, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineviewAdapter.MyViewholder holder, int position) {
        VaccineviewMyModel vaccineviewMyModel=list.get(position);
        holder. name.setText(vaccineviewMyModel.getName());
        holder. address.setText(vaccineviewMyModel.getAddress());
        holder. vaccinename.setText(vaccineviewMyModel.getVaccinename());
        holder. age.setText(vaccineviewMyModel.getAge());
        holder. anganwadiname.setText(vaccineviewMyModel.getAnganwadiname());
        holder. panchayath.setText(vaccineviewMyModel.getPanchayath());
        holder. id.setText(vaccineviewMyModel.getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<VaccineviewMyModel> filteredlist) {
        list = filteredlist;
        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView name,address,vaccinename,age,anganwadiname,panchayath,id;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.names);
            address=itemView.findViewById(R.id.addresss);
            vaccinename=itemView.findViewById(R.id.vaccinenames);
            age=itemView.findViewById(R.id.ages);
            anganwadiname=itemView.findViewById(R.id.anganwadinames);
            panchayath=itemView.findViewById(R.id.panchayaths);
            id=itemView.findViewById(R.id.ids);
        }
    }
}
