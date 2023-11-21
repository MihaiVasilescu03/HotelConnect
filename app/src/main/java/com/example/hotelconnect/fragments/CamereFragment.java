package com.example.hotelconnect.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelconnect.API.CamereApi;
import com.example.hotelconnect.API.RetrofitService;
import com.example.hotelconnect.R;
import com.example.hotelconnect.holders.MyAdapter_ShowCamere;
import com.example.hotelconnect.models.Camere;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CamereFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camere, container, false);

        recyclerView = view.findViewById(R.id.RecyclerViewCamere);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadCamere();

        return view;
    }

    private void loadCamere() {
        RetrofitService retrofitService = new RetrofitService();
        CamereApi camereApi = retrofitService.getRetrofit().create(CamereApi.class);
        camereApi.getAllCamere().enqueue(new Callback<List<Camere>>() {
            @Override
            public void onResponse(Call<List<Camere>> call, Response<List<Camere>> response) {
                if (isAdded()) {
                    if (response.isSuccessful() && response.body() != null) {
                        populateListViewCamere(response.body());
                    } else {
                        Toast.makeText(getContext(), "Failed to get data", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Camere>> call, Throwable t) {
                if (isAdded()) {
                    Toast.makeText(getContext(), "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("CamereFragment", "Network request failed", t);
                }
            }
        });
    }

    private void populateListViewCamere(List<Camere> camereList) {
        if (isAdded()) {
            MyAdapter_ShowCamere adapterShowCamere = new MyAdapter_ShowCamere(camereList);
            recyclerView.setAdapter(adapterShowCamere);
        }
    }
}