package com.example.veritabaniunite7;

public class Uygulama3GT {
    public int id;
    public String urunadi;
    public String urunfiyat;
    public String urunadet;
    public byte[] urunresim;

    public Uygulama3GT(int gurunID, String gurunadi, String gurunfiyat, String gurunadet, byte[] gurunresim) {
        this.id = gurunID;
        this.urunadi = gurunadi;
        this.urunfiyat = gurunfiyat;
        this.urunadet = gurunadet;
        this.urunresim = gurunresim;
    }

    public int getId() {
        return id;
    }

    public String getUrunadi() {
        return urunadi;
    }

    public String getUrunfiyat() {
        return urunfiyat;
    }

    public String getUrunadet() {
        return urunadet;
    }

    public byte[] getUrunresim() {
        return urunresim;
    }
}
