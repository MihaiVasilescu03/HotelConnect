package com.example.hotelconnect;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class AddUserActivity extends AppCompatActivity {

    EditText numeReg,prenumeReg,parolaReg,emailReg;
    Button btnSignUp;
    DBHelper helper;
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
        helper = new DBHelper(this);

        /*btnDel = (Button) findViewById(R.id.delButton2);

        helper = new DBHelper(this);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),DeleteActivity.class);
                startActivity(i);
            }
        });*/
        btnSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String nume = numeReg.getText().toString();
                String prenume = prenumeReg.getText().toString();
                String email = emailReg.getText().toString();
                String parola = parolaReg.getText().toString();
                String statusReg = spinner.getSelectedItem().toString();

                if(nume.equals("")||prenume.equals("")||email.equals("")||parola.equals(""))
                {
                    Toast.makeText(AddUserActivity.this, "Toate campurile trebuiesc completate", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean numePrenumeValid = helper.checkNumePrenume(nume,prenume);
                    if (numePrenumeValid){
                        Toast.makeText(AddUserActivity.this, "Aceasta persoana este deja inregistrata!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Boolean verificSignUp = helper.insertData(nume,prenume,email,parola,statusReg);
                        if(verificSignUp){
                            Toast.makeText(AddUserActivity.this, "Angajat adaugat cu succes!", Toast.LENGTH_SHORT).show();
                            numeReg.setText("");
                            prenumeReg.setText("");
                            emailReg.setText("");
                            parolaReg.setText("");
                        }
                        else {
                            Toast.makeText(AddUserActivity.this, "Adaugarea a esuat!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

    }
}

