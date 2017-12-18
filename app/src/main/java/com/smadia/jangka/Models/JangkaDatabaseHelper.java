package com.smadia.jangka.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smadia.jangka.Models.Offline.BeritaOffline;
import com.smadia.jangka.Models.Offline.DaerahOffline;
import com.smadia.jangka.Models.Offline.KategoriOffline;
import com.smadia.jangka.Models.Offline.UserOffline;

public class JangkaDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME ="Jangka";

    public static SQLiteDatabase writableDB;

    public JangkaDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        new BeritaOffline().createTable(db);
        new UserOffline().createTable(db);
        new KategoriOffline().createTable(db);
        new DaerahOffline().createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        new BeritaOffline().deleteTable(db);
        new UserOffline().deleteTable(db);
        new KategoriOffline().deleteTable(db);
        new DaerahOffline().deleteTable(db);
        this.onCreate(db);
    }

}
