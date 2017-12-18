package com.smadia.jangka.Util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.smadia.jangka.Views.Adapter.JangkaRecyclerViewAdapter;

public class RecyclerViewOnTouchListener implements RecyclerView.OnItemTouchListener {

    private RecyclerViewOnItemClickListener listener;

    private GestureDetector gestureDetector;

    private JangkaRecyclerViewAdapter adapter;

    public RecyclerViewOnTouchListener(Context context, final RecyclerView recyclerView, JangkaRecyclerViewAdapter adapter, final RecyclerViewOnItemClickListener listener) {
        this.listener = listener;
        this.adapter = adapter;

        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && listener != null) {
                    listener.onLongClick(child, recyclerView.getChildAdapterPosition(child), RecyclerViewOnTouchListener.this.adapter.getItemId(recyclerView.getChildAdapterPosition(child)));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());

        if (child != null && this.listener != null && gestureDetector.onTouchEvent(e)) {
            this.listener.onClick(child, rv.getChildAdapterPosition(child), adapter.getItemId(rv.getChildAdapterPosition(child)));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}


