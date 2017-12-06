package com.smadia.jangka.Controllers;

import android.widget.Toast;

import com.smadia.jangka.Models.Online.Berita;
import com.smadia.jangka.Models.Online.User;

public class BookmarkController {

    public void addToBookmark(User user, Berita berita)
    {
        if(berita.addBookmarker(user)) {
            Toast.makeText(null, "Berhasil menambahkan ke bookmark !", Toast.LENGTH_SHORT).show();
        }
    }

}
