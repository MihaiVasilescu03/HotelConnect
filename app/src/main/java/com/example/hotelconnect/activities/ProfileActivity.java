package com.example.hotelconnect.activities;


import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelconnect.API_SERVICES.RetrofitService;
import com.example.hotelconnect.API_SERVICES.UserApi;
import com.example.hotelconnect.R;
import com.example.hotelconnect.models.ChangePasswordRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    EditText oldPassword, newPassword;
    Button btn, logout;

    String password,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_menu);

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");

        oldPassword = findViewById(R.id.profileOldPassword);
        newPassword = findViewById(R.id.profileNewPassword);

        logout = findViewById(R.id.logoutButton);

        btn = findViewById(R.id.changePasswordButton);

        ImageButton backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(view -> onBackPressed());
        try {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String oldPass = oldPassword.getText().toString();
                    String newPass = newPassword.getText().toString();

                    if (oldPass.equals("") || newPass.equals("")) {
                        Toast.makeText(ProfileActivity.this, "Completeaza toate campurile", Toast.LENGTH_SHORT).show();
                    } else if (password.equals(newPass)) {
                        Toast.makeText(ProfileActivity.this, "Parola noua nu poate fi cea veche", Toast.LENGTH_SHORT).show();
                    } else if (!password.equals(oldPass)) {
                        Toast.makeText(ProfileActivity.this, "Parola nu corespunde contului!", Toast.LENGTH_SHORT).show();
                    } else {
                        changePassword(username, oldPass, newPass);
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Confirmare Logout!").
                        setMessage("Esti sigur ca vrei sa te deconectezi?");
                builder.setPositiveButton("Da",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                finish();
                                startActivity(i);
                            }
                        });
                builder.setNegativeButton("Nu",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertLogout = builder.create();
                alertLogout.show();

            }
        });


    }

    private void changePassword(String username, String oldPass, String newPass) {
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        Call<Void> call = userApi.changePassword(new ChangePasswordRequest(username, oldPass, newPass));


            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        onPasswordChangeSuccess();
                        password = newPass;
                        oldPassword.setText("");
                        newPassword.setText("");

                    } else {
                        onPasswordChangeError(response.message());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    onPasswordChangeError("Network error");
                }
            });

    }

    private void onPasswordChangeSuccess() {
        // Handle success, e.g., display a success message
        Toast.makeText(this, "Parola a fost schimbata!", Toast.LENGTH_SHORT).show();
    }

    private void onPasswordChangeError(String errorMessage) {
        // Handle error, e.g., display an error message
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}

