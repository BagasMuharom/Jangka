package com.smadia.jangka.Views.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.smadia.jangka.Models.Online.Kategori;
import com.smadia.jangka.R;
import com.smadia.jangka.Views.Layout.DaftarKategoriBaseAdapter;

import java.util.ArrayList;

public class DaftarKategoriFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daftar_kategori, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = (ListView) this.getActivity().findViewById(R.id.daftar_kategori);

        ArrayList<Kategori> daftarKategori = new Kategori().all();

        DaftarKategoriBaseAdapter daftarKategoriBaseAdapter = new DaftarKategoriBaseAdapter(daftarKategori, this);

        listView.setAdapter(daftarKategoriBaseAdapter);
    }

}
