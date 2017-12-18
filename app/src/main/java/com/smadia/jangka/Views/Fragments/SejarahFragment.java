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
import com.smadia.jangka.Models.Online.Sejarah;
import com.smadia.jangka.R;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Views.Adapter.DaftarSejarahAdapter;

import java.util.ArrayList;

public class SejarahFragment extends Fragment {

    private ArrayList<Sejarah> daftarSejarah;

    RecyclerView daftarSejarahRv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daftar_sejarah, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        daftarSejarahRv = (RecyclerView) view.findViewById(R.id.daftarSejarahRv);
        daftarSejarahRv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        FetchDaftarSejarah fetchDaftarSejarah = new FetchDaftarSejarah();
        JsonFetcher jsonFetcher = new JsonFetcher(App.generateUrl("sejarah"));
        fetchDaftarSejarah.execute(jsonFetcher);
    }

    private class FetchDaftarSejarah extends JsonFetcherAsyncTask implements AsyncTaskListener {

        public FetchDaftarSejarah() {
            super.asyncTaskListener = this;
        }

        @Override
        public void onPreExecuteListener() {

        }

        @Override
        public void onPostExecuteListener(JsonFetcher jsonFetcher) {
            ArrayList<Sejarah> daftarSejarah = new Sejarah().all(jsonFetcher);
            DaftarSejarahAdapter adapter = new DaftarSejarahAdapter(daftarSejarah);
            SejarahFragment.this.daftarSejarahRv.setAdapter(adapter);
        }

        @Override
        public void onProgressUpdateListener(Integer progress) {

        }
    }

}
