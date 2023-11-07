package com.example.hotelconnect;


import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    EditText oldPassword , newPassword;
    Button btn;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_menu);

        oldPassword = findViewById(R.id.profileOldPassword);
        newPassword = findViewById(R.id.profileNewPassword);
        final SharedPreferences[] preferences = new SharedPreferences[1];


        btn = findViewById(R.id.changePasswordButton);

        helper = new DBHelper(this);

        ImageButton backButton = findViewById(R.id.backButton);


        backButton.setOnClickListener(view -> onBackPressed());



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPw = oldPassword.getText().toString();
                String newPw = newPassword.getText().toString();
                preferences[0] = getSharedPreferences("user_logat", MODE_PRIVATE);

                if(oldPw.equals("") || newPw.equals(""))
                {
                    Toast.makeText(ProfileActivity.this, "Toate campurile trebuie completate!", Toast.LENGTH_SHORT).show();
                }else
                    if(oldPw.equals(newPw)){
                        Toast.makeText(ProfileActivity.this, "Parola noua nu poate fi cea veche!", Toast.LENGTH_SHORT).show();
                    }
                    else {

                    Boolean checkPw = helper.checkOldPassword(oldPw);
                    String username = preferences[0].getString("username","");
                    if (checkPw && helper.updatePassword(username, newPw, oldPw)) {
                            helper.updatePassword(username, newPw, oldPw);


                            Toast.makeText(ProfileActivity.this, "Parola a fost schimbata!", Toast.LENGTH_SHORT).show();
                            oldPassword.setText("");
                            newPassword.setText("");
                    }
                    else{
                        Toast.makeText(ProfileActivity.this, "Parola veche este gresita!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
