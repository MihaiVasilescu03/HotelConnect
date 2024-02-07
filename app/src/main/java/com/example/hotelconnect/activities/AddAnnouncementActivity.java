package com.example.hotelconnect.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hotelconnect.API_SERVICES.AnunturiApi;
import com.example.hotelconnect.API_SERVICES.CamereApi;
import com.example.hotelconnect.API_SERVICES.RetrofitService;
import com.example.hotelconnect.R;
import com.example.hotelconnect.models.Anunturi;
import com.example.hotelconnect.models.Camere;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAnnouncementActivity extends Activity {

    EditText titlu, mainbody;
    Button btn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_announcement_menu);

        btn = findViewById(R.id.addAnnouncementContentButton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titlu = (EditText) findViewById(R.id.announcementTitle);
                mainbody = (EditText) findViewById(R.id.announcementBody);

                addAnunt(titlu.getText().toString(), mainbody.getText().toString());
            }
        });



        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> onBackPressed());
    }

    private void addAnunt(String titlu, String mainbody) {
        RetrofitService retrofitService = new RetrofitService();
        AnunturiApi anunturiApi = retrofitService.getRetrofit().create(AnunturiApi.class);

        Call<Void> call = anunturiApi.addNewAnunt(titlu, mainbody);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddAnnouncementActivity.this, "Anunt adaugat cu succes!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddAnnouncementActivity.this, "Ceva nu a functionat!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddAnnouncementActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}