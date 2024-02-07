package com.example.hotelconnect.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelconnect.R;

public class AnunturiHolder extends RecyclerView.ViewHolder{
    TextView data,titlu,mainbody;
    CardView cardView;

    public AnunturiHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardViewAnunturi);
        data = itemView.findViewById(R.id.DataRecycler);
        titlu = itemView.findViewById(R.id.TitleRecycler);
        mainbody = itemView.findViewById(R.id.MainbodyRecycler);


    }
}
