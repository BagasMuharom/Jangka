package com.smadia.jangka.Models.Online;

import com.smadia.jangka.JSON.JsonParser;
import com.smadia.jangka.Models.Models;
import com.smadia.jangka.Util.DateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Kategori extends Models {

    protected String table = "kategori";

    private int id;

    private String nama;

    private DateFormat created_at;

    private DateFormat updated_at;

    public Kategori() {

    }

    public Kategori(int id) {
        super(id);
    }

    @Override
    public void setPropetyFromJsonObject(JSONObject jsonObject) {
        try {
            this.id = Integer.parseInt(jsonObject.getString("id"));
            this.nama = jsonObject.getString("nama");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Berita> daftarBerita() {
        ArrayList<Berita> daftarBerita = new ArrayList<>();
        JSONArray jsonArray = this.getJsonArray(this.table + "/" + this.getId() + "/berita", "");
        JsonParser jsonParser = new JsonParser(jsonArray);
        ArrayList<JSONObject> jsonObjects = jsonParser.getJsonObjects();

        for(int i= 0; i < jsonObjects.size(); i++) {
            Berita berita = new Berita();
            berita.setPropetyFromJsonObject(jsonObjects.get(i));
            daftarBerita.add(berita);
        }

        return daftarBerita;
    }

    public int getId() {
        return this.id;
    }

    public String getNama() {
        return this.nama;
    }

    public DateFormat getCreated_at() {
        return this.created_at;
    }

    public DateFormat getUpdated_at() {
        return this.updated_at;
    }
}
