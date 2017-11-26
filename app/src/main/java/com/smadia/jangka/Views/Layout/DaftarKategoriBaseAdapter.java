package com.smadia.jangka.Views.Layout;

import android.support.v4.app.Fragment;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smadia.jangka.Models.Online.Kategori;
import com.smadia.jangka.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DaftarKategoriBaseAdapter extends BaseAdapter {

    private ArrayList<Kategori> daftarKategori;

    private Fragment fragment;

    public DaftarKategoriBaseAdapter(ArrayList<Kategori> daftarKategori, Fragment fragment) {
        this.daftarKategori = daftarKategori;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return this.daftarKategori.size();
    }

    @Override
    public Object getItem(int i) {
        return this.daftarKategori.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.daftarKategori.get(i).getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = this.fragment.getLayoutInflater().inflate(R.layout.list_view_kategori, null);

        TextView namaTextView = (TextView) view.findViewById(R.id.nama);

        namaTextView.setText(this.daftarKategori.get(i).getNama());

        return view;
    }

}
