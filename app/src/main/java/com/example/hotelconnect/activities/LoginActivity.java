package com.example.hotelconnect.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotelconnect.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.loginUser);
        password = findViewById(R.id.loginPassword);

        btnLogin = findViewById(R.id.loginButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                authenticateUser();

                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

                //URL UNDE ADAUGAM
                String url = "http://192.168.1.92:9080/api/v1/angajati/login";

                HashMap<String, String> params = new HashMap<String, String>();

                params.put("username", username.getText().toString());
                params.put("password", password.getText().toString());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    String username = (String) response.get("username");
                                    String nume = (String) response.get("nume");
                                    String prenume = (String) response.get("prenume");
                                    String email = (String) response.get("email");
                                    String status = (String) response.get("status");

                                    Intent intent;
                                    if (status.equals("manager")) {
                                        intent = new Intent(getApplicationContext(), ManagerActivity.class);
                                    } else {
                                        intent = new Intent(getApplicationContext(), UserActivity.class);
                                    }

                                    intent.putExtra("username", username);
                                    intent.putExtra("password", password.getText().toString());
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    finish();
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    System.out.println(e.getMessage());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                System.out.println(error.getMessage());
                                Toast.makeText(LoginActivity.this, "Autentificare a esuat!", Toast.LENGTH_SHORT).show();
                            }
                        });
                requestQueue.add(jsonObjectRequest);

            }
        });

    }

    public void authenticateUser() {
        if (!validateUsername() || !validatePassword()) {
            validatePassword();
            validateUsername();
        }
    }

    public boolean validateUsername() {
        String usernm = username.getText().toString();

        if (usernm.isEmpty()) {
            username.setError("Acest camp trebuie completat!");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    public boolean validatePassword() {
        String pass = password.getText().toString();

        if (pass.isEmpty()) {
            password.setError("Acest camp trebuie completat!");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
}
