package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anganwadi.R;

import java.util.ArrayList;

public class MemberMyAdapter extends RecyclerView.Adapter<MemberMyAdapter.MyViewholder> {
    memberviewhomedetails context;
    ArrayList<memberModelclass> list;

    public MemberMyAdapter(memberviewhomedetails context, ArrayList<memberModelclass> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MemberMyAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.memberitem, parent, false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberMyAdapter.MyViewholder holder, int position) {

       memberModelclass memberModelclasst=list.get(position);
        holder. Membername.setText(memberModelclasst.getName());
        holder.Memberid.setText(memberModelclasst.getMember_id());
      //  holder.MemberAadharid.setText(memberModelclass.getMemberid());
        holder.MemberAddress.setText(memberModelclasst.getMember_address());
        holder.Memberdob.setText(memberModelclasst.getDob());
        holder.Memberward.setText(memberModelclasst.getWard());
        holder.Membermobileno.setText(memberModelclasst.getMobileno());
        holder.MemberPanchayath.setText(memberModelclasst.getPanchayath());
        holder.Memberposition.setText(memberModelclasst.getPosition());
       // holder.Memberplace.setText(memberModelclass.getMemberplace());
       // holder.Memberemail.setText(memberModelclass.getMemberemail());


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<memberModelclass> filteredlist) {
        // below line is to add our filtered
        // list in our course array list.
        list = filteredlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {
        TextView Membername,Memberid,MemberAadharid,MemberAddress,Memberdob,Memberward,Membermobileno,MemberPanchayath,Memberposition,Memberplace,Memberemail;
        public MyViewholder(View itemView ) {
            super(itemView);
            Membername=itemView.findViewById(R.id.memname);
            Memberid=itemView.findViewById(R.id.memid);
           // MemberAadharid=itemView.findViewById(R.id.memaadharid);
            MemberAddress=itemView.findViewById(R.id.memaddress);
            Memberward=itemView.findViewById(R.id.memward);
            Membermobileno=itemView.findViewById(R.id.memmobileno);
            MemberPanchayath=itemView.findViewById(R.id.mempanchayath);
            Memberposition=itemView.findViewById(R.id.memposition);
            Memberdob=itemView.findViewById(R.id.memdob);
           // Memberplace=itemView.findViewById(R.id.memplace);
          //  Memberemail=itemView.findViewById(R.id.mememail);

        }
    }
}
