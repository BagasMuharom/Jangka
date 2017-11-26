package com.smadia.jangka.Models.Online;

import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonParser;
import com.smadia.jangka.Models.Models;
import com.smadia.jangka.Models.Online.Relationship.Komentar;
import com.smadia.jangka.Util.DateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Berita extends Models {

    protected String table = "berita";

    private int id;

    private String judul = "";

    private String isi = "";

    private Daerah lokasi;

    private boolean berita_nasional;

    private DateFormat created_at;

    private DateFormat updated_at;

    private int dilihat;

    private int like;

    private int dislike;

    private int bookmark;

    public Berita() {}

    public Berita(int id) {
        JSONObject jsonObject = this.getJsonObject(this.table + '/' + id, "" , 0);
        setPropetyFromJsonObject(jsonObject);
    }

    public static Berita find(int id) {
        return new Berita(id);
    }

    public ArrayList<Berita> all() {
        JSONArray jsonArray = this.getJsonArray(this.table, "");
        ArrayList<Berita> daftarBerita = new ArrayList<>();
        JsonParser jsonParser = new JsonParser(jsonArray);
        ArrayList<JSONObject> jsonObjects = jsonParser.getJsonObjects();

        for (int i = 0; i < jsonObjects.size(); i++) {
            Berita berita = new Berita();
            berita.setPropetyFromJsonObject(jsonObjects.get(i));
            daftarBerita.add(berita);
        }

        return daftarBerita;
    }

    public void setPropetyFromJsonObject(JSONObject jsonObject) {
        try {
            this.judul = jsonObject.getString("judul");
            this.id = Integer.parseInt(jsonObject.getString("id"));
            this.isi = jsonObject.getString("isi");
            this.berita_nasional = Boolean.parseBoolean(jsonObject.getString("berita_nasional"));
            this.dilihat = Integer.parseInt(jsonObject.getString("dilihat"));
//            this.lokasi = new Daerah(Integer.parseInt(jsonObject.getString("lokasi")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Komentar> daftarKomentar() {
        ArrayList<Komentar> daftarKomentar = new ArrayList<>();
        JSONArray jsonArray = this.getJsonArray(this.table + '/' + this.id + "/komentar", "");
        JsonParser jsonParser = new JsonParser(jsonArray);
        ArrayList<JSONObject> jsonObjects = jsonParser.getJsonObjects();

        for(int i = 0; i < jsonObjects.size(); i++) {
            try {
                User user = new User(Integer.parseInt(jsonObjects.get(i).getString("user")));
                Komentar komentar = new Komentar(this, user, jsonObjects.get(i).getString("isi"));
                daftarKomentar.add(komentar);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return daftarKomentar;
    }

    public ArrayList<Kategori> daftarKategori() {
        ArrayList<Kategori> daftarKategori = new ArrayList<>();
        JSONArray jsonArray = this.getJsonArray(this.table + '/' + this.id + "/kategori", "");
        JsonParser jsonParser = new JsonParser(jsonArray);
        ArrayList<JSONObject> jsonObjects = jsonParser.getJsonObjects();

        for (int i = 0; i < jsonObjects.size(); i++) {
            Kategori kategori = new Kategori();
            kategori.setPropetyFromJsonObject(jsonObjects.get(i));
            daftarKategori.add(kategori);
        }

        return daftarKategori;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getIsi() {
        return isi;
    }

    public Daerah getLokasi() {
        return this.lokasi;
    }

    public boolean isBerita_nasional() {
        return berita_nasional;
    }

    public DateFormat getCreated_at() {
        return created_at;
    }

    public DateFormat getUpdated_at() {
        return updated_at;
    }

    public int getDilihat() {
        return dilihat;
    }

    public boolean addBookmarker(User user) {
        JSONArray jsonArray = this.getJsonArray("tambah/bookmarker/" + user.getId(), "");
        JsonParser jsonParser = new JsonParser(jsonArray);
        ArrayList<JSONObject> response= jsonParser.getJsonObjects();

        try {
            return (Integer.parseInt(response.get(0).getString("status")) == 200);
        } catch (JSONException e) {
            return false;
        }
    }

}
