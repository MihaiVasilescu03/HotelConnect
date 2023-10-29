package com.example.hoteltest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button button = (Button)findViewById(R.id.loginButton);
        button.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ManagerActivity.class);
            startActivity(i);
        });
    }
}