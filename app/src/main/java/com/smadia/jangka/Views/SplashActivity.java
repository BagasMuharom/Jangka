package com.smadia.jangka.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smadia.jangka.JSON.AsyncTaskListener;
import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonFetcherAsyncTask;
import com.smadia.jangka.Models.JangkaDatabaseHelper;
import com.smadia.jangka.Models.Offline.UserOffline;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Models.Online.Kategori;
import com.smadia.jangka.R;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Views.Fragments.DaftarKategoriFragment;
import com.smadia.jangka.Views.Fragments.HomeFragment;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    private JangkaDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        databaseHelper = new JangkaDatabaseHelper(this);

        this.initializeLocalUser();
        this.fetchDaftarBerita();
    }

    private void startApp() {
        this.finish();
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void fetchDaftarBerita() {
        FetchDaftarBerita jsonFetcherAsyncTask = new FetchDaftarBerita();
        JsonFetcher jsonFetcher = new JsonFetcher(App.generateUrl("berita"));
        jsonFetcherAsyncTask.execute(jsonFetcher);
    }

    private class FetchDaftarBerita extends JsonFetcherAsyncTask implements AsyncTaskListener {

        public FetchDaftarBerita() {
            super.asyncTaskListener = this;
        }

        public FetchDaftarBerita(AsyncTaskListener asyncTaskListener) {
            super(asyncTaskListener);
        }

        @Override
        public void onPreExecuteListener() {

        }

        @Override
        public void onPostExecuteListener(JsonFetcher jsonFetcher) {
            ArrayList<Berita> daftarBerita = new Berita().all(jsonFetcher.getJsonArray());
            HomeFragment.daftarBerita = daftarBerita;

            SplashActivity.this.fetchDaftarKategori();
        }

        @Override
        public void onProgressUpdateListener(Integer progress) {

        }

    }

    private void fetchDaftarKategori() {
        FetchDaftarKategori daftarKategori = new FetchDaftarKategori();
        JsonFetcher jsonFetcher = new JsonFetcher(App.generateUrl("kategori"));
        daftarKategori.execute(jsonFetcher);
    }

    private class FetchDaftarKategori extends JsonFetcherAsyncTask implements AsyncTaskListener {

        public FetchDaftarKategori() {
            super.asyncTaskListener = this;
        }

        @Override
        public void onPreExecuteListener() {

        }

        @Override
        public void onPostExecuteListener(JsonFetcher jsonFetcher) {
            ArrayList<Kategori> daftarKategori = new Kategori().all(jsonFetcher);
            DaftarKategoriFragment.daftarKategori = daftarKategori;

            SplashActivity.this.startApp();
        }

        @Override
        public void onProgressUpdateListener(Integer progress) {

        }

    }

    private void initializeLocalUser() {
        JangkaDatabaseHelper helper = new JangkaDatabaseHelper(this);
        UserOffline.initLoggedInUser(helper);
    }

}
