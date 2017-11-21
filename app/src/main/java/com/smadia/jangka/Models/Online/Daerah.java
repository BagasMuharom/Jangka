package com.smadia.jangka.Models.Online;

import com.smadia.jangka.JSON.JsonParser;
import com.smadia.jangka.Models.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Daerah extends Models {

    protected String table = "daerah";

    private int id;

    private String nama;

    public Daerah(int id) {
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

    public ArrayList<Berita> daftarberita() {
        ArrayList<Berita> daftarBerita = new ArrayList<>();
        JSONArray daftarBeritaJson = this.getJsonArray(this.table + "/" + this.id + "berita", "");
        JsonParser jsonParser = new JsonParser(daftarBeritaJson);
        ArrayList<JSONObject> jsonObjects = jsonParser.getJsonObjects();

        for (int i = 0; i < jsonObjects.size(); i++) {
            Berita berita = new Berita();
            berita.setPropetyFromJsonObject(jsonObjects.get(i));
            daftarBerita.add(berita);
        }

        return daftarBerita;
    }

}
