package com.example.ouestmonenfant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Inscription extends AppCompatActivity {
    private EditText prenom, nom, email, parent_login, parent_pwd, enfant_login, enfant_pwd;
    private Button btn_valider;

    private Database database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        prenom = (EditText) findViewById(R.id.prenom_parent);
        nom = (EditText) findViewById(R.id.nom_parent);
        email = (EditText) findViewById(R.id.email_parent);
        parent_login = (EditText) findViewById(R.id.parent_login);
        parent_pwd = (EditText) findViewById(R.id.parent_password);
        enfant_login = (EditText) findViewById(R.id.enfant_login);
        enfant_pwd = (EditText) findViewById(R.id.enfant_password);
        btn_valider = (Button) findViewById(R.id.btnValider);

        database = new Database(this);

        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = prenom.getText().toString();
                String n = nom.getText().toString();
                String e = email.getText().toString();
                String loginParent = parent_login.getText().toString();
                String pwdParent = parent_pwd.getText().toString();
                String loginEnfant = enfant_login.getText().toString();
                String pwdEnfant = enfant_pwd.getText().toString();

                // while fields are empty won't pass to another activity
                if (p.equals("") || n.equals("") || e.equals("") || loginParent.equals("") || pwdParent.equals("") || loginEnfant.equals("") || pwdEnfant.equals("")){
                    Toast.makeText(Inscription.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{ //if not empty we create a new user
                    Boolean insert = database.insertData_parent(loginParent,pwdParent,p,n,e,loginEnfant,pwdEnfant);
                    if (insert==true)
                        Toast.makeText(Inscription.this, "Registered successfully, now you can log in !!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Inscription.this, "Registration failed, please try again !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}