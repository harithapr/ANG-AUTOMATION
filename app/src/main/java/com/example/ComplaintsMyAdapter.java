package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class ComplaintsMyAdapter extends RecyclerView.Adapter<ComplaintsMyAdapter.MyViewholder> {
    Complaintviewpanchayath context;
    ArrayList<ComplaintsMyModelClass> list;

    public ComplaintsMyAdapter(Complaintviewpanchayath context, ArrayList<ComplaintsMyModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ComplaintsMyAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complaintlayoutdetails, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintsMyAdapter.MyViewholder holder, int position) {
        ComplaintsMyModelClass complaintsMyModelClass=list.get(position);
        holder.complaintername.setText(complaintsMyModelClass.getComplaintername());
        holder.complainteraddress.setText(complaintsMyModelClass.getComplainteraddress());
        holder.complaints.setText(complaintsMyModelClass.getComplaints());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  void  filterList(ArrayList<ComplaintsMyModelClass> filteredlist){
        list=filteredlist;
        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView complaintername,complainteraddress,complaints;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            complaintername=itemView.findViewById(R.id.cccname);
            complainteraddress=itemView.findViewById(R.id.cccaddress);
            complaints=itemView.findViewById(R.id.ccc);
        }
    }
}
