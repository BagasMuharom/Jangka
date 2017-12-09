package com.smadia.jangka.Models.Online;

import com.smadia.jangka.Models.Models;
import com.smadia.jangka.Util.DateFormat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Sejarah extends Models<Sejarah> {

    protected String table = "sejarah";

    private int id;

    private String judul;

    private String isi;

    private DateFormat tgl_terjadi;

    private DateFormat created_at;

    private DateFormat updated_at;

    public Sejarah() {

    }

    public Sejarah(int id) {

    }

    @Override
    public void setPropetyFromJsonObject(JSONObject jsonObject) {

    }

    @Override
    public ArrayList<Sejarah> all(JSONArray jsonArray) {
        return null;
    }

}
