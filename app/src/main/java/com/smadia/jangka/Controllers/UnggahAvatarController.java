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
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smadia.jangka.Models.Offline.UserOffline;
import com.smadia.jangka.Request.Request;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Util.ImageGetter;
import com.smadia.jangka.Views.UbahAvatar;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UnggahAvatarController {

    private UbahAvatar activity;

    private Bitmap image;

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
                    this.image = image;
                }
                else {
                    ParcelFileDescriptor parcelFileDescriptor;
                    try {
                        parcelFileDescriptor = activity.getContentResolver().openFileDescriptor(selectedImage, "r");
                        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                        parcelFileDescriptor.close();
                        activity.imageView.setImageBitmap(image);
                        this.image = image;
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

    public void unggah() {
        String url = App.generateUrl("user/unggah/avatar");
        UnggahAvatarListener listener = new UnggahAvatarListener();
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("avatar", Base64.encodeToString(ImageGetter.getImageByte(this.image), Base64.DEFAULT));
        request.addParams("user", 1 + "");
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 5, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private class UnggahAvatarListener implements Response.Listener<String>, Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(UnggahAvatarController.this.activity, "Gagal !", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(String response) {
            Toast.makeText(UnggahAvatarController.this.activity, "Berhasil !", Toast.LENGTH_SHORT).show();
        }

    }

}
