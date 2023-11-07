package com.example.hotelconnect;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_menu);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> onBackPressed());
    }

}
