package com.smadia.jangka.Models.Offline;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.smadia.jangka.Models.OfflineModel;
import com.smadia.jangka.Models.OfflineModelInterface;

import org.w3c.dom.Text;

import java.sql.Blob;

public class User extends OfflineModel implements OfflineModelInterface {

    private  int id;

    private String username;

    private String email;

    private Blob avatar;

    private boolean login;

    public static String table ="user";

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user ("
                + "id integer PRIMARY KEY AUTOINCREMENT, "
                + "username string, "
                + "email string, "
                + "avatar blob, "
                + "login boolean );");
    }

    @Override
    public void deleteTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }

}
