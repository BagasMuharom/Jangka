package com.smadia.jangka.Models.Offline;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.smadia.jangka.Models.OfflineModel;
import com.smadia.jangka.Models.OfflineModelInterface;

import org.w3c.dom.Text;

import java.sql.Timestamp;

public class Berita extends OfflineModel implements OfflineModelInterface{

    private int user;

    private int id;

    private String judul;

    private Text isi;

    private String lokasi;

    private String reporter;

    private Timestamp tglBuat;

    private Bitmap gambar;

    public static String table ="berita";

    public Berita(int id)
    {

    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS berita ("
                + "id integer PRIMARY KEY AUTOINCREMENT, "
                + "judul string, "
                + "isi text, "
                + "lokasi string, "
                + "reporter string, "
                + "tglBuat timestamp );");
    }

    @Override
    public void deleteTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS berita");
    }

}
