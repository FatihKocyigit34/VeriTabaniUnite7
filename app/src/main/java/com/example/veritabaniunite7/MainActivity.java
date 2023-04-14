package com.example.veritabaniunite7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button uyg1,uyg2,uyg3,uyg4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uyg1 = findViewById(R.id.btn1);
        uyg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uyg = new Intent(MainActivity.this, Uygulama1.class);
                startActivity(uyg);
            }
        });

        uyg2 = findViewById(R.id.btn2);
        uyg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uyg = new Intent(MainActivity.this, Uygulama2.class);
                startActivity(uyg);
            }
        });

        uyg3 = findViewById(R.id.btn3);
        uyg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uyg = new Intent(MainActivity.this, Uygulama3.class);
                startActivity(uyg);
            }
        });

        uyg4 = findViewById(R.id.btn4);
        uyg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uyg = new Intent(MainActivity.this, Uygulama4.class);
                startActivity(uyg);
            }
        });
    }
}