package com.smadia.jangka.Views.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.R;
import com.smadia.jangka.Views.Layout.DaftarBeritaBaseAdapter;

public class HomeFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;

    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragment = this;
        return inflater.inflate(R.layout.daftar_berita, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.swipeRefreshLayout = (SwipeRefreshLayout) this.getActivity().findViewById(R.id.swipe_layout);
        final Berita berita = new Berita();
        DaftarBeritaBaseAdapter baseAdapter = new DaftarBeritaBaseAdapter(berita.all(), this);
        ListView listView = (ListView) this.getActivity().findViewById(R.id.daftar);
        listView.setAdapter(baseAdapter);

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                DaftarBeritaBaseAdapter baseAdapter = new DaftarBeritaBaseAdapter(new Berita().all(), fragment);
                ListView listView = (ListView) fragment.getActivity().findViewById(R.id.daftar);
                listView.setAdapter(baseAdapter);

                swipeRefreshLayout.setRefreshing(false);
            }

        });

        if(Build.VERSION.SDK_INT >= 23) {
            this.getView().setOnScrollChangeListener(new View.OnScrollChangeListener() {

                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                }

            });
        }
    }

}
