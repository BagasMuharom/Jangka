package com.smadia.jangka.Models.Offline;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.smadia.jangka.Models.OfflineModel;
import com.smadia.jangka.Models.OfflineModelInterface;
import java.sql.Blob;
import java.util.ArrayList;

public class User extends OfflineModel implements OfflineModelInterface {

    private int id;

    private String username;

    private String email;

    private Blob avatar;

    private boolean login;

    private User activeUser;

    public static String table ="user";

    public User() {}

    public User(int id) {

    }

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

    public void setActiveUser(int id) {
        this.activeUser = new User(id);
    }

    public User getActiveUser() {
        return this.activeUser;
    }

    public ArrayList<Berita> getBeritaTerunduh() {
        ArrayList<Berita> daftarBeritaTerunduh = new ArrayList<>();

        return daftarBeritaTerunduh;
    }

    /**
     * Memasukkan data user baru ke database local
     * @param id
     * @param username
     * @param email
     * @param avatar
     * @param loggedIn
     * @return
     */
    public boolean insert(int id, String username, String email, Bitmap avatar, boolean loggedIn) {
        return true;
    }

    /**
     * Menghapus user dengan id tertentu
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return true;
    }

    /**
     * Mengubah data user
     * @param email
     * @param avatar
     * @return
     */
    public boolean update(String email, Bitmap avatar) {
        return true;
    }

    /**
     * Mengeset apakah user tertentu dianggap login atau tidak
     * @param loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.login = loggedIn;
    }

}
