package com.smadia.jangka.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.smadia.jangka.Controllers.UnggahAvatarController;
import com.smadia.jangka.R;

public class UbahAvatar extends AppCompatActivity {

    public ImageView imageView;

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
        controller.showImage(requestCode, resultCode, data);
    }

}
