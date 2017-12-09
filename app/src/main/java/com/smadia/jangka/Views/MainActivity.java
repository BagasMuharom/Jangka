package com.smadia.jangka.Views;

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
import com.smadia.jangka.Views.Fragments.SejarahFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    AppCompatActivity activity;

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
                        Fragment fragmentTerpilih = null;
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        switch (item.getItemId()) {
                            case R.id.profile:
                                Toast.makeText(activity, "Sejarah", Toast.LENGTH_SHORT).show();
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

}
