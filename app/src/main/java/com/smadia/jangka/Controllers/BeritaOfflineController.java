package com.smadia.jangka.Controllers;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smadia.jangka.Models.JangkaDatabaseHelper;
import com.smadia.jangka.Models.Offline.BeritaOffline;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Request.Request;
import com.smadia.jangka.Util.App;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.Blob;
import java.sql.Timestamp;

public class BeritaOfflineController {

    private AppCompatActivity activity;

    public BeritaOfflineController (AppCompatActivity activity) {
        this.activity = activity;
    }

    public void simpanKeOffline(Berita berita) {
        JangkaDatabaseHelper helper = new JangkaDatabaseHelper(this.activity);
        BeritaOffline beritaOffline = new BeritaOffline(berita);
        if(beritaOffline.save(helper)) {
            Toast.makeText(this.activity, "Berhasil menyimpan !", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this.activity, "Gagal menyimpan !", Toast.LENGTH_SHORT).show();
        }
    }

    public void Berita(String judul, Text isi, String lokasi, String reporter, Timestamp tglbuat, Blob gambar)
    {
        String url = App.generateUrl("berita");
        BeritaListener listener = new BeritaListener();
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("judul", judul + "");
        request.addParams("isi", isi + "");
        request.addParams("lokasi", lokasi + "");
        request.addParams("reporter", reporter + "");
        request.addParams("tanggal Buat", tglbuat + "");
        request.addParams("gambar", gambar + "");
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private class BeritaListener implements Response.Listener<String>, Response.ErrorListener
    {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if(success) {
                    Toast.makeText(BeritaOfflineController.this.activity, "Berhasil !", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(BeritaOfflineController.this.activity, "Gagal !", Toast.LENGTH_SHORT).show();
                }

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(BeritaOfflineController.this.activity, "Terjadi kesalahan !", Toast.LENGTH_SHORT).show();
        }

    }
}
