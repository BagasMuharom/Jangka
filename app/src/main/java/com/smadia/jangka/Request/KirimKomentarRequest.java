package com.smadia.jangka.Request;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Models.Online.User;
import com.smadia.jangka.Views.KomentarActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class KirimKomentarRequest implements RequestInterface, Response.Listener<String>, Response.ErrorListener{

    private KomentarActivity activity;

    private Request request;

    private final String url = "http://192.168.55.2/jangka/public/komentar/tambah";

    public KirimKomentarRequest(User user, String komentar, Berita berita, KomentarActivity activity) {
        this.activity = activity;
        this.request = new Request(com.android.volley.Request.Method.POST, this.url, this, this);
        this.request.addParams("user", user.getId() + "");
        this.request.addParams("komentar", komentar);
        this.request.addParams("berita", berita.getId() + "");

        Toast.makeText(this.activity, "user : " + user.getId() + ", berita : " + berita.getId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void send() {
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(this.request);
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            boolean success = jsonResponse.getBoolean("success");

            if(success) {
                Toast.makeText(this.activity, "Berhasil mengirim komentar !", Toast.LENGTH_SHORT).show();
                this.activity.komentar.setText("");
            }
            else{
                Toast.makeText(this.activity, "Gagal mengirim komentar !", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this.activity, "Terjadi kesalahan, silahkan coba beberapa saat lagi !", Toast.LENGTH_SHORT).show();
    }

}
