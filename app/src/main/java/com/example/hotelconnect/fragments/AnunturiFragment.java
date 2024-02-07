package com.example.hotelconnect.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotelconnect.API_SERVICES.AnunturiApi;
import com.example.hotelconnect.API_SERVICES.CamereApi;
import com.example.hotelconnect.API_SERVICES.RetrofitService;
import com.example.hotelconnect.R;
import com.example.hotelconnect.holders.MyAdapter_ShowAnunturi;
import com.example.hotelconnect.holders.MyAdapter_ShowCamere;
import com.example.hotelconnect.models.Anunturi;
import com.example.hotelconnect.models.Camere;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AnunturiFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_anunturi, container, false);

        swipeRefreshLayout = view.findViewById(R.id.SwipeRefreshAnunturi);

        recyclerView = view.findViewById(R.id.RecyclerViewAnunturi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadAnunturi();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        loadAnunturi();


        return view;
    }

    private void loadAnunturi() {
        RetrofitService retrofitService = new RetrofitService();
        AnunturiApi anunturiApi = retrofitService.getRetrofit().create(AnunturiApi.class);
        anunturiApi.getAllAnunturi().enqueue(new Callback<List<Anunturi>>() {
            @Override
            public void onResponse(Call<List<Anunturi>> call, Response<List<Anunturi>> response) {
                if (isAdded()) {
                    if (response.isSuccessful() && response.body() != null) {
                        populateListViewAnunturi(response.body());
                    } else {
                        Toast.makeText(getContext(), "Failed to get data", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Anunturi>> call, Throwable t) {
                if (isAdded()) {
                    Toast.makeText(getContext(), "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("AnunturiFragment", "Network request failed", t);
                }
            }
        });
    }

    private void populateListViewAnunturi(List<Anunturi> anunturiList) {
        if (isAdded()) {
            MyAdapter_ShowAnunturi adapterShowAnunturi = new MyAdapter_ShowAnunturi(anunturiList);
            recyclerView.setAdapter(adapterShowAnunturi);
        }
    }

}