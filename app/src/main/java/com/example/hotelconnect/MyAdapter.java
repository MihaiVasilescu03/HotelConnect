package com.example.hotelconnect;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList nameId , prenumeId , statusId;

    public MyAdapter(Context context, ArrayList nameId, ArrayList prenumeId, ArrayList statusId) {
        this.context = context;
        this.nameId = nameId;
        this.prenumeId = prenumeId;
        this.statusId = statusId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.show_user_menu , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nume_id.setText(String.valueOf(nameId.get(position)));
        holder.prenume_id.setText(String.valueOf(prenumeId.get(position)));
        holder.status_id.setText(String.valueOf(statusId.get(position)));
    }

    @Override
    public int getItemCount() {
        return nameId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nume_id ,prenume_id,status_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nume_id = itemView.findViewById(R.id.numeRecycler);
            prenume_id = itemView.findViewById(R.id.prenumeRecycler);
            status_id = itemView.findViewById(R.id.statusRecycler);
        }
    }
}
