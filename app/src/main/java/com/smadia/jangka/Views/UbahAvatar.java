package com.smadia.jangka.Views;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.smadia.jangka.Controllers.UnggahAvatarController;
import com.smadia.jangka.R;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UbahAvatar extends AppCompatActivity {

    ImageView imageView;

    Button pilihGambar;

    Button unggahAvatar;

    private UnggahAvatarController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_avatar);

        this.controller = new UnggahAvatarController(this);

        imageView = (ImageView) findViewById(R.id.imageView);

        pilihGambar = (Button) findViewById(R.id.pilihGambar);

        pilihGambar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                controller.chooseImage();
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == 1) {
                Uri selectedImage = data.getData();
                if(Build.VERSION.SDK_INT < 19) {
                    String selectedImagePath = getPath(selectedImage);
                    Bitmap image = BitmapFactory.decodeFile(selectedImagePath);
                    this.imageView.setImageBitmap(image);
                }
                else {
                    ParcelFileDescriptor parcelFileDescriptor;
                    try {
                        parcelFileDescriptor = getContentResolver().openFileDescriptor(selectedImage, "r");
                        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                        parcelFileDescriptor.close();
                        this.imageView.setImageBitmap(image);
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
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            int colindex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(colindex);
        }

        return uri.getPath();
    }

}
