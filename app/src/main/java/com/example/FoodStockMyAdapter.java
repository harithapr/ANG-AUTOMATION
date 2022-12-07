package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class FoodStockMyAdapter extends RecyclerView.Adapter<FoodStockMyAdapter.MyViewholder> {
    Foodstockdetailviewuser context;
    ArrayList<FoodStockMyModel> list;

    public FoodStockMyAdapter(Foodstockdetailviewuser context, ArrayList<FoodStockMyModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodStockMyAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.foodstocklayout, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodStockMyAdapter.MyViewholder holder, int position) {
        FoodStockMyModel foodStockMyModel=list.get(position);
        holder. date.setText(foodStockMyModel.getDate());
        //holder. anganwadiid.setText(foodStockMyModel.getAnganwadiid());
        holder. rice.setText(foodStockMyModel.getRice());
        holder. grains.setText(foodStockMyModel.getGrains());
        holder. otherfoods.setText(foodStockMyModel.getOtherfood());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(ArrayList<FoodStockMyModel> filteredlist) {
        // below line is to add our filtered
        // list in our course array list.
        list= filteredlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView date,anganwadiid,rice,grains,otherfoods;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.memname);
            //anganwadiid=itemView.findViewById(R.id.fanganid);
            rice=itemView.findViewById(R.id.memaadharid);
            grains=itemView.findViewById(R.id.mememail);
            otherfoods=itemView.findViewById(R.id.memmobileno);
        }
    }
}
