package com.smadia.jangka.Util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;

public class ImageGetter {

    public static void loadImageToImageView(ImageView imageView, String url) {

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

    public static Bitmap getImageBitmap(byte[] imageByte) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);

        return bitmap;
    }

}
