package com.example.hotelconnect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hotelconnect.databinding.ManagerMenuBinding;

public class ManagerActivity extends AppCompatActivity {
    ManagerMenuBinding binding;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ManagerMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ManageFragment());
        binding.managerBottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.managemenu){
                replaceFragment(new ManageFragment());
            } else if (item.getItemId()==R.id.anunturimenu) {
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
        fragmentTransaction.replace(R.id.managerFrameLayout,fragment);
        fragmentTransaction.commit();
    }
}
