package com.smadia.jangka.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JangkaDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME ="Jangka";

    public static SQLiteDatabase writeableDatabase;

    public static SQLiteDatabase readableDatabase;

    public JangkaDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        JangkaDatabaseHelper.readableDatabase = this.getReadableDatabase();
        JangkaDatabaseHelper.writeableDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
