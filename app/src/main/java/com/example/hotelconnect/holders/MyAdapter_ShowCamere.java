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
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelconnect.R;
import com.example.hotelconnect.activities.RoomDataActivity;
import com.example.hotelconnect.fragments.CamereFragment;
import com.example.hotelconnect.models.Camere;

import java.util.List;

public class MyAdapter_ShowCamere extends RecyclerView.Adapter<CamereHolder> {
    private List<Camere> camereList;
    public MyAdapter_ShowCamere(List<Camere> camereList) {
        this.camereList = camereList;
    }

    @NonNull
    @Override
    public CamereHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_camere_layout , parent , false);
        return new CamereHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CamereHolder holder, int position) {
        Camere camere = camereList.get(position);
        String camera = String.valueOf(camere.getId());
        holder.id.setText(camera);
        holder.status.setText(camere.getStatus());
        holder.obs.setText(camere.getObs());

        int bgOcupat = 0xFFFD4949;
        int bgLiber = 0xFF54FF71;
        int bgReparatii = 0xFF858585;
        int bgInCuratenie = 0xFF393151;
        int bgCurata = 0xFFBBFFF9;
        int bgDeIesit = 0xFFE4C601;



        if(camere.getObs().equals(""))
            holder.obs.setText("NU");
        else
            holder.obs.setText("DA");
        if(camere.getStatus().equals("Liber")) {
            holder.cardView.setCardBackgroundColor(bgLiber);
        }
        else if(camere.getStatus().equals("Ocupat")){
            holder.cardView.setCardBackgroundColor(bgOcupat);
        }
        else if(camere.getStatus().equals("În reparații")){
            holder.cardView.setCardBackgroundColor(bgReparatii);
        }
        else if(camere.getStatus().equals("De Ieșit")){
            holder.cardView.setCardBackgroundColor(bgDeIesit);
        }
        else if(camere.getStatus().equals("In curățenie")){
            holder.cardView.setCardBackgroundColor(bgInCuratenie);
        }
        else if(camere.getStatus().equals("Curată")){
            holder.cardView.setCardBackgroundColor(bgCurata);
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mesaj;
                if(camere.getObs().equals(""))
                    mesaj = "Nu sunt observatii!";
                else
                    mesaj = camere.getObs();

                showPopup(mesaj,camera,v.getContext());

            }
        });


    }

    @Override
    public int getItemCount() {
        return camereList.size();
    }

    public void showPopup(String mesaj,String id, Context context){


        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_camere_obs,null);
        PopupWindow popup = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);

        Button btn = (Button) popupView.findViewById(R.id.changeRoomButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RoomDataActivity.class);
                i.putExtra("cameraId",id);
                context.startActivity(i);
                popup.dismiss();
            }
        });
        int bgOpacity = 0x33000000;

        TextView textView = popupView.findViewById(R.id.PopUpObs);
        textView.setText(mesaj);
        textView = popupView.findViewById(R.id.CameraPop);
        textView.setText(id);

        popup.setAnimationStyle(R.style.AnimatiePopup);
        popup.setOutsideTouchable(true);
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        popup.showAtLocation(popupView,Gravity.CENTER,0,0);

    }

}

