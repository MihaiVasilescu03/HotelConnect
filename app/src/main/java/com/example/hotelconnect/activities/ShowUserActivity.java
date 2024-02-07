package com.example.hotelconnect.activities;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelconnect.API_SERVICES.RetrofitService;
import com.example.hotelconnect.API_SERVICES.UserApi;
import com.example.hotelconnect.R;
import com.example.hotelconnect.holders.MyAdapter_ShowUser;
import com.example.hotelconnect.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_user);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> onBackPressed());
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadUsers();
    }

    private void loadUsers() {
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        userApi.getAllUsers()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(ShowUserActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }

                });

    }

    private void populateListView(List<User> userList) {
        MyAdapter_ShowUser adapterShowUser = new MyAdapter_ShowUser(userList);
        recyclerView.setAdapter(adapterShowUser);
    }
}
