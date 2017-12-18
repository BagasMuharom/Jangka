package com.smadia.jangka.Models.Offline;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smadia.jangka.Models.JangkaDatabaseHelper;
import com.smadia.jangka.Models.OfflineModel;
import com.smadia.jangka.Models.OfflineModelInterface;

public class KategoriOffline extends OfflineModel implements OfflineModelInterface {

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS kategori ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nama STRING"
                + ");");
    }

    @Override
    public void deleteTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS kategori");
    }

    @Override
    public void setFromCursor(Cursor cursor) {

    }

    @Override
    public boolean save(JangkaDatabaseHelper databaseHelper) {
        return false;
    }
}
