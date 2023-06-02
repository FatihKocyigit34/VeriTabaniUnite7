package com.example.veritabaniunite7;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Uygulama3Adapter extends BaseAdapter {

    ArrayList<Uygulama3GT> arrayList;
    Context context;

    public Uygulama3Adapter(ArrayList<Uygulama3GT> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_uyg3listview,null);
        }
        Uygulama3GT urun = arrayList.get(i);
        TextView urunadi = view.findViewById(R.id.tasarimurunadi);
        TextView urunfiyat = view.findViewById(R.id.tasarimurunfiyati);
        TextView urunadet = view.findViewById(R.id.tasarimurunadedi);
        ImageView urunresim = view.findViewById(R.id.urunFotografi);
        if (urun.getUrunresim() == null)
            urunresim.setImageResource(R.drawable.ic_launcher_background);
        else {
            Bitmap bitmap = BitmapFactory.decodeByteArray(urun.getUrunresim(),0,urun.getUrunresim().length);
            urunresim.setImageBitmap(bitmap);
        }
        urunadi.setText(urun.getUrunadi());
        urunfiyat.setText(urun.getUrunfiyat());
        urunadet.setText(urun.getUrunadet());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,Uygulama3Detay.class);
                i.putExtra("urunid",urun.getId());
                i.putExtra("urunadi",urun.getUrunadi());
                i.putExtra("urunfiyat",urun.getUrunfiyat());
                i.putExtra("urunadet",urun.getUrunadet());
                i.putExtra("urunresim",urun.getUrunresim());
                context.startActivity(i);
            }
        });
        return view;
    }
}
