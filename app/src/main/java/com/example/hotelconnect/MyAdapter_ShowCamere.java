package com.example.hotelconnect;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter_ShowCamere extends RecyclerView.Adapter<MyAdapter_ShowCamere.MyViewHolder> {
    List<CamereList> camereList;

    public MyAdapter_ShowCamere(List<CamereList> camereList) {
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
        holder.camere_id.setText(camere.getCamere());
        holder.status_id.setText(camere.getStatus());
    }

    @Override
    public int getItemCount() {
        return camereList.size();
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

