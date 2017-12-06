package com.smadia.jangka.Controllers;

import android.widget.BaseAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Models.Online.Relationship.Komentar;
import com.smadia.jangka.Models.Online.User;
import com.smadia.jangka.Request.Request;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Views.KomentarActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class KomentarController {

    private KomentarActivity activity;

    public KomentarController(KomentarActivity activity) {
        this.activity = activity;
    }

    public void kirimKomentar(User user, String komentar, Berita berita) {
        String url = App.generateUrl("komentar/tambah");
        KirimKomentarListener listener = new KirimKomentarListener(user);
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("user", user.getId() + "");
        request.addParams("berita", berita.getId() + "");
        request.addParams("komentar", komentar);
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private class KirimKomentarListener implements Response.Listener<String>, Response.ErrorListener{

        User user;

        public KirimKomentarListener(User user) {
            this.user = user;
        }

        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if(success) {
                    Toast.makeText(KomentarController.this.activity, "Berhasil mengirim komentar !", Toast.LENGTH_SHORT).show();
                    Berita berita = KomentarController.this.activity.berita;
                   KomentarController.this.activity.daftarKomentar.add(new Komentar(berita, this.user , KomentarController.this.activity.komentar.getText().toString()));
                    ((BaseAdapter) KomentarController.this.activity.daftar_komentar.getAdapter()).notifyDataSetChanged();
                    KomentarController.this.activity.komentar.setText("");
                }
                else{
                    Toast.makeText(KomentarController.this.activity, "Gagal mengirim komentar !", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(KomentarController.this.activity, "Terjadi kesalahan, silahkan coba beberapa saat lagi !", Toast.LENGTH_SHORT).show();
        }

    }

}
