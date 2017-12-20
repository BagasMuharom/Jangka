package com.smadia.jangka.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smadia.jangka.Controllers.BeritaOfflineController;
import com.smadia.jangka.Controllers.BookmarkController;
import com.smadia.jangka.Controllers.FeedbackBeritaController;
import com.smadia.jangka.JSON.AsyncTaskListener;
import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonFetcherAsyncTask;
import com.smadia.jangka.Models.Offline.UserOffline;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.R;
import com.smadia.jangka.Util.App;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailBeritaActivity extends AppCompatActivity {

    private Berita berita;

    private TextView judul;

    private TextView isi;

    private FloatingActionButton komentar;

    private Button sukaBtn;

    private Button tidakSukaBtn;

    private Button bookmarkB;

    private Button simpan;

    private BookmarkController bookmarkController;

    private FeedbackBeritaController feedbackBeritaController;

    private BeritaOfflineController beritaOfflineController;

    private JSONObject jsonResponse;

    private boolean disukai = false;

    private boolean tidak_disukai = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.bookmarkController = new BookmarkController(this);

        this.feedbackBeritaController = new FeedbackBeritaController(this);

        this.beritaOfflineController = new BeritaOfflineController(this);

        this.initLayout();

        this.judul.setText(getIntent().getStringExtra("judul"));

        this.initIsiBerita();
    }

    private void initLayout() {
        this.judul = (TextView) this.findViewById(R.id.judul);

        this.isi = (TextView) this.findViewById(R.id.isi);

        this.bookmarkB = (Button) this.findViewById(R.id.bookmark);

        this.sukaBtn = (Button) this.findViewById(R.id.suka);

        this.tidakSukaBtn = (Button) this.findViewById(R.id.tidak_suka);

        this.simpan = (Button) this.findViewById(R.id.simpan);

        komentar = (FloatingActionButton) findViewById(R.id.fab);
        komentar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailBeritaActivity.this, KomentarActivity.class);
                intent.putExtra("berita", DetailBeritaActivity.this.berita.getId());
                DetailBeritaActivity.this.startActivity(intent);
            }

        });

        this.sukaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbackBeritaController.toggleFeedback(DetailBeritaActivity.this.berita, true, disukai, tidak_disukai);
            }
        });

        this.tidakSukaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbackBeritaController.toggleFeedback(DetailBeritaActivity.this.berita, false, disukai, tidak_disukai);
            }
        });

        this.simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DetailBeritaActivity.this.beritaOfflineController.simpanKeOffline(DetailBeritaActivity.this.berita);
            }
        });
    }

    private void tampilkanIsiBerita() {
        this.isi.setText(this.berita.getIsi());

        Toast.makeText(this, this.berita.toString(), Toast.LENGTH_SHORT).show();
    }

    private void initIsiBerita() {
        long idBerita = this.getIntent().getLongExtra("idberita", -1);
        if (idBerita != -1) {
            FetchDetailBerita fetchDetailBerita = new FetchDetailBerita();
            String url = "berita/" + idBerita;
            if(UserOffline.activeUser != null) {
                url += "?user=" + UserOffline.activeUser.getIdOnline();
            }
            JsonFetcher jsonFetcher = new JsonFetcher(App.generateUrl(url));
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
                DetailBeritaActivity.this.jsonResponse = jsonFetcher.getJsonArray().getJSONObject(0);
                DetailBeritaActivity.this.berita.setPropetyFromJsonObject(DetailBeritaActivity.this.jsonResponse);
                DetailBeritaActivity.this.tampilkanIsiBerita();

                if(jsonResponse.has("user_bookmark")) {
                    if (jsonResponse.getBoolean("user_bookmark")) {
                        DetailBeritaActivity.this.bookmarkB.setText("Hapus dari bookmark");
                        DetailBeritaActivity.this.bookmarkB.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                DetailBeritaActivity.this.bookmarkController.hapusDariBookmark(UserOffline.activeUser, DetailBeritaActivity.this.berita);
                            }

                        });
                    } else {
                        DetailBeritaActivity.this.bookmarkB.setText("tambah ke bookmark");
                        DetailBeritaActivity.this.bookmarkB.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                DetailBeritaActivity.this.bookmarkController.tambahKeBookmark(UserOffline.activeUser, DetailBeritaActivity.this.berita);
                            }

                        });
                    }
                }
                else {
                    DetailBeritaActivity.this.bookmarkB.setText("login untuk menambahkan ke bookmark");
                }

                if(jsonResponse.has("user_feedback")) {
                    if(jsonResponse.getBoolean("user_like")) {
                        DetailBeritaActivity.this.sukaBtn.setText("Batalkan suka");
                    }
                    else {
                        DetailBeritaActivity.this.tidakSukaBtn.setText("Batalkan tidak suka");
                    }
                }
                else {
                    DetailBeritaActivity.this.sukaBtn.setEnabled(false);
                    DetailBeritaActivity.this.tidakSukaBtn.setEnabled(false);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onProgressUpdateListener(Integer progress) {

        }

    }

    public void setDisukai(boolean disukai) {
        this.disukai = disukai;

        if(disukai)
            DetailBeritaActivity.this.sukaBtn.setText("Batalkan suka");
        else
            DetailBeritaActivity.this.sukaBtn.setText("suka");
    }

    public void setTidak_disukai(boolean tidak_disukai) {
        this.tidak_disukai = tidak_disukai;

        if(tidak_disukai)
            DetailBeritaActivity.this.tidakSukaBtn.setText("Batalkan tidak suka");
        else
            DetailBeritaActivity.this.tidakSukaBtn.setText("tidak suka");
    }

}
