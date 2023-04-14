package com.example.veritabaniunite7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Uygulama1 extends AppCompatActivity {
    EditText kullaniciadieditxt;
    Button btnUyg1Kaydet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uygulama1);
        btnUyg1Kaydet = findViewById(R.id.btnUyg1);
        kullaniciadieditxt = findViewById(R.id.editTxtUyg1);

        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String gelenveri = sharedPreferences.getString("kullanici","");
        if (!gelenveri.isEmpty())
        {
        kullaniciadieditxt.setText(gelenveri);
        }

        btnUyg1Kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String veri;
                veri = kullaniciadieditxt.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("kullanici", veri);
                editor.apply();
            }
        });
    }



    }

