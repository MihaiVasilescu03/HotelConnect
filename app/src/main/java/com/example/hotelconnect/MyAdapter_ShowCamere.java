package com.example.hotelconnect;


import android.content.Context;
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
import androidx.cardview.widget.CardView;
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
        int bgLiber = 0xFF54FF71;
        int bgReparatii = 0xFF858585;


        holder.camere_id.setText(camere.getCamere());
        holder.status_id.setText(camere.getStatus());
        if(camere.getObs().equals(""))
            holder.obs_id.setText("NU");
        else
            holder.obs_id.setText("DA");
        if(camere.getStatus().equals("Liber")) {
            holder.cardView.setCardBackgroundColor(bgLiber);
        }
        else if(camere.getStatus().equals("Ocupat")){
            holder.cardView.setCardBackgroundColor(bgOcupat);
        }
        else if(camere.getStatus().equals("In reparatii")){
            holder.cardView.setCardBackgroundColor(bgReparatii);
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mesaj;
                if(camere.getObs().equals(""))
                    mesaj = "Nu sunt observatii!";
                else
                    mesaj = camere.getObs();

                showPopup(mesaj,camere.getCamere());

            }
        });


    }

    @Override
    public int getItemCount() {
        return camereList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView camere_id ,status_id, obs_id ;

        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewCamere);
            camere_id = itemView.findViewById(R.id.CameraRecycler);
            status_id = itemView.findViewById(R.id.StareCameraRecycler);
            obs_id = itemView.findViewById(R.id.ObservatiiCameraRecycler);
        }
    }

    public void showPopup(String mesaj,String nume){


        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_camere_obs,null);
        PopupWindow popup = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);

        Button btn = (Button) popupView.findViewById(R.id.PopupButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        int bgOpacity = 0x33000000;

        TextView textView = popupView.findViewById(R.id.PopUpObs);
        textView.setText(mesaj);
        textView = popupView.findViewById(R.id.CameraPop);
        textView.setText(nume);

        popup.setAnimationStyle(R.style.AnimatiePopup);
        popup.setOutsideTouchable(true);
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        popup.showAtLocation(popupView,Gravity.CENTER,0,0);

    }

}

