package com.example.hotelconnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hotelconnect.databinding.UserMenuBinding;
import com.example.hotelconnect.fragments.AnunturiFragment;
import com.example.hotelconnect.fragments.CamereFragment;
import com.example.hotelconnect.R;

public class UserActivity extends AppCompatActivity {
    UserMenuBinding binding;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        binding = UserMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new CamereFragment());
        binding.userBottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()== R.id.anunturimenu) {
                replaceFragment(new AnunturiFragment());
            } else if (item.getItemId()==R.id.cameremenu) {
                replaceFragment(new CamereFragment());
            }
            return true;
        });
        Button profileButton = findViewById(R.id.userProfileButton);
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, ProfileActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("password",password);
            startActivity(intent);
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentmanager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentmanager.beginTransaction();
        fragmentTransaction.replace(R.id.userFrameLayout,fragment);
        fragmentTransaction.commit();
    }
}
