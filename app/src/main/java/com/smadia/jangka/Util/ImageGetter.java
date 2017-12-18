package com.smadia.jangka.Util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.smadia.jangka.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImageGetter {

    public static void loadImageToImageView(AppCompatActivity activity, ImageView imageView, String url) {
        Picasso.with(activity).load(url)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }

        });
    }

    public static void loadImgeToImageView(ImageView imageView, byte[] imageByte) {
        imageView.setImageBitmap(ImageGetter.getImageBitmap(imageByte));
    }

    public static byte[] getImageByte(SQLiteDatabase database, String query) {
        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            byte[] imageByte = cursor.getBlob(0);
            return imageByte;
        }

        return new byte[0];
    }

    public static byte[] getImageByte(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap getImageBitmap(byte[] imageByte) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);

        return bitmap;
    }

}
