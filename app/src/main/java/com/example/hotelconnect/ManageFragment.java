package com.example.hotelconnect;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ManageFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage, container, false);
        Button buttonadduser = view.findViewById(R.id.addUserButton);
        Button buttonremoveuser = view.findViewById(R.id.removeUserButton);
        Button buttonshowuser = view.findViewById(R.id.showUserButton);
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
        return view;
    }
}