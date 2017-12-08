package com.smadia.jangka.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smadia.jangka.JSON.AsyncTaskListener;
import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonFetcherAsyncTask;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.R;
import com.smadia.jangka.Util.App;

import org.json.JSONException;

public class DetailBeritaActivity extends AppCompatActivity {

    private Berita berita;

    private TextView judul;

    private TextView isi;

    private FloatingActionButton komentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.initLayout();

        this.judul.setText(getIntent().getStringExtra("judul"));

        this.initIsiBerita();
    }

    private void initLayout() {
        this.judul = (TextView) this.findViewById(R.id.judul);

        this.isi = (TextView) this.findViewById(R.id.isi);

        komentar = (FloatingActionButton) findViewById(R.id.fab);
        komentar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailBeritaActivity.this, KomentarActivity.class);
                intent.putExtra("berita", DetailBeritaActivity.this.berita.getId());
                DetailBeritaActivity.this.startActivity(intent);
            }

        });
    }

    private void tampilkanIsiBerita() {
        this.isi.setText(this.berita.getIsi());

        Toast.makeText(this, this.berita.toString(), Toast.LENGTH_SHORT).show();
    }

    private void initIsiBerita() {
        long idBerita = this.getIntent().getLongExtra("idberita", -1);
        if(idBerita != -1) {
            FetchDetailBerita fetchDetailBerita = new FetchDetailBerita();
            JsonFetcher jsonFetcher = new JsonFetcher(App.generateUrl("berita/" + idBerita));
            fetchDetailBerita.setAsyncTaskListener(fetchDetailBerita);
            fetchDetailBerita.execute(jsonFetcher);
        }
    }

    private class FetchDetailBerita extends JsonFetcherAsyncTask implements AsyncTaskListener {

        @Override
        public void onPreExecuteListener() {

        }

        @Override
        public void onPostExecuteListener(JsonFetcher jsonFetcher) {
            DetailBeritaActivity.this.berita = new Berita();
            try {
                DetailBeritaActivity.this.berita.setPropetyFromJsonObject(jsonFetcher.getJsonArray().getJSONObject(0));
                DetailBeritaActivity.this.tampilkanIsiBerita();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onProgressUpdateListener(Integer progress) {

        }

    }

}
