package com.example.hotelconnect;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        oldPassword = (EditText)findViewById(R.id.profileOldPassword);
        newPassword = (EditText)findViewById(R.id.profileNewPassword);
        btn = (Button) findViewById(R.id.changePasswordButton);
        helper = new DBHelper(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPw = oldPassword.getText().toString();
                String newPw = newPassword.getText().toString();

                if(oldPw.equals("") || newPw.equals(""))
                {
                    Toast.makeText(ProfileActivity.this, "Toate campurile trebuie completate!", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkPw = helper.checkOldPassword(oldPw);
                    if (checkPw) {
                        helper.updatePassword(newPw , oldPw);
                        Toast.makeText(ProfileActivity.this, "Parola a fost schimbata!", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(ProfileActivity.this, "Parola veche este gresita!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
