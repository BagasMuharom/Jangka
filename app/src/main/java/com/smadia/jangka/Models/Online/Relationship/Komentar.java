package com.smadia.jangka.Models.Online.Relationship;

import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Models.Online.User;
import com.smadia.jangka.Util.DateFormat;

public class Komentar {

    private Berita berita;

    private User user;

    private DateFormat created_at;

    private DateFormat updated_at;

    private String isi;

    public Komentar(Berita berita, User user, String isi) {
        this.berita = berita;
        this.user = user;
        this.isi = isi;
    }

    public Berita getBerita() {
        return this.berita;
    }

    public User getUser() {
        return this.user;
    }

    public DateFormat getCreated_at() {
        return this.created_at;
    }

    public DateFormat getUpdated_at() {
        return this.updated_at;
    }

    public String getIsi() {
        return isi;
    }

}
