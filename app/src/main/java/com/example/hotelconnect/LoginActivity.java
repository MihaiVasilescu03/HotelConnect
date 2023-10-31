package com.example.hotelconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ManagerActivity.class);
            startActivity(i);
        });
    }
}
