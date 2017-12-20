package com.smadia.jangka.Views.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class DaftarBeritaOfflineAdapter extends RecyclerView.Adapter<DaftarBeritaOfflineAdapter.BeritaHolder> implements JangkaRecyclerViewAdapter {

    @Override
    public BeritaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BeritaHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class BeritaHolder extends RecyclerView.ViewHolder {

        public BeritaHolder(View itemView) {
            super(itemView);
        }
    }

}