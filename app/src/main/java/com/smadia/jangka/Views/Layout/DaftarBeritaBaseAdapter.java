package com.smadia.jangka.Views.Layout;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.R;

import java.util.ArrayList;

public class DaftarBeritaBaseAdapter extends BaseAdapter {

    private AppCompatActivity activity = null;

    private Fragment fragment = null;

    private ArrayList<Berita> daftarBerita;

    public DaftarBeritaBaseAdapter(ArrayList<Berita> daftarBerita, AppCompatActivity activity) {
        this.daftarBerita = daftarBerita;
        this.activity = activity;
    }

    public DaftarBeritaBaseAdapter(ArrayList<Berita> daftarBerita, Fragment fragment) {
        this.daftarBerita = daftarBerita;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return this.daftarBerita.size();
    }

    @Override
    public Object getItem(int i) {
        return this.daftarBerita.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.daftarBerita.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(null != this.activity)
            view = this.activity.getLayoutInflater().inflate(R.layout.list_view, null);
        else
            view = this.fragment.getLayoutInflater().inflate(R.layout.list_view, null);

        TextView judul = (TextView) view.findViewById(R.id.judul);
        TextView isi = (TextView) view.findViewById(R.id.isi);

        judul.setText(this.daftarBerita.get(i).getJudul());
        isi.setText(this.daftarBerita.get(i).getIsi());

        return view;
    }

}
