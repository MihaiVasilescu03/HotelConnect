package com.example.hotelconnect;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> nume , prenume , status;
    MyAdapter_ShowUser adapter;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_user);

        RequestQueue requestQueue = Volley.newRequestQueue(ShowUserActivity.this);

        //URL UNDE ADAUGAM
        String url = "http://192.168.0.31:9080/api/v1/angajati/show";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){

                try {
                    response.
                    String nume = (String) response.getJSONArray()



                }
                catch (JSONException e){
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        nume = new ArrayList<>();
        prenume = new ArrayList<>();
        status = new ArrayList<>();
        recyclerView = findViewById(R.id.RecyclerView);
        adapter = new MyAdapter_ShowUser(this , nume , prenume , status);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> onBackPressed());

    }

    private void displayData() {
        Cursor cursor = .getData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "Baza de date este goala", Toast.LENGTH_SHORT).show();
        }else
        {
            while(cursor.moveToNext()){
                nume.add(cursor.getString(1));
                prenume.add(cursor.getString(2));
                status.add(cursor.getString(5));
            }
        }
    }
}
