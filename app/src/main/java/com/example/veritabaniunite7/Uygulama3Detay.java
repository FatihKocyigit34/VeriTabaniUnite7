package com.example.veritabaniunite7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Uygulama3Detay extends AppCompatActivity {

    int id = 0;
    String uadi,ufiyat,uadet;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uygulama3_detay);
        database = openOrCreateDatabase("database",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS veriler (id INTEGER PRIMARY KEY, urunadi TEXT, urunfiyat TEXT, urunadet TEXT, urunresim BLOB)");
        Intent i = getIntent();
        TextView urunadi = findViewById(R.id.detay_urunadi);
        TextView urunfiyat = findViewById(R.id.detay_urunfiyat);
        TextView urunadet = findViewById(R.id.detay_urunadet);
        ImageView urunresim = findViewById(R.id.detay_resim);
        uadi = i.getStringExtra("urunadi");
        ufiyat = i.getStringExtra("urunfiyat");
        uadet = i.getStringExtra("urunadet");
        urunadi.setText("ürün adı: "+i.getStringExtra("urunadi"));
        urunfiyat.setText("ürün fiyatı: "+i.getStringExtra("urunfiyat"));
        urunadet.setText("ürün adedi: "+i.getStringExtra("urunadet"));
        byte[] resim = i.getByteArrayExtra("urunresim");
        id = i.getIntExtra("urunid",0);
        if (resim == null)
            urunresim.setImageResource(R.drawable.ic_launcher_background);
        else {
            Bitmap bitmap = BitmapFactory.decodeByteArray(resim,0,resim.length);
            urunresim.setImageBitmap(bitmap);
        }
    }

    public void urunguncelle(View view) {
        Intent i = new Intent(this,Uygulama3VeriEkleme.class);
        i.putExtra("urunid",id);
        i.putExtra("urunadi",uadi);
        i.putExtra("urunfiyat",ufiyat);
        i.putExtra("urunadet",uadet);
        i.putExtra("durum",false);
        startActivity(i);
    }

    public void urunsil(View view) {
        SQLiteStatement sqLiteStatement = database.compileStatement("DELETE FROM veriler WHERE id = ?");
        sqLiteStatement.bindLong(1,id);
        sqLiteStatement.execute();
        Toast.makeText(this, "Silindi.", Toast.LENGTH_SHORT).show();
    }
}