package com.smadia.jangka.Util;

import android.view.View;

public interface RecyclerViewOnItemClickListener {

    public void onClick(View view, int position, long id);

    public void onLongClick(View view,int position, long id);

}
