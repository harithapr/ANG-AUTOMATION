package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewholder> {
    FoodStockDetailsViewNew context;
    ArrayList<FoodMyModel> list;

    public FoodAdapter(FoodStockDetailsViewNew context, ArrayList<FoodMyModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.foodstocklayout, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewholder holder, int position) {
        FoodMyModel foodMyModel = list.get(position);
        holder.date.setText(foodMyModel.getDate());
        holder.grains.setText(foodMyModel.getGrains());
        holder.otherfood.setText(foodMyModel.getOtherfood());
        holder.rice.setText(foodMyModel.getRice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(ArrayList<FoodMyModel> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView date,grains,otherfood,rice;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.oodate);
            grains=itemView.findViewById(R.id.oograins);
            otherfood=itemView.findViewById(R.id.oootherfood);
            rice=itemView.findViewById(R.id.oorice);
        }
    }
}
