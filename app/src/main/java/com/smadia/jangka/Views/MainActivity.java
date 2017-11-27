package com.smadia.jangka.Views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.R;
import com.smadia.jangka.Util.ConnectionChecker;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ConnectionChecker.isConnectedOnNetwork(this)) {
            Berita berita = new Berita();
            ListView listView = (ListView) this.findViewById(R.id.listView);
            CustomAdapter customAdapter = new CustomAdapter(berita.all());
            listView.setAdapter(customAdapter);
        }
        else
            Toast.makeText(this, "Not Connected", Toast.LENGTH_LONG).show();
    }

    class CustomAdapter extends BaseAdapter {

        ArrayList<Berita> daftarBerita;

        public CustomAdapter(ArrayList<Berita> daftarBerita)
        {
            this.daftarBerita = daftarBerita;
        }

        @Override
        public int getCount() {
            return this.daftarBerita.size();
        }

        @Override
        public Object getItem(int i) {
            return this.daftarBerita.get(i);
        }

        @Override
        public long getItemId(int i) {
            return this.daftarBerita.get(i).getId();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.list_view, null);

            TextView judul = (TextView) view.findViewById(R.id.judul);
            TextView isi = (TextView) view.findViewById(R.id.isi);

            judul.setText(this.daftarBerita.get(i).getJudul());
            isi.setText(this.daftarBerita.get(i).getIsi());

            return view;
        }
    }

}
