package com.smadia.jangka.Views.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smadia.jangka.Models.Online.Sejarah;
import com.smadia.jangka.R;

import java.util.ArrayList;

public class DaftarSejarahAdapter extends RecyclerView.Adapter<SejarahHolder> implements JangkaRecyclerViewAdapter {

    private ArrayList<Sejarah> daftarSejarah;

    public DaftarSejarahAdapter(ArrayList<Sejarah> daftarSejarah) {
        this.daftarSejarah = daftarSejarah;
    }

    @Override
    public SejarahHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sejarah, parent, false);

        return new SejarahHolder(view);
    }

    @Override
    public void onBindViewHolder(SejarahHolder holder, int position) {
        Sejarah sejarah = this.daftarSejarah.get(position);
        holder.judul.setText(sejarah.getJudul());
        holder.isi.setText(sejarah.getIsi());
        holder.tgl_terjadi.setText(sejarah.getTglTerjadi());
    }

    @Override
    public int getItemCount() {
        return this.daftarSejarah.size();
    }

    @Override
    public long getItemId(int position) {
        return ((Sejarah) this.getItem(position)).getId();
    }

    @Override
    public Object getItem(int position) {
        return this.daftarSejarah.get(position);
    }

}

class SejarahHolder extends RecyclerView.ViewHolder {

    public TextView judul;

    public TextView isi;

    public TextView tgl_terjadi;

    public SejarahHolder(View itemView) {
        super(itemView);
        judul = (TextView) itemView.findViewById(R.id.judul);
        isi = (TextView) itemView.findViewById(R.id.isi);
        tgl_terjadi = (TextView) itemView.findViewById(R.id.tgl_terjadi);
    }

}
