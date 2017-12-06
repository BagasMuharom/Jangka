package com.smadia.jangka.Views.Layout;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.R;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Views.DetailBeritaActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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
            view = this.activity.getLayoutInflater().inflate(R.layout.list_view_daftar_berita, null);
        else
            view = this.fragment.getLayoutInflater().inflate(R.layout.list_view_daftar_berita, null);

        TextView judul = (TextView) view.findViewById(R.id.judul);
        ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        judul.setText(this.daftarBerita.get(i).getJudul());

        String thumbnailUrl = App.generateUrl("berita/" + i + "/gambar");
        Picasso.with((null != activity) ? activity : fragment.getActivity()).load(thumbnailUrl)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .into(thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }

                });

        final int idBerita = this.daftarBerita.get(i).getId();

//        final AppCompatActivity act = this.activity;

        judul.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Activity act = (null != activity) ? activity : fragment.getActivity();
                Intent intent = new Intent(act, DetailBeritaActivity.class);
                intent.putExtra("id", idBerita);
                act.startActivity(intent);
            }

        });

        return view;
    }

}
