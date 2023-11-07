package com.example.hotelconnect;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> nume , prenume , status;
    DBHelper DB;
    MyAdapter_ShowUser adapter;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_user);

        DB = new DBHelper(this);
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
        Cursor cursor = DB.getData();
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
