package com.smadia.jangka.Models.Online;

import com.smadia.jangka.JSON.JsonParser;
import com.smadia.jangka.Models.Models;
import com.smadia.jangka.Util.DateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User extends Models {

    protected String table = "user";

    private int id;

    private String username;

    private String avatar;

    private String email;

    private DateFormat created_at;

    private DateFormat updated_at;

    public User() {

    }

    public User(int id) {
        super(id);
    }

    public static User find(int id) {
        return new User(id);
    }

    @Override
    public void setPropetyFromJsonObject(JSONObject jsonObject) {
        try {
            this.id = Integer.parseInt(jsonObject.getString("id"));
            this.username = jsonObject.getString("username");
            this.avatar = jsonObject.getString("avatar");
            this.email = jsonObject.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Berita> bookmark() {
        ArrayList<Berita> daftarBerita = new ArrayList<>();
        JSONArray jsonArray = this.getJsonArray(this.table + '/' + this.id + "/bookmark", "");
        JsonParser jsonParser = new JsonParser(jsonArray);
        ArrayList<JSONObject> jsonObjects = jsonParser.getJsonObjects();

        for(int i = 0; i < jsonObjects.size(); i++) {
            Berita berita = new Berita();
            berita.setPropetyFromJsonObject(jsonObjects.get(i));
            daftarBerita.add(berita);
        }

        return daftarBerita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateFormat getCreated_at() {
        return created_at;
    }

    public void setCreated_at(DateFormat created_at) {
        this.created_at = created_at;
    }

    public DateFormat getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(DateFormat updated_at) {
        this.updated_at = updated_at;
    }
}
