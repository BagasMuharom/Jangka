package com.smadia.jangka.Controllers;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Models.Online.User;
import com.smadia.jangka.Request.Request;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Views.DetailBeritaActivity;

public class FeedbackBeritaController {

    private DetailBeritaActivity activity;

    public FeedbackBeritaController(DetailBeritaActivity activity) {
        this.activity = activity;
    }

    public void tambahFeedback(User user, Berita berita, boolean suka) {
        String url = App.generateUrl("berita/tambah/feedback");
        FeedbackBeritaListener listener = new FeedbackBeritaListener();
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("user", user.getId() + "");
        request.addParams("berita", berita.getId() + "");
        request.addParams("suka", suka ? "1" : "0");
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private class FeedbackBeritaListener implements Response.Listener<String>, Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {

        }

        @Override
        public void onResponse(String response) {

        }

    }

}
