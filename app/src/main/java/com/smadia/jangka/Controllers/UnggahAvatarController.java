package com.smadia.jangka.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class UnggahAvatarController {

    private AppCompatActivity activity;

    public UnggahAvatarController(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        this.activity.startActivityForResult(Intent.createChooser(intent, "Pilih Foto"), 1);
    }

}
