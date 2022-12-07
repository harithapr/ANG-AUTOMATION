package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.MyViewholder> {
    Notificationxmljava context;
    ArrayList<NotifyModelClass> list;


    public NotifyAdapter(Notificationxmljava context, ArrayList<NotifyModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NotifyAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notifylayout, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyAdapter.MyViewholder holder, int position) {
        NotifyModelClass notifyModelClass=list.get(position);
        holder. sentby.setText(notifyModelClass.getSentby());
        holder. title.setText(notifyModelClass.getTitle());
        holder. date.setText(notifyModelClass.getDate());
        holder. venue.setText(notifyModelClass.getVenue());
        holder. time.setText(notifyModelClass.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(ArrayList<NotifyModelClass> filteredlist) {

        list = filteredlist;

        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView sentby,date,time,title,venue;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            sentby=itemView.findViewById(R.id.sentby);
            title=itemView.findViewById(R.id.Title);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            venue=itemView.findViewById(R.id.venue);
        }
    }
}
