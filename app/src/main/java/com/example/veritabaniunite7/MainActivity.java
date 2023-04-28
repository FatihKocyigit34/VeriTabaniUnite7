package com.example.veritabaniunite7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {
    Button uyg1,uyg2,uyg3,uyg4;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDB();
        insertDB();
        updateDB();

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

    private void createDB() {
        database = this.openOrCreateDatabase("Urun", MODE_PRIVATE, null);
        String TABLO = "CREATE TABLE IF NOT EXISTS urunler(id INTEGER PRIMARY KEY, " +
                "urunadi TEXT, " +
                "fiyat DOUBLE, " +
                "adet INTEGER)";

        database.execSQL(TABLO);
    }

    private void insertDB()
    {
        SQLiteStatement results = database.compileStatement("INSERT INTO urunler (urunadi,fiyat,adet) VALUES (?,?,?)");
        results.bindString(1,"Bilgisayar");
        results.bindDouble(2,1500);
        results.bindLong(3,10);
        results.execute();
    }
    private void updateDB()
    {
        SQLiteStatement results = database.compileStatement("UPDATE urunler SET urunadi = ?, fiyat = ? WHERE id = ?");
        results.bindString(1,"leptop");
        results.bindDouble(2,2000);
        results.bindLong(3,1);
        results.execute();
    }
}