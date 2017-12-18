package com.smadia.jangka.Views.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.smadia.jangka.Models.Online.Berita;

import java.util.ArrayList;

public class DaftarBookmarkAdapter extends RecyclerView.Adapter<BookmarkHolder> implements JangkaRecyclerViewAdapter {

    private ArrayList<Berita> daftarBookmark;

    public DaftarBookmarkAdapter(ArrayList<Berita> daftarBookmark) {
        this.daftarBookmark = daftarBookmark;
    }

    @Override
    public BookmarkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BookmarkHolder holder, int position) {

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
}

class BookmarkHolder extends RecyclerView.ViewHolder{

    public BookmarkHolder(View itemView) {
        super(itemView);
    }

}