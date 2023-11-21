package com.example.hotelconnect.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelconnect.R;

public class CamereHolder extends RecyclerView.ViewHolder{

    TextView id,status,obs;
    CardView cardView;

    public CamereHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardViewCamere);
        id = itemView.findViewById(R.id.CameraRecycler);
        status = itemView.findViewById(R.id.StareCameraRecycler);
        obs = itemView.findViewById(R.id.ObservatiiCameraRecycler);


    }
}
