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

import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.R;

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

        this.initIsiBerita();

        this.tampilkanIsiBerita();
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
        this.judul.setText(this.berita.getJudul());
        this.isi.setText(this.berita.getIsi());

        Toast.makeText(this, this.berita.toString(), Toast.LENGTH_SHORT).show();
    }

    private void initIsiBerita() {
        Intent intent = getIntent();
        int idBerita = intent.getIntExtra("id", -1);
        this.berita = new Berita(idBerita);
    }

}
