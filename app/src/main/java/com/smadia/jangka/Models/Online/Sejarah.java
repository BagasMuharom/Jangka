package com.smadia.jangka.Models.Online;

import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.Models.Models;
import com.smadia.jangka.Util.DateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Sejarah extends Models<Sejarah> {

    protected String table = "sejarah";

    private int id;

    private String judul;

    private String isi;

    private String tgl_terjadi;

    private DateFormat created_at;

    private DateFormat updated_at;

    public Sejarah() {

    }

    public Sejarah(JSONObject jsonObject) {
        this.setPropetyFromJsonObject(jsonObject);
    }

    public Sejarah(int id) {

    }

    @Override
    public void setPropetyFromJsonObject(JSONObject jsonObject) {
        try {
            this.judul = jsonObject.getString("judul");
            this.isi = jsonObject.getString("isi");
            this.tgl_terjadi = jsonObject.getString("tgl_terjadi");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Sejarah> all(JSONArray jsonArray) {
        ArrayList<Sejarah> daftarSejarah = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                Sejarah sejarah = new Sejarah(jsonArray.getJSONObject(i));
                daftarSejarah.add(sejarah);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return daftarSejarah;
    }

    public ArrayList<Sejarah> all(JsonFetcher jsonFetcher) {
        return this.all(jsonFetcher.getJsonArray());
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getTglTerjadi() {
        return tgl_terjadi;
    }

    public String getIsi() {
        return isi;
    }
}
