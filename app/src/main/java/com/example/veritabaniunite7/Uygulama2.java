package com.example.veritabaniunite7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Uygulama2 extends AppCompatActivity {
    RadioButton radioBtnDark, radioBtnLight;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int veri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uygulama2);
        radioBtnDark = findViewById(R.id.radioBtnDark);
        radioBtnLight = findViewById(R.id.radioBtnLight);

        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (veri == AppCompatDelegate.MODE_NIGHT_NO)
        {
            radioBtnLight.setChecked(true);
            radioBtnDark.setChecked(false);
        }
        else
        {
            radioBtnLight.setChecked(false);
            radioBtnDark.setChecked(true);
        }

        radioBtnLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton)view).isChecked();
                if (checked)
                {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putInt("tema", AppCompatDelegate.MODE_NIGHT_NO);
                editor.apply();
                radioBtnLight.setChecked(true);
                radioBtnDark.setChecked(false);
                }
            }
        });

        radioBtnDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton)view).isChecked();
                if (checked)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putInt("tema", AppCompatDelegate.MODE_NIGHT_YES);
                    editor.apply();
                    radioBtnLight.setChecked(false);
                    radioBtnDark.setChecked(true);
                }
            }
        });
    }
}