package com.smadia.jangka.Models.Online;

import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonParser;
import com.smadia.jangka.Models.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Daerah extends Models<Daerah> {

    protected String table = "daerah";

    private int id;

    private String nama;

    public Daerah() {

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

    @Override
    public ArrayList<Daerah> all(JSONArray jsonArray) {
        ArrayList<Daerah> daftarDaerah = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            Daerah daerah = new Daerah();
            try {
                daerah.setPropetyFromJsonObject(jsonArray.getJSONObject(i));
                daftarDaerah.add(daerah);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return daftarDaerah;
    }

    public ArrayList<Daerah> all(JsonFetcher jsonFetcher) {
        return this.all(jsonFetcher.getJsonArray());
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }
}
