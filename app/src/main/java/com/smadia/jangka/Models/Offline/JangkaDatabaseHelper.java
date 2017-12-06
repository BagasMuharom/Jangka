package com.smadia.jangka.Models.Offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JangkaDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME ="Jangka";


    SQLiteDatabase db;


    private static final String TABLE_CREATE = "create table user (id integer primary key not null auto_increment, " +
            " username text not null, email text not null, avatar Bitmap not null); ";

    public JangkaDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


//    public JangkaDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        User.createTable(db);
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

//    public void insertContact(Contact c)
//    {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAMA, c.getNama());
//        values.put(COLUMN_EMAIL, c.getEmail());
//        values.put(COLUMN_USERNAME, c.getUsername());
//        values.put(COLUMN_PASSWORD, c.getPassword());
//
//        db.insert(TABLE_NAME, null, values);
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if ( oldVersion == 1)
        {

        }

        if (oldVersion < 3)
        {

        }

//        String query = "DROP TABLE IF EXIST" + TABLE_NAME;
//        db.execSQL(query);
//        this.onCreate(db);
    }

}
