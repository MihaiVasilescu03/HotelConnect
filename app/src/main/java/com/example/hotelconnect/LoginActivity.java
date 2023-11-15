package com.example.hotelconnect;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText numeReg,parolaReg;
    Button btnLogin;
    DBHelper helper;

    DBHelper_Camere helperCamere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        numeReg = findViewById(R.id.loginUser);
        parolaReg = findViewById(R.id.loginPassword);

        btnLogin = findViewById(R.id.loginButton);

        helper = new DBHelper(this);

        helperCamere = new DBHelper_Camere(this);

        for(int i=1;i<=2;i++)
        {
            helperCamere.insertData("Camera"+i,"Liber", "");
        }

        for(int i=3;i<=4;i++)
        {
            helperCamere.insertData("Camera"+i,"Ocupat", "");
        }
        for(int i=5;i<=6;i++)
        {
            helperCamere.insertData("Camera"+i,"In reparatii", "AVEM NEVOIE DE PROSOAPE");
        }

        String nume = "Bartos";
        String prenume = "Bogdan";
        if(!helper.checkNumePrenume(nume,prenume) )
            helper.insertData(nume,prenume,"office@hotelstop.ro","manager123", "manager");
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String username = numeReg.getText().toString();
                String parola = parolaReg.getText().toString();

                boolean isAuthenticated = helper.authenticateUser(username, parola);


                if(username.equals("") || parola.equals("")) {
                    Toast.makeText(LoginActivity.this, "Toate campurile trebuie completate", Toast.LENGTH_SHORT).show();

                }else {
                    Boolean verificareDate = helper.checkUsernamePassword(username, parola);
                    if (verificareDate) {
                        if (isAuthenticated) {

                            SharedPreferences preferences = getSharedPreferences("user_logat", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("username", username);
                            editor.putString("password", parola);
                            editor.apply();

                            Toast.makeText(LoginActivity.this, "Conectarea a reusit!", Toast.LENGTH_SHORT).show();
                            Boolean verificareStatus = helper.checkStatus(username,"manager");
                            if(verificareStatus){
                                Intent i = new Intent(getApplicationContext(),ManagerActivity.class);
                                startActivity(i);
                            }
                            else{
                                Intent i = new Intent(getApplicationContext(), UserActivity.class);
                                startActivity(i);
                            }
                            numeReg.setText("");
                            parolaReg.setText("");
                        }
                    } else{
                        Toast.makeText(LoginActivity.this, "Numele utilizatorului sau parola sunt gresite!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
}
