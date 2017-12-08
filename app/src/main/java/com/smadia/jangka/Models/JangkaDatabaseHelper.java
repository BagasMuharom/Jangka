package com.smadia.jangka.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smadia.jangka.Controllers.AuthController;
import com.smadia.jangka.Models.Offline.Berita;
import com.smadia.jangka.Models.Offline.User;

public class JangkaDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME ="Jangka";

    SQLiteDatabase db;

    public JangkaDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        new Berita().createTable(db);
        new User().createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
