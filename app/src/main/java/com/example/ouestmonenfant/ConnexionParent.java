package com.example.ouestmonenfant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConnexionParent extends AppCompatActivity {

    private EditText login, password ;
    private Button btnConnexion,btnInscription;

    private Database database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_connexion_act);

        this.login = findViewById(R.id.parent_login);
        this.password = findViewById(R.id.parent_password);
        this.btnConnexion = findViewById(R.id.btnConnexionParent);
        this.btnInscription = findViewById(R.id.btnInscription);

        database = new Database(this);

        //SIGN IN
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log = login.getText().toString();
                String pwd = password.getText().toString();

                // while fields are empty won't pass to another activity
                if (log.equals("") || pwd.equals("")){
                    Toast.makeText(ConnexionParent.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else { //Verify if user is in the database
                    Boolean checkUser = database.checkEnfantPassword(log,pwd);
                    if (checkUser == false){
                        Toast.makeText(ConnexionParent.this, "Incorrect, you must register", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(ConnexionParent.this, InterfaceParent.class);
                        startActivity(intent);
                    }
                    Boolean userMatchPwd = database.checkEnfantUserName(log);
                    if (userMatchPwd==false){
                        Toast.makeText(ConnexionParent.this, "User name or password incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*
        To register on the application
         */
        this.btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConnexionParent.this, Inscription.class);
                startActivity(intent);
            }
        });
    }
}
