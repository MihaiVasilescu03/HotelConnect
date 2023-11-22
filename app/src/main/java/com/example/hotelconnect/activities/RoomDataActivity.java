package com.example.hotelconnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelconnect.API.CamereApi;
import com.example.hotelconnect.API.RetrofitService;
import com.example.hotelconnect.API.UserApi;
import com.example.hotelconnect.R;
import com.example.hotelconnect.models.Camere;
import com.example.hotelconnect.models.ChangePasswordRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomDataActivity extends AppCompatActivity {

    EditText obs;
    TextView camera;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_data_menu);

        btn = findViewById(R.id.buttonChangeRoom);

        camera = findViewById(R.id.nrCamera);

        camera.setText(getIntent().getStringExtra("cameraId"));

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> onBackPressed());

        Spinner spinnerRoomStatus = (Spinner) findViewById(R.id.roomStatusSpinner);
        ArrayAdapter<CharSequence> adapterRoomStatus = ArrayAdapter.createFromResource(
                this,
                R.array.room_status_array,
                android.R.layout.simple_spinner_item
        );
        adapterRoomStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomStatus.setAdapter(adapterRoomStatus);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roomStatus = spinnerRoomStatus.getSelectedItem().toString();
                int id = Integer.parseInt(camera.getText().toString());
                obs = (EditText) findViewById(R.id.roomObs);
                changeCamera(id, obs.getText().toString(), roomStatus);
                finish();
            }
        });
    }

    private void changeCamera(int id, String obs, String status) {
        RetrofitService retrofitService = new RetrofitService();
        CamereApi camereApi = retrofitService.getRetrofit().create(CamereApi.class);
        Call<Void> call = camereApi.changeCamera(new Camere(id, status, obs));


        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RoomDataActivity.this, "Camera actualizata cu succes!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RoomDataActivity.this, "Ceva nu a functionat!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RoomDataActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
