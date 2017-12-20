package com.smadia.jangka.Controllers;

import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smadia.jangka.Models.Offline.UserOffline;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Request.Request;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Views.DetailBeritaActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackBeritaController {

    private DetailBeritaActivity activity;

    public FeedbackBeritaController(DetailBeritaActivity activity) {
        this.activity = activity;
    }

    public void toggleFeedback(Berita berita, boolean disukai, boolean suka, boolean tidakSuka) {
        String url = App.generateUrl("berita/toggle/feedback");
        FeedbackBeritaListener listener = new FeedbackBeritaListener();
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("user", UserOffline.activeUser.getId() + "");
        request.addParams("berita", berita.getId() + "");
        request.addParams("suka", disukai ? "1" : "0");
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private class FeedbackBeritaListener implements Response.Listener<String>, Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {

        }

        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);

                Toast.makeText(FeedbackBeritaController.this.activity, response, Toast.LENGTH_SHORT).show();

                if(jsonObject.getBoolean("success")) {

                    // Jika disukai
                    if(jsonObject.getBoolean("disukai")) {
                        FeedbackBeritaController.this.activity.setDisukai(true);
                        FeedbackBeritaController.this.activity.setTidak_disukai(false);
                    }
                    // Jika tidak disukai
                    if(jsonObject.getBoolean("tidak_disukai")) {
                        FeedbackBeritaController.this.activity.setDisukai(false);
                        FeedbackBeritaController.this.activity.setTidak_disukai(true);
                    }

                    // Jika tidak ada feedback
                    if(!jsonObject.getBoolean("disukai") && !jsonObject.getBoolean("tidak_disukai")) {
                        FeedbackBeritaController.this.activity.setDisukai(false);
                        FeedbackBeritaController.this.activity.setTidak_disukai(false);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
