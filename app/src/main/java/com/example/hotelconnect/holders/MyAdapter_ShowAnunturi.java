package com.example.hotelconnect.holders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelconnect.R;
import com.example.hotelconnect.activities.RoomDataActivity;
import com.example.hotelconnect.fragments.CamereFragment;
import com.example.hotelconnect.models.Anunturi;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MyAdapter_ShowAnunturi extends RecyclerView.Adapter<AnunturiHolder>{

    private List<Anunturi> anunturiList;
    private SimpleDateFormat dateFormat;
    public MyAdapter_ShowAnunturi(List<Anunturi> anunturiList) {
        this.anunturiList = anunturiList;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }

    @NonNull
    @Override
    public AnunturiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_anunturi_layout , parent , false);
        return new AnunturiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnunturiHolder holder, int position) {
        if(!anunturiList.isEmpty()) {
            Anunturi anunt = anunturiList.get(position);
            String formattedDate = dateFormat.format(anunt.getData());
            holder.data.setText(formattedDate);
            holder.titlu.setText(anunt.getTitlu());
            holder.mainbody.setText(anunt.getMainbody());
            holder.cardView.setCardBackgroundColor(0xFF8BC34A);
        }
    }

    @Override
    public int getItemCount() {
        return anunturiList.size();
    }

}
