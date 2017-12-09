package com.smadia.jangka.Views.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smadia.jangka.JSON.AsyncTaskListener;
import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonFetcherAsyncTask;
import com.smadia.jangka.Models.Online.Kategori;
import com.smadia.jangka.R;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Views.Adapter.DaftarKategoriAdapter;

import java.util.ArrayList;

public class DaftarKategoriFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daftar_kategori, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) this.getActivity().findViewById(R.id.daftar_kategori);
        recyclerView.setLayoutManager(new LinearLayoutManager(DaftarKategoriFragment.this.getContext()));

        FetchDaftarKategori daftarKategori = new FetchDaftarKategori();
        JsonFetcher jsonFetcher = new JsonFetcher(App.generateUrl("kategori"));
        daftarKategori.execute(jsonFetcher);
    }

    private class FetchDaftarKategori extends JsonFetcherAsyncTask implements AsyncTaskListener {

        public FetchDaftarKategori() {
            super.asyncTaskListener = this;
        }

        @Override
        public void onPreExecuteListener() {

        }

        @Override
        public void onPostExecuteListener(JsonFetcher jsonFetcher) {
            ArrayList<Kategori> daftarKategori = new Kategori().all(jsonFetcher);
            DaftarKategoriAdapter adapter = new DaftarKategoriAdapter(daftarKategori);
            DaftarKategoriFragment.this.recyclerView.setAdapter(adapter);
        }

        @Override
        public void onProgressUpdateListener(Integer progress) {

        }

    }

}
