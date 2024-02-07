package com.example.hotelconnect.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hotelconnect.R;
import com.example.hotelconnect.activities.AddAnnouncementActivity;
import com.example.hotelconnect.activities.AddUserActivity;
import com.example.hotelconnect.activities.RemoveUserActivity;
import com.example.hotelconnect.activities.ShowUserActivity;

public class ManageFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage, container, false);
        Button buttonadduser = view.findViewById(R.id.addUserButton);
        Button buttonremoveuser = view.findViewById(R.id.removeUserButton);
        Button buttonshowuser = view.findViewById(R.id.showUserButton);
        Button buttonaddannouncement = view.findViewById(R.id.addAnnouncementButton);
        buttonadduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddUserActivity.class);
                startActivity(intent);
            }
        });
        buttonremoveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RemoveUserActivity.class);
                startActivity(intent);
            }
        });
        buttonshowuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowUserActivity.class);
                startActivity(intent);
            }
        });
        buttonaddannouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddAnnouncementActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}