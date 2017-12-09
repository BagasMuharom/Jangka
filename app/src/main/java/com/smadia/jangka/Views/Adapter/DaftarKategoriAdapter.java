package com.smadia.jangka.Views.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smadia.jangka.Models.Online.Kategori;
import com.smadia.jangka.R;

import java.util.ArrayList;

public class DaftarKategoriAdapter extends RecyclerView.Adapter<KategoriHolder> implements JangkaRecyclerViewAdapter {

    private ArrayList<Kategori> daftarkategori;

    public DaftarKategoriAdapter(ArrayList<Kategori> daftarKategori) {
        this.daftarkategori = daftarKategori;
    }

    @Override
    public KategoriHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_kategori, null);
        return new KategoriHolder(view);
    }

    @Override
    public void onBindViewHolder(KategoriHolder holder, int position) {
        Kategori kategori = (Kategori) this.getItem(position);
        holder.nama.setText(kategori.getNama());
    }

    @Override
    public int getItemCount() {
        return this.daftarkategori.size();
    }

    @Override
    public long getItemId(int position) {
        return this.daftarkategori.get(position).getId();
    }

    @Override
    public Object getItem(int position) {
        return this.daftarkategori.get(position);
    }

}

class KategoriHolder extends RecyclerView.ViewHolder {

    public TextView nama;

    public KategoriHolder(View itemView) {
        super(itemView);

        nama = (TextView) itemView.findViewById(R.id.nama);
    }

}
