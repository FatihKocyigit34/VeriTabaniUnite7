package com.example.veritabaniunite7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class Uygulama2 extends AppCompatActivity {
    RadioButton radioBtnDark, radioBtnLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uygulama2);
        radioBtnDark = findViewById(R.id.radioBtnDark);
        radioBtnLight = findViewById(R.id.radioBtnLight);

    }
}