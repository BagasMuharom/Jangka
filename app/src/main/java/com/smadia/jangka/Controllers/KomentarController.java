package com.smadia.jangka.Controllers;

import android.support.v7.app.AppCompatActivity;

import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Models.Online.User;
import com.smadia.jangka.Request.KirimKomentarRequest;
import com.smadia.jangka.Views.KomentarActivity;

public class KomentarController {

    private KomentarActivity activity;

    public KomentarController(KomentarActivity activity) {
        this.activity = activity;
    }

    public void kirimKomentar(User user, String komentar, Berita berita) {
        KirimKomentarRequest request = new KirimKomentarRequest(user, komentar, berita, this.activity);
        request.send();
    }

}
