package com.smadia.jangka.Models.Offline.Relationship;

import android.database.sqlite.SQLiteDatabase;

import com.smadia.jangka.Models.OfflineModel;
import com.smadia.jangka.Models.OfflineModelInterface;

public class BeritaTerunduh extends OfflineModel implements OfflineModelInterface {

    private int user;
    private int berita;

    private static String table ="berita_offline";

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS berita_offline ("
                + "user integer, "
                + "berita integer );");
    }

    @Override
    public void deleteTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS berita_offline");
    }
}
