package com.example.hotelconnect.holders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelconnect.R;
import com.example.hotelconnect.holders.UserHolder;
import com.example.hotelconnect.models.User;

import java.util.List;

public class MyAdapter_ShowUser extends RecyclerView.Adapter<UserHolder> {

    private List<User> userList;

    public MyAdapter_ShowUser(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_user_menu , parent , false);
        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = userList.get(position);
        holder.nume.setText(user.getNume());
        holder.prenume.setText(user.getPrenume());
        holder.status.setText(user.getStatus());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}

