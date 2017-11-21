package com.smadia.jangka.Models.Online;

import com.smadia.jangka.Models.Models;
import com.smadia.jangka.Util.DateFormat;

import org.json.JSONObject;

public class Sejarah extends Models {

    private int id;

    private String judul;

    private String isi;

    private DateFormat tgl_terjadi;

    private DateFormat created_at;

    private DateFormat updated_at;

    public Sejarah() {

    }

    public Sejarah(int id) {
        super(id);
    }

    @Override
    public void setPropetyFromJsonObject(JSONObject jsonObject) {

    }

}
