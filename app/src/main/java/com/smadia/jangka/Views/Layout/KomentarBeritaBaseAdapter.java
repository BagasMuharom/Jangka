package com.smadia.jangka.Views.Layout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smadia.jangka.Models.Online.Relationship.Komentar;
import com.smadia.jangka.R;
import com.smadia.jangka.Views.KomentarActivity;

import java.util.ArrayList;

public class KomentarBeritaBaseAdapter extends BaseAdapter {

    private ArrayList<Komentar> daftarKomentar;

    private KomentarActivity activity;

    public KomentarBeritaBaseAdapter(ArrayList<Komentar> daftarKomentar, KomentarActivity activity) {
        this.daftarKomentar = daftarKomentar;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.daftarKomentar.size();
    }

    @Override
    public Object getItem(int i) {
        return this.daftarKomentar.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.daftarKomentar.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = this.activity.getLayoutInflater().inflate(R.layout.detail_komentar, null);

        TextView pengirim = (TextView) view.findViewById(R.id.pengirim);
        TextView komentar = (TextView) view.findViewById(R.id.komentar);

        Komentar Modelkomentar = this.daftarKomentar.get(i);

        pengirim.setText(Modelkomentar.getUser().getUsername());
        komentar.setText(Modelkomentar.getIsi());

        return view;
    }

}
