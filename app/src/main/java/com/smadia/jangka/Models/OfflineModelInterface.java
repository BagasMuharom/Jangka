package com.smadia.jangka.Models;

import android.database.sqlite.SQLiteDatabase;

public interface OfflineModelInterface {

    /**
     * Membuat tabel
     */
    public void createTable(SQLiteDatabase db);

    /**
     * Menghapus tabel
     */
    public void deleteTable(SQLiteDatabase db);

}
