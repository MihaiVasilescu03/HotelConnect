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

public class MyAdapter_ShowCamere extends RecyclerView.Adapter<MyAdapter_ShowCamere.MyViewHolder> {
    private Context context;
    private ArrayList camereId , statusId;

    public MyAdapter_ShowCamere(Context context, ArrayList camereId, ArrayList statusId) {
        this.context = context;
        this.camereId = camereId;
        this.statusId = statusId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_camere , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.camere_id.setText(String.valueOf(camereId.get(position)));
        holder.status_id.setText(String.valueOf(statusId.get(position)));
    }

    @Override
    public int getItemCount() {
        return camereId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView camere_id ,status_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            camere_id = itemView.findViewById(R.id.CameraRecycler);
            status_id = itemView.findViewById(R.id.StareCameraRecycler);
        }
    }
}
