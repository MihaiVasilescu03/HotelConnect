package com.example.hotelconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RemoveUserActivity extends AppCompatActivity {

    EditText numeDel,prenumeDel;

    DBHelper helper;
    Button btnDel;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_user_menu);

        numeDel = (EditText) findViewById(R.id.removeNume);
        prenumeDel = (EditText) findViewById(R.id.removePrenume);

        helper = new DBHelper(this);

        btnDel = (Button) findViewById(R.id.removeUserButton_2);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> onBackPressed());
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nume = numeDel.getText().toString();
                String prenume = prenumeDel.getText().toString();

                if(nume.equals("Bartos") && prenume.equals("Bogdan"))
                    Toast.makeText(RemoveUserActivity.this, "Contul acesta nu poate fi sters!", Toast.LENGTH_SHORT).show();
                else
                if(nume.equals("")||prenume.equals("")){
                    Toast.makeText(RemoveUserActivity.this, "Toate campurile trebuie completate!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean numePrenume = helper.checkNumePrenume(nume,prenume);
                    if(numePrenume == true)
                    {
                        Toast.makeText(RemoveUserActivity.this, "Contul a fost sters cu succes!", Toast.LENGTH_SHORT).show();
                        helper.deleteData(nume,prenume);
                        numeDel.setText("");
                        prenumeDel.setText("");
                    }
                    else {
                        Toast.makeText(RemoveUserActivity.this, "Persoana nu a putut fi gasita in baza de date!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



    }
}

