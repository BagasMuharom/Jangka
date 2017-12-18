package com.smadia.jangka.Views;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.smadia.jangka.R;
import com.smadia.jangka.Views.Fragments.DaftarKategoriFragment;
import com.smadia.jangka.Views.Fragments.HomeFragment;
import com.smadia.jangka.Views.Fragments.ProfilUserFragment;
import com.smadia.jangka.Views.Fragments.SejarahFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    AppCompatActivity activity;

    Fragment fragmentTerpilih = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activity = this;
        this.bottomNavigationView = (BottomNavigationView) this.findViewById(R.id.bottom_navigation);
        this.bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        switch (item.getItemId()) {
                            case R.id.profile:
                                fragmentTerpilih = new ProfilUserFragment();
                                fragmentTransaction.replace(R.id.fragment_layout, fragmentTerpilih);
                                fragmentTransaction.commit();
                                break;
                            case R.id.home:
                                fragmentTerpilih = new HomeFragment();
                                fragmentTransaction.replace(R.id.fragment_layout, fragmentTerpilih);
                                fragmentTransaction.commit();
                                break;
                            case R.id.sejarah:
                                fragmentTerpilih = new SejarahFragment();
                                fragmentTransaction.replace(R.id.fragment_layout, fragmentTerpilih);
                                fragmentTransaction.commit();
                                break;
                            case R.id.kategori:
                                fragmentTerpilih = new DaftarKategoriFragment();
                                fragmentTransaction.replace(R.id.fragment_layout, fragmentTerpilih);
                                fragmentTransaction.commit();
                                break;
                        }
                        return true;
                    }

                });

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, new HomeFragment());
        fragmentTransaction.commit();
    }

    private boolean tekanDuaKaliExit = false;

    @Override
    public void onBackPressed() {
        if(tekanDuaKaliExit) {
            super.onBackPressed();
            return;
        }

        this.tekanDuaKaliExit = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar !", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.tekanDuaKaliExit = false;
            }
        }, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentTerpilih.onActivityResult(requestCode, resultCode, data);
    }
}
