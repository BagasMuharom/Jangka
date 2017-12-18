package com.smadia.jangka.Models;

import android.database.sqlite.SQLiteDatabase;

abstract public class OfflineModel {

    public SQLiteDatabase writableDB;

    public OfflineModel() {
        this.writableDB = JangkaDatabaseHelper.writableDB;
    }

    public abstract boolean save(JangkaDatabaseHelper databaseHelper);

}
