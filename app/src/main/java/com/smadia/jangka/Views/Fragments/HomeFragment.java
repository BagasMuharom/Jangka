package com.smadia.jangka.Views.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smadia.jangka.JSON.AsyncTaskListener;
import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonFetcherAsyncTask;
import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.R;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Util.RecyclerViewOnItemClickListener;
import com.smadia.jangka.Util.RecyclerViewOnTouchListener;
import com.smadia.jangka.Views.Adapter.DaftarBeritaAdapter;
import com.smadia.jangka.Views.DetailBeritaActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView;

    public static ArrayList<Berita> daftarBerita;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daftar_berita, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.swipeRefreshLayout = (SwipeRefreshLayout) this.getActivity().findViewById(R.id.swipe_layout);

        recyclerView = (RecyclerView) this.getActivity().findViewById(R.id.daftar);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DaftarBeritaAdapter adapter = new DaftarBeritaAdapter(daftarBerita);
        HomeFragment.this.recyclerView.setAdapter(adapter);
        HomeFragment.this.swipeRefreshLayout.setRefreshing(false);

        HomeFragment.this.recyclerView.addOnItemTouchListener(new RecyclerViewOnTouchListener(HomeFragment.this.getContext(), HomeFragment.this.recyclerView, adapter, new RecyclerViewOnItemClickListener() {

            @Override
            public void onClick(View view, int position, long id) {
                Intent intent = new Intent(HomeFragment.this.getContext(), DetailBeritaActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(HomeFragment.this.getActivity(), (TextView) view.findViewById(R.id.judul), "judul");
                intent.putExtra("judul", ((TextView) view.findViewById(R.id.judul)).getText());
                intent.putExtra("idberita", id);
                Toast.makeText(HomeFragment.this.getActivity(), "idberita : " + id, Toast.LENGTH_SHORT).show();
                HomeFragment.this.getActivity().startActivity(intent, options.toBundle());
            }

            @Override
            public void onLongClick(View view, int position, long id) {

            }

        }));

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                FetchDaftarBerita jsonFetcherAsyncTask = new FetchDaftarBerita();
                JsonFetcher jsonFetcher = new JsonFetcher(App.generateUrl("berita"));
                jsonFetcherAsyncTask.execute(jsonFetcher);
            }

        });
    }

    private class FetchDaftarBerita extends JsonFetcherAsyncTask implements AsyncTaskListener {

        public FetchDaftarBerita() {
            super.asyncTaskListener = this;
        }

        public FetchDaftarBerita(AsyncTaskListener asyncTaskListener) {
            super(asyncTaskListener);
        }

        @Override
        public void onPreExecuteListener() {

        }

        @Override
        public void onPostExecuteListener(JsonFetcher jsonFetcher) {
            try {
                ArrayList<Berita> daftarBerita = new Berita().all(jsonFetcher.getJsonArray());
                Toast.makeText(HomeFragment.this.getContext(), "jumlah : " + daftarBerita.size(), Toast.LENGTH_SHORT).show();
                DaftarBeritaAdapter adapter = new DaftarBeritaAdapter(daftarBerita);
                HomeFragment.this.recyclerView.setAdapter(adapter);
                HomeFragment.this.swipeRefreshLayout.setRefreshing(false);

                HomeFragment.this.recyclerView.addOnItemTouchListener(new RecyclerViewOnTouchListener(HomeFragment.this.getContext(), HomeFragment.this.recyclerView, adapter, new RecyclerViewOnItemClickListener() {

                    @Override
                    public void onClick(View view, int position, long id) {
                        Intent intent = new Intent(HomeFragment.this.getContext(), DetailBeritaActivity.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(HomeFragment.this.getActivity(), (TextView) view.findViewById(R.id.judul), "judul");
                        intent.putExtra("judul", ((TextView) view.findViewById(R.id.judul)).getText());
                        intent.putExtra("idberita", id);
                        Toast.makeText(HomeFragment.this.getActivity(), "idberita : " + id, Toast.LENGTH_SHORT).show();
                        HomeFragment.this.getActivity().startActivity(intent, options.toBundle());
                    }

                    @Override
                    public void onLongClick(View view, int position, long id) {

                    }

                }));
            } catch (NullPointerException e) {
                Toast.makeText(HomeFragment.this.getActivity(), "Null Pointer", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onProgressUpdateListener(Integer progress) {

        }

    }

}
