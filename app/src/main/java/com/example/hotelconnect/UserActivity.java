package com.example.hotelconnect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hotelconnect.databinding.UserMenuBinding;

public class UserActivity extends AppCompatActivity {
    UserMenuBinding binding;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = UserMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new CamereFragment());
        binding.userBottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.anunturimenu) {
                replaceFragment(new AnunturiFragment());
            } else if (item.getItemId()==R.id.cameremenu) {
                replaceFragment(new CamereFragment());
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentmanager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentmanager.beginTransaction();
        fragmentTransaction.replace(R.id.userFrameLayout,fragment);
        fragmentTransaction.commit();
    }
}
