package com.smadia.jangka.Views.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.R;

import java.util.ArrayList;

public class DaftarBeritaAdapter extends RecyclerView.Adapter<BeritaHolder> implements JangkaRecyclerViewAdapter {

    private ArrayList<Berita> daftarBerita;

    public DaftarBeritaAdapter(ArrayList<Berita> daftarBerita) {
        this.daftarBerita = daftarBerita;
    }

    @Override
    public BeritaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_daftar_berita, parent, false);
        return new BeritaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BeritaHolder holder, int position) {
        Berita berita = this.daftarBerita.get(position);
        holder.judul.setText(berita.getJudul());
    }

    @Override
    public int getItemCount() {
        return this.daftarBerita.size();
    }

    public long getItemId(int position) {
        return this.daftarBerita.get(position).getId();
    }

}

class BeritaHolder extends RecyclerView.ViewHolder {

    public TextView judul;

    public ImageView thumbnail;

    public BeritaHolder(View itemView) {
        super(itemView);
        judul = (TextView) itemView.findViewById(R.id.judul);
        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
    }

}