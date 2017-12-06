package com.smadia.jangka.Models.Offline;

import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Text;

import java.sql.Timestamp;

/**
 * Created by My Laptop on 11/21/2017.
 */

public class Berita {

    private int id;
    private String judul;
    private Text isi;
    private String lokasi;
    private String reporter;
    private Timestamp tglBuat;

    public static String table ="berita";

    public Berita(int id)
    {

    }

    public static void createTable(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE berita ("
                + "id integer PRIMARY KEY AUTOINCREMENT, "
                + "judul String, "
                + "isi Text, "
                + "lokasi String, "
                + "reporter String, "
                + "tglBuat TimeStamp );");
    }
}
