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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CamereFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CamereFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<String> camere , status;

    DBHelper_Camere DB;
    MyAdapter_ShowCamere adapter;

    public CamereFragment() {
    }


    public static CamereFragment newInstance(String param1, String param2) {
        CamereFragment fragment = new CamereFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        DB = new DBHelper_Camere(this);
        camere = new ArrayList<>();
        status = new ArrayList<>();
        recyclerView = findViewById(R.id.CameraRecycler);
        adapter = new MyAdapter_ShowUser(this , camere , status);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new FragmentManager(this));
        displayData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camere, container, false);
    }
    private void displayData() {
        Cursor cursor = DB.getData();

            while(cursor.moveToNext()){
                camere.add(cursor.getString(0));
                status.add(cursor.getString(1));
            }
    }
}