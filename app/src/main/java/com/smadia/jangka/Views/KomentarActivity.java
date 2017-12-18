package com.smadia.jangka.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.smadia.jangka.Controllers.KomentarController;
import com.smadia.jangka.JSON.AsyncTaskListener;
import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonFetcherAsyncTask;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Models.Online.Relationship.Komentar;
import com.smadia.jangka.Models.Online.User;
import com.smadia.jangka.R;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Views.Layout.KomentarBeritaBaseAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class KomentarActivity extends AppCompatActivity {

    public Button kirim_komentar;

    public EditText komentar;

    private KomentarController controller;

    public ListView daftar_komentar;

    public ArrayList<Komentar> daftarKomentar;

    public Berita berita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komentar);

        this.controller = new KomentarController(this);

        this.kirim_komentar = (Button) findViewById(R.id.kirim_komentar);
        this.komentar = (EditText) findViewById(R.id.komentar);
        this.daftar_komentar = (ListView) findViewById(R.id.daftar_komentar);

        this.berita = new Berita();
        this.daftarKomentar = new ArrayList<>();

        int idBerita = getIntent().getIntExtra("berita", -1);

        FetchBerita fetchBerita = new FetchBerita();
        fetchBerita.execute(new JsonFetcher(App.generateUrl("berita/" + idBerita)));

        KomentarActivity.this.kirim_komentar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                KomentarActivity.this.controller.kirimKomentar(
                        new User(2),
                        String.valueOf(KomentarActivity.this.komentar.getText()),
                        KomentarActivity.this.berita
                );
            }

        });
    }

    public class FetchBerita extends JsonFetcherAsyncTask implements AsyncTaskListener {

        public FetchBerita() {
            this.asyncTaskListener = this;
        }

        @Override
        public void onPreExecuteListener() {

        }

        @Override
        public void onPostExecuteListener(JsonFetcher jsonFetcher) {
            try {
                KomentarActivity.this.berita.setPropetyFromJsonObject(jsonFetcher.getJsonArray().getJSONObject(0));

                FetchDaftarKomentar fetchDaftarKomentar = new FetchDaftarKomentar();
                fetchDaftarKomentar.execute(new JsonFetcher(App.generateUrl("berita/" + KomentarActivity.this.berita.getId() + "/komentar")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onProgressUpdateListener(Integer progress) {

        }

    }

    private class FetchDaftarKomentar extends JsonFetcherAsyncTask implements AsyncTaskListener {

        public FetchDaftarKomentar() {
            this.asyncTaskListener = this;
        }

        @Override
        public void onPreExecuteListener() {

        }

        @Override
        public void onPostExecuteListener(JsonFetcher jsonFetcher) {

            for(int i = 0; i < jsonFetcher.getJsonArray().length(); i++) {
                try {
                    User user = new User();
                    user.setPropetyFromJsonObject(jsonFetcher.getJsonArray().getJSONObject(i));
                    JSONObject pivot = jsonFetcher.getJsonArray().getJSONObject(i).getJSONObject("pivot");
                    String isi = pivot.getString("isi");
                    Komentar komentar = new Komentar(
                            KomentarActivity.this.berita,
                            user,
                            isi,
                            pivot.getInt("id"));
                    KomentarActivity.this.daftarKomentar.add(komentar);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            Toast.makeText(KomentarActivity.this, "Jumlah komentar : " + KomentarActivity.this.daftarKomentar.size(), Toast.LENGTH_LONG).show();

            KomentarBeritaBaseAdapter adapter = new KomentarBeritaBaseAdapter(KomentarActivity.this.daftarKomentar, KomentarActivity.this);
            KomentarActivity.this.daftar_komentar.setAdapter(adapter);
        }

        @Override
        public void onProgressUpdateListener(Integer progress) {

        }

    }

}
