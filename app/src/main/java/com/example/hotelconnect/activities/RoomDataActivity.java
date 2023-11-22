package com.example.hotelconnect.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_data_menu);

        btn = findViewById(R.id.buttonChangeRoom);


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

        Spinner spinnerRoomNumber = (Spinner) findViewById(R.id.roomNumberSpinner);
        ArrayAdapter<CharSequence> adapterRoomNumber = ArrayAdapter.createFromResource(
                this,
                R.array.room_number_array,
                android.R.layout.simple_spinner_item
        );
        adapterRoomNumber.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomNumber.setAdapter(adapterRoomNumber);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roomStatus = spinnerRoomStatus.getSelectedItem().toString();
                Object idString = spinnerRoomNumber.getSelectedItem();
                int id = Integer.parseInt(String.valueOf(idString));
                obs = (EditText) findViewById(R.id.roomObs);
                changeCamera(id, obs.getText().toString(), roomStatus);
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
