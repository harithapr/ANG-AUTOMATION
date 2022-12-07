package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class PregnentWomenMyAdapter extends RecyclerView.Adapter<PregnentWomenMyAdapter.MyViewholder> {
    viewpregnentwomens context;
    ArrayList<PregnentWomenMyModel> list;

    public PregnentWomenMyAdapter(viewpregnentwomens context, ArrayList<PregnentWomenMyModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PregnentWomenMyAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewpregnentwomendetails, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PregnentWomenMyAdapter.MyViewholder holder, int position) {
        PregnentWomenMyModel pregnentWomenMyModel = list.get(position);
        holder.Aadharid.setText(pregnentWomenMyModel.getAadharid());
        holder.Address.setText(pregnentWomenMyModel.getAddress());
        holder.Age.setText(pregnentWomenMyModel.getAge());
        holder.Dob.setText(pregnentWomenMyModel.getDob());
        holder.Height.setText(pregnentWomenMyModel.getHeight());
        holder.Husbandname.setText(pregnentWomenMyModel.getHusbandname());
        holder.Mobileno.setText(pregnentWomenMyModel.getMobileno());
        holder.Month.setText(pregnentWomenMyModel.getMonth());
        holder.Name.setText(pregnentWomenMyModel.getName());
        holder.Weight.setText(pregnentWomenMyModel.getWeight());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(ArrayList<PregnentWomenMyModel> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView Aadharid,Address,Age,Dob,Height,Mobileno,Husbandname,Month,Name,Weight;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            Aadharid=itemView.findViewById(R.id.gaadharid);
            Address=itemView.findViewById(R.id.gaddress);
            Age=itemView.findViewById(R.id.gage);
            Dob=itemView.findViewById(R.id.gdob);
            Height=itemView.findViewById(R.id.gheight);
            Mobileno=itemView.findViewById(R.id.gmobileno);
            Husbandname=itemView.findViewById(R.id.ghusbandname);
            Month=itemView.findViewById(R.id.gmonth);
            Name=itemView.findViewById(R.id.gname);
            Weight=itemView.findViewById(R.id.gweight);
        }
    }
}
