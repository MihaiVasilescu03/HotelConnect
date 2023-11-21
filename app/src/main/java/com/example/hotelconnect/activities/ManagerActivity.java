package com.example.hotelconnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hotelconnect.databinding.ManagerMenuBinding;
import com.example.hotelconnect.fragments.AnunturiFragment;
import com.example.hotelconnect.fragments.CamereFragment;
import com.example.hotelconnect.fragments.ManageFragment;
import com.example.hotelconnect.R;

public class ManagerActivity extends AppCompatActivity {
    ManagerMenuBinding binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        binding = ManagerMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ManageFragment());
        binding.managerBottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId()== R.id.managemenu){
                replaceFragment(new ManageFragment());
            } else if (item.getItemId()==R.id.anunturimenu) {
                replaceFragment(new AnunturiFragment());
            } else if (item.getItemId()==R.id.cameremenu) {
                replaceFragment(new CamereFragment());
            }
            return true;
        });
        Button profileButton = findViewById(R.id.managerProfileButton);
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(ManagerActivity.this, ProfileActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("password",password);
            startActivity(intent);
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentmanager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentmanager.beginTransaction();
        fragmentTransaction.replace(R.id.managerFrameLayout,fragment);
        fragmentTransaction.commit();
    }
}
