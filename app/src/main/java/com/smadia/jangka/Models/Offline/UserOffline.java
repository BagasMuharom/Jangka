package com.smadia.jangka.Models.Offline;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.smadia.jangka.Models.JangkaDatabaseHelper;
import com.smadia.jangka.Models.OfflineModel;
import com.smadia.jangka.Models.OfflineModelInterface;
import com.smadia.jangka.Models.Online.User;

import java.util.ArrayList;

public class UserOffline extends OfflineModel implements OfflineModelInterface {

    private int id;

    private int idOnline;

    private String username;

    private String email;

    private byte[] avatar;

    private boolean login;

    public static UserOffline activeUser;

    public String table ="user";

    public UserOffline() {
        super();
    }

    @Override
    public boolean save(JangkaDatabaseHelper databaseHelper) {
        if(this.getId() == -1) {
            return this.insert(databaseHelper.getWritableDatabase(), this.getIdOnline(), this.getUsername(), this.getEmail(), null, true);
        }
        else {

        }

        return true;
    }

    public UserOffline(User user) {
        this.id = -1;
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.login = true;
        this.idOnline = user.getId();
    }

    public UserOffline(int id, JangkaDatabaseHelper helper) {
        SQLiteDatabase db = helper.getReadableDatabase();
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user ("
                + "id integer PRIMARY KEY AUTOINCREMENT, "
                + "idOnline INTEGER, "
                + "username string, "
                + "email string, "
                + "avatar blob, "
                + "login boolean );");
    }

    @Override
    public void deleteTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }

    @Override
    public void setFromCursor(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.idOnline = cursor.getInt(1);
        this.username = cursor.getString(2);
        this.email = cursor.getString(3);
        this.avatar = cursor.getBlob(4);
        this.login = Boolean.parseBoolean(cursor.getString(5));
    }

    public void setActiveUser(int id, JangkaDatabaseHelper helper) {
        activeUser = new UserOffline(id, helper);
    }

    public UserOffline getActiveUser() {
        return activeUser;
    }

    public ArrayList<BeritaOffline> getBeritaTerunduh() {
        ArrayList<BeritaOffline> daftarBeritaTerunduh = new ArrayList<>();

        return daftarBeritaTerunduh;
    }

    /**
     * Memasukkan data user baru ke database local
     * @param idOnline
     * @param username
     * @param email
     * @param avatar
     * @param loggedIn
     * @return
     */
    public boolean insert(SQLiteDatabase database, int idOnline, String username, String email, Bitmap avatar, boolean loggedIn) {

        //byte[] avatarByte = ImageGetter.getImageByte(avatar);

        ContentValues values = new ContentValues();
        //values.put("image", avatarByte);
        values.put("idOnline", idOnline);
        values.put("username", username);
        values.put("email", email);
        values.put("login", loggedIn);

        database.insert(this.table, null, values);

        return true;
    }

    /**
     * Menghapus user dengan id tertentu
     * @return
     */
    public boolean delete(JangkaDatabaseHelper helper) {
        SQLiteDatabase db = helper.getWritableDatabase();

        return db.delete("user", "id = ?", new String[] {String.valueOf(this.id)}) > 0;
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

    public static void initLoggedInUser(JangkaDatabaseHelper helper) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM user where login = 1", null, null);

        if(cursor.moveToFirst()) {
            UserOffline userOffline = new UserOffline();
            userOffline.setFromCursor(cursor);
            UserOffline.activeUser = userOffline;
        }
        else {
            UserOffline.activeUser = null;
        }

        cursor.close();
        db.close();
    }

    public int getId() {
        return id;
    }

    public int getIdOnline() {
        return idOnline;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getAvatar() {
        return avatar;
    }
}
