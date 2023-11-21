package com.example.hotelconnect.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelconnect.R;

public class UserHolder extends RecyclerView.ViewHolder {
    TextView nume , prenume , status;
    public UserHolder(@NonNull View itemView) {
        super(itemView);
        nume = itemView.findViewById(R.id.numeRecycler);
        prenume = itemView.findViewById(R.id.prenumeRecycler);
        status = itemView.findViewById(R.id.statusRecycler);
    }
}
