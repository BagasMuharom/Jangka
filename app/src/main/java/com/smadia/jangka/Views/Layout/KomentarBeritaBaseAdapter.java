package com.smadia.jangka.Views.Layout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smadia.jangka.Controllers.KomentarController;
import com.smadia.jangka.Models.Offline.UserOffline;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = this.activity.getLayoutInflater().inflate(R.layout.detail_komentar, null);

        TextView pengirim = (TextView) view.findViewById(R.id.pengirim);
        TextView komentar = (TextView) view.findViewById(R.id.komentar);
        Button hapus = (Button) view.findViewById(R.id.hapusKomentar);

        if(UserOffline.activeUser != null) {
            if(UserOffline.activeUser.getIdOnline() == ((Komentar) this.getItem(i)).getUser().getId()) {
                hapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        KomentarController controller = new KomentarController(activity);
                        Toast.makeText(activity, "id_komentar : " + (int) getItemId(i), Toast.LENGTH_SHORT).show();
                        controller.hapusKomentar(UserOffline.activeUser, (int) getItemId(i));
                    }
                });
            }
            else{
                hapus.setVisibility(View.INVISIBLE);
            }
        }
        else {
            hapus.setVisibility(View.INVISIBLE);
        }

        Komentar Modelkomentar = this.daftarKomentar.get(i);

        pengirim.setText(Modelkomentar.getUser().getUsername());
        komentar.setText(Modelkomentar.getIsi());

        return view;
    }

}
