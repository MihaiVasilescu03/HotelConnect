package com.example.hotelconnect;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RoomDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_data_menu);
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
    }
}
