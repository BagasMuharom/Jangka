package com.smadia.jangka.Controllers;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Models.Online.User;
import com.smadia.jangka.Request.Request;
import com.smadia.jangka.Util.App;

public class BookmarkController {

    public AppCompatActivity activity;

    public BookmarkController(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void tambahKeBookmark(User user, Berita berita) {
        String url = "http://jangka.herokuapp.com/bookmark/tambah";
        TambahKeBookmarkListener listener = new TambahKeBookmarkListener();
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("user", user.getId() + "");
        request.addParams("berita", berita.getId() + "");
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private class TambahKeBookmarkListener implements Response.Listener<String>, Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(BookmarkController.this.activity, "Gagal Menambahkan ke bookmark", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(String response) {
            Toast.makeText(BookmarkController.this.activity, "Berhasil Menambahkan ke bookmark", Toast.LENGTH_SHORT).show();
        }

    }

    public void hapusDariBookmark(User user, Berita berita) {
        String url = App.generateUrl("bookmark/hapus");
        HapusDariBookmarkListener listener = new HapusDariBookmarkListener();
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("user", user.getId() + "");
        request.addParams("berita", berita.getId() + "");
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private class HapusDariBookmarkListener implements Response.Listener<String>, Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {

        }

        @Override
        public void onResponse(String response) {

        }

    }

}
