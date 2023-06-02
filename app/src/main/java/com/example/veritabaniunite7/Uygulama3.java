package com.example.veritabaniunite7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Uygulama3 extends AppCompatActivity {

    SQLiteDatabase database;
    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uyg3);
        database = openOrCreateDatabase("database",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS veriler (id INTEGER PRIMARY KEY, urunadi TEXT, urunfiyat TEXT, urunadet TEXT, urunresim BLOB)");
    }

    @Override
    protected void onStart() {
        super.onStart();
        liste = findViewById(R.id.Uyg3ListView);
        ArrayList<Uygulama3GT> arraylist = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM veriler",null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int GurunID = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String Gurunadi = cursor.getString(cursor.getColumnIndex("urunadi"));
                @SuppressLint("Range") String Gurunfiyat = cursor.getString(cursor.getColumnIndex("urunfiyat"));
                @SuppressLint("Range") String Gurunadet = cursor.getString(cursor.getColumnIndex("urunadet"));
                @SuppressLint("Range") byte[] Gurunresim = cursor.getBlob(cursor.getColumnIndex("urunresim"));
                arraylist.add(new Uygulama3GT(GurunID,Gurunadi,Gurunfiyat,Gurunadet,Gurunresim));
            } while (cursor.moveToNext());
            Uygulama3Adapter adapter = new Uygulama3Adapter(arraylist,this);
            adapter.notifyDataSetChanged();
            liste.setAdapter(adapter);
        }
    }

    public void EkleClick(View view) {
        Intent i = new Intent(this,Uygulama3VeriEkleme.class);
        i.putExtra("durum",true);
        startActivity(i);
    }
}