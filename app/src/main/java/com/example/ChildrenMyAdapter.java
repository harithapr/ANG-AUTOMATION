package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class ChildrenMyAdapter extends RecyclerView.Adapter<ChildrenMyAdapter.MyViewholder> {
    viewchildren0to3yearsdetails context;
    ArrayList<ChildrenMyModelClass> list;

    public ChildrenMyAdapter(viewchildren0to3yearsdetails context, ArrayList<ChildrenMyModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChildrenMyAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.childrenlayoutdetails, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildrenMyAdapter.MyViewholder holder, int position) {
ChildrenMyModelClass childrenMyModelClass=list.get(position);
holder.name.setText(childrenMyModelClass.getName());
        holder.age.setText(childrenMyModelClass.getAge());
        holder.address.setText(childrenMyModelClass.getAddress());
        holder.mothername.setText(childrenMyModelClass.getMothername());
        holder.fathername.setText(childrenMyModelClass.getFathername());
        holder.ward.setText(childrenMyModelClass.getWard());
        holder.place.setText(childrenMyModelClass.getPlace());
        holder.id.setText(childrenMyModelClass.getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(ArrayList<ChildrenMyModelClass>  filteredlist) {
        list = filteredlist;
        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView name,age,address,mothername,fathername,ward,place,id;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.ccname);
            age=itemView.findViewById(R.id.ccage);
            address=itemView.findViewById(R.id.ccaddress);
            mothername=itemView.findViewById(R.id.ccmothername);
            fathername=itemView.findViewById(R.id.ccfathername);
            ward=itemView.findViewById(R.id.ccward);
            place=itemView.findViewById(R.id.ccplace);
            id=itemView.findViewById(R.id.ccid);

        }
    }
}
