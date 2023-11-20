package com.example.hotelconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoveUserActivity extends AppCompatActivity {

    private EditText nume , prenume;
    private Button deleteButton;

    UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_user_menu);
        RetrofitService retrofitService = new RetrofitService();
        userApi = retrofitService.getRetrofit().create(UserApi.class);
        nume = findViewById(R.id.removeNume);
        prenume = findViewById(R.id.removePrenume);
        deleteButton = findViewById(R.id.removeUserButton_2);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> onBackPressed());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });
    }

    private void deleteUser() {
        String numedel = nume.getText().toString().toLowerCase().trim();
        String prenumedel = nume.getText().toString().toLowerCase().trim();

        if (numedel.isEmpty() ||prenumedel.isEmpty()) {
            Toast.makeText(this, "Completeaza toate campurile", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Void> call = userApi.deleteUser(numedel, prenumedel);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RemoveUserActivity.this, "Angajatul a fost sters cu succes", Toast.LENGTH_SHORT).show();
                    nume.setText("");
                    prenume.setText("");
                } else {
                    Toast.makeText(RemoveUserActivity.this, "Angajatul nu a fost gasit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(RemoveUserActivity.this, "Eroare Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}