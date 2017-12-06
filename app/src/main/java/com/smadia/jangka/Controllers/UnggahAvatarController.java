package com.smadia.jangka.Controllers;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import com.smadia.jangka.Views.UbahAvatar;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UnggahAvatarController {

    private UbahAvatar activity;

    public UnggahAvatarController(AppCompatActivity activity) {
        this.activity = (UbahAvatar) activity;
    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        this.activity.startActivityForResult(Intent.createChooser(intent, "Pilih Foto"), 1);
    }

    public void showImage(int requestCode, int resultCode, Intent data) {
        if(resultCode == activity.RESULT_OK) {
            if(requestCode == 1) {
                Uri selectedImage = data.getData();
                if(Build.VERSION.SDK_INT < 19) {
                    String selectedImagePath = getPath(selectedImage);
                    Bitmap image = BitmapFactory.decodeFile(selectedImagePath);
                    activity.imageView.setImageBitmap(image);
                }
                else {
                    ParcelFileDescriptor parcelFileDescriptor;
                    try {
                        parcelFileDescriptor = activity.getContentResolver().openFileDescriptor(selectedImage, "r");
                        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                        parcelFileDescriptor.close();
                        activity.imageView.setImageBitmap(image);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String getPath(Uri uri) {
        if (uri == null)
            return null;

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            int colindex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(colindex);
        }

        return uri.getPath();
    }
}
