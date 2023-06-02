package com.example.veritabaniunite7;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Uygulama3VeriEkleme extends AppCompatActivity {
    private static final int GALERI_SEC = 1;
    private static final int KAMERA_SEC = 2;

    SQLiteDatabase database;

    EditText urunadi, urunfiyat, urunadet;
    ImageView imageView;
    boolean islem = false;
    int id = 0;

    private void tanimlamalar() {
        urunadi = findViewById(R.id.urunAdiTxt);
        urunfiyat = findViewById(R.id.urunFiyatiTxt);
        urunadet = findViewById(R.id.urunAdediTxt);
        imageView = findViewById(R.id.imageView);
        database = openOrCreateDatabase("database",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS veriler (id INTEGER PRIMARY KEY, urunadi TEXT, urunfiyat TEXT, urunadet TEXT, urunresim BLOB)");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uyg3_kayitve_duzenleme);
        tanimlamalar();
        Intent i = getIntent();
        islem = i.getBooleanExtra("durum",false);
        if (!islem) {
            id = i.getIntExtra("urunid",0);
            urunadi.setText(i.getStringExtra("urunadi"));
            urunfiyat.setText(i.getStringExtra("urunfiyat"));
            urunadet.setText(i.getStringExtra("urunadet"));
        }
    }

    public void fotografsec(View view) {
        String[] secimler = {"Galeriden Seç", "Kameradan Çek"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fotoğraf Seçimi");
        builder.setItems(secimler, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    galeridenFotografSec();
                } else if (which == 1) {
                    kameradanFotografCek();
                }
            }
        });
        builder.show();
    }

    private void galeridenFotografSec() {
        Intent galeriIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galeriIntent, GALERI_SEC);
    }

    private void kameradanFotografCek() {
        Intent kameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(kameraIntent, KAMERA_SEC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALERI_SEC && data != null) {
                Uri fotoUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fotoUri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == KAMERA_SEC && data != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public void KaydetIslem(View view) {
        String uadi = urunadi.getText().toString(),
                ufiyat = urunfiyat.getText().toString(),
                uadet = urunadet.getText().toString();
        Drawable drawable = imageView.getDrawable();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] uresim = byteArrayOutputStream.toByteArray();
        if (islem) {
            SQLiteStatement sqLiteStatement = database.compileStatement("INSERT INTO veriler (urunadi, urunfiyat, urunadet, urunresim) VALUES (?,?,?,?)");
            sqLiteStatement.bindString(1, uadi);
            sqLiteStatement.bindString(2, ufiyat);
            sqLiteStatement.bindString(3, uadet);
            sqLiteStatement.bindBlob(4, uresim);
            sqLiteStatement.execute();
            Toast.makeText(this, "Başarıyla ürün eklendi.", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteStatement sqLiteStatement = database.compileStatement("UPDATE veriler SET urunadi = ?, urunfiyat = ?, urunadet = ?, urunresim = ? WHERE id = ?");
            sqLiteStatement.bindString(1, uadi);
            sqLiteStatement.bindString(2, ufiyat);
            sqLiteStatement.bindString(3, uadet);
            sqLiteStatement.bindBlob(4, uresim);
            sqLiteStatement.bindLong(5,id);
            sqLiteStatement.execute();
            Toast.makeText(this, "Başarıyla ürün güncellendi..", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnBack(View view) {
        Intent i = new Intent(this,Uygulama3.class);
        startActivity(i);
    }
}
