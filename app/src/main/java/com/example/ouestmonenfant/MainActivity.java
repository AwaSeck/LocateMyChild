package com.example.ouestmonenfant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton imgParent;
    private ImageButton imgEnfant;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imgParent = findViewById(R.id.imgBtnParent);
        this.imgEnfant = findViewById(R.id.imgBtnEnfant);

        this.imgParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConnexionParent.class);
                startActivity(intent);
            }
        });

        this.imgEnfant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConnexionEnfant.class);
                startActivity(intent);
            }
        });
    }

}