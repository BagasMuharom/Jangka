package com.smadia.jangka.Models.Offline;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.smadia.jangka.Models.JangkaDatabaseHelper;
import com.smadia.jangka.Models.OfflineModel;
import com.smadia.jangka.Models.OfflineModelInterface;
import com.smadia.jangka.Util.ImageGetter;

import org.w3c.dom.Text;

import java.sql.Blob;
import java.sql.Timestamp;

public class BeritaOffline extends OfflineModel implements OfflineModelInterface{

    private int user;

    private int id;

    private String judul;

    private Text isi;

    private String lokasi;

    private String reporter;

    private Timestamp tglBuat;

    private Blob gambar;

    public String table ="berita";

    public BeritaOffline() {}

    @Override
    public boolean save(JangkaDatabaseHelper databaseHelper) {
        return false;
    }

    public BeritaOffline(int id)
    {

    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS berita ("
                + "id integer PRIMARY KEY AUTOINCREMENT, "
                + "idOnline integer, "
                + "judul string, "
                + "isi text, "
                + "lokasi int, "
                + "thumbnail blob, "
                + "tglBuat timestamp );");

        db.execSQL("CREATE TABLE IF NOT EXISTS kategori_berita ("
                + "id_berita INTEGER, "
                + "id_kategori INTEGER "
                + ");");
    }

    public boolean insert(com.smadia.jangka.Models.Online.Berita berita) {
        SQLiteDatabase db = this.writableDB;

        ContentValues values = new ContentValues();
        values.put("idOnline", berita.getId());
        values.put("judul", berita.getJudul());
        values.put("isi", berita.getIsi());
        values.put("thumbnail", ImageGetter.getImageByte(berita.getThumbnail()));
        values.put("lokasi", berita.getLokasi().getId());

        return db.insert(this.table, null, values) > 0;
    }

    @Override
    public void deleteTable(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS berita");
    }

    @Override
    public void setFromCursor(Cursor cursor) {

    }

}
