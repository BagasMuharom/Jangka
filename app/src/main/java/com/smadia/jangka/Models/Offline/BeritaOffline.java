package com.smadia.jangka.Models.Offline;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.smadia.jangka.Models.JangkaDatabaseHelper;
import com.smadia.jangka.Models.OfflineModel;
import com.smadia.jangka.Models.OfflineModelInterface;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Util.ImageGetter;

import java.sql.Timestamp;
import java.util.ArrayList;

public class BeritaOffline extends OfflineModel implements OfflineModelInterface{

    private int user;

    private int id;

    private String judul;

    private String isi;

    private String lokasi = "";

    private Timestamp tglBuat;

    private Bitmap gambar;

    private int idOnline;

    public String table ="berita";

    public BeritaOffline() {

    }

    @Override
    public boolean save(JangkaDatabaseHelper databaseHelper) {
        if (this.id == -1) {
            return this.insert(databaseHelper.getWritableDatabase(), this.judul, this.idOnline, this.isi, this.lokasi);
        }

        return false;
    }

    public boolean insert(SQLiteDatabase db, String judul, int idOnline, String isi, String lokasi) {
        ContentValues values = new ContentValues();
        values.put("idOnline", idOnline);
        values.put("judul", judul);
        values.put("isi", isi);
        //values.put("lokasi", lokasi);

        return db.insert(this.table, null, values) > 0;
    }

    public BeritaOffline(int id)
    {

    }

    public BeritaOffline(Berita berita) {
        this.id = -1;
        this.judul = berita.getJudul();
        this.isi = berita.getIsi();
        //this.lokasi = berita.getLokasi().getNama();
        this.idOnline = berita.getId();
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS berita ("
                + "id integer PRIMARY KEY AUTOINCREMENT, "
                + "idOnline integer, "
                + "judul string, "
                + "isi text, "
                + "lokasi string, "
                + "thumbnail blob, "
                + "tglBuat timestamp );");
    }

    public boolean insert(com.smadia.jangka.Models.Online.Berita berita) {
        SQLiteDatabase db = this.writableDB;

        ContentValues values = new ContentValues();
        values.put("idOnline", berita.getId());
        values.put("judul", berita.getJudul());
        values.put("isi", berita.getIsi());
        //values.put("thumbnail", ImageGetter.getImageByte(berita.getThumbnail()));
        values.put("lokasi", berita.getLokasi().getId());

        return db.insert(this.table, null, values) > 0;
    }

    public ArrayList<BeritaOffline> all(JangkaDatabaseHelper helper) {
        ArrayList<BeritaOffline> daftarBerita = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM berita", null);

        if(cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                BeritaOffline beritaOffline = new BeritaOffline();
                beritaOffline.setFromCursor(cursor);
            }
        }

        return daftarBerita;
    }

    @Override
    public void deleteTable(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS berita");
    }

    @Override
    public void setFromCursor(Cursor cursor) {
        this.idOnline = cursor.getInt(1);
        this.judul = cursor.getString(2);
        this.isi = cursor.getString(3);
        this.lokasi = cursor.getString(4);
        this.gambar = ImageGetter.getImageBitmap(cursor.getBlob(5));
    }

}
