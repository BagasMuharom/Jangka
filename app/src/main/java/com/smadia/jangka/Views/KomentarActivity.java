package com.smadia.jangka.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smadia.jangka.Controllers.KomentarController;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Models.Online.User;
import com.smadia.jangka.R;

public class KomentarActivity extends AppCompatActivity {

    public Button kirim_komentar;

    public EditText komentar;

    private KomentarController controller;

    private Berita berita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komentar);

        this.controller = new KomentarController(this);
        this.berita = new Berita(this.getIntent().getIntExtra("berita", 0));

        this.kirim_komentar = (Button) findViewById(R.id.kirim_komentar);
        this.komentar = (EditText) findViewById(R.id.komentar);
        final User user = new User(2);

        this.kirim_komentar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                KomentarActivity.this.controller.kirimKomentar(
                        user,
                        String.valueOf(KomentarActivity.this.komentar.getText()),
                        KomentarActivity.this.berita
                );
            }

        });
    }

}
