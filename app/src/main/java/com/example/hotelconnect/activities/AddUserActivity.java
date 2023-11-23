package com.example.hotelconnect.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotelconnect.R;

import java.util.HashMap;
import java.util.Map;


public class AddUserActivity extends AppCompatActivity {

    EditText numeReg,prenumeReg,parolaReg,emailReg;
    String statusReg;
    Button btnSignUp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_menu);
        Spinner spinner = (Spinner) findViewById(R.id.role_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.role_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        numeReg = (EditText)findViewById(R.id.addsignupNume);
        prenumeReg = (EditText)findViewById(R.id.addsignupPrenume);
        emailReg = (EditText)findViewById(R.id.addsignupEmail);
        parolaReg = (EditText)findViewById(R.id.addsignupPassword);

        btnSignUp = (Button)findViewById(R.id.addUserButton);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> onBackPressed());
        btnSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String statusReg = spinner.getSelectedItem().toString();

                verifFields();

                RequestQueue requestQueue = Volley.newRequestQueue(AddUserActivity.this);

                //URL UNDE ADAUGAM
                String url = "http://10.206.2.12:9080/api/v1/angajati/register";

                // String request

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equalsIgnoreCase("Succes")) {
                                    numeReg.setText(null);
                                    emailReg.setText(null);
                                    prenumeReg.setText(null);
                                    parolaReg.setText(null);
                                    Toast.makeText(AddUserActivity.this, "Angajatul a fost adaugat cu succes!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddUserActivity.this, "Angajatul nu a putut fi adaugat, verificati daca acesta deja exista!", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("nume", numeReg.getText().toString());
                        params.put("prenume", prenumeReg.getText().toString());
                        params.put("email", emailReg.getText().toString());
                        params.put("password", parolaReg.getText().toString());
                        params.put("status", statusReg);

                        return params;
                    }
                };

                requestQueue.add(stringRequest);

            }
        });


    }

    public void verifFields(){
        if(!validateEmail() || !validateNume() || !validatePrenume() || !validatePassword()){
            validatePassword();
            validateNume();
            validateEmail();
            validatePrenume();
        }
    }


    public boolean validateNume(){
        String nume = numeReg.getText().toString();

        if(nume.isEmpty()){
            numeReg.setError("Acest camp trebuie completat!");
            return false;
        }
        else{
            numeReg.setError(null);
            return true;
        }
    }
    public boolean validatePrenume(){
        String prenume = prenumeReg.getText().toString();

        if(prenume.isEmpty()){
            prenumeReg.setError("Acest camp trebuie completat!");
            return false;
        }
        else{
            prenumeReg.setError(null);
            return true;
        }
    }
    public boolean validateEmail(){
        String email = emailReg.getText().toString();

        if(email.isEmpty()){
            emailReg.setError("Acest camp trebuie completat!");
            return false;
        }
        else{
            emailReg.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        String pass = parolaReg.getText().toString();

        if(pass.isEmpty()){
            parolaReg.setError("Acest camp trebuie completat!");
            return false;
        }
        else{
            parolaReg.setError(null);
            return true;
        }
    }

}
