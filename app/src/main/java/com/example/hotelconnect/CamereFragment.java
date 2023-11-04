package com.example.hotelconnect;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CamereFragment extends Fragment {

    List<CamereList> camereList;

    RecyclerView recyclerView;

    MyAdapter_ShowCamere adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camere, container, false);

        camereList = getDataFromDatabase();

        recyclerView = view.findViewById(R.id.RecyclerViewCamere);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyAdapter_ShowCamere(camereList);
        recyclerView.setAdapter(adapter);

        return view;
    }
    private List<CamereList> getDataFromDatabase() {
        DBHelper_Camere dbHelper = new DBHelper_Camere(getContext());
        List<CamereList> data = dbHelper.getData();
        return data;
    }
}