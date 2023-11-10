package com.example.hotelconnect;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter_ShowCamere extends RecyclerView.Adapter<MyAdapter_ShowCamere.MyViewHolder> {
    List<CamereList> camereList;

    Context context;


    public MyAdapter_ShowCamere(List<CamereList> camereList, Context context) {
        this.context = context;
        this.camereList = camereList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_camere_layout , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CamereList camere = camereList.get(position);

        int bgOcupat = 0xFFFD4949;
        int bhLiber = 0xFF54FF71;

        holder.camere_id.setText(camere.getCamere());
        holder.status_id.setText(camere.getStatus());
        if(camere.getStatus().equals("Liber")) {
            holder.cardView.setCardBackgroundColor(bhLiber);
        }
        else if(camere.getStatus().equals("Ocupat")){
            holder.cardView.setCardBackgroundColor(bgOcupat);
        }

    }

    @Override
    public int getItemCount() {
        return camereList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView camere_id ,status_id;

        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewCamere);
            camere_id = itemView.findViewById(R.id.CameraRecycler);
            status_id = itemView.findViewById(R.id.StareCameraRecycler);
        }
    }
}

