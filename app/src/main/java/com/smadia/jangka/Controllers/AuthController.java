package com.smadia.jangka.Controllers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smadia.jangka.Models.JangkaDatabaseHelper;
import com.smadia.jangka.Models.Offline.UserOffline;
import com.smadia.jangka.Models.Online.User;
import com.smadia.jangka.Request.Request;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Views.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Blob;

public class AuthController {

    JangkaDatabaseHelper databaseHelper;

    private AppCompatActivity activity;

    private FragmentActivity fragmentActivity;

    public AuthController(AppCompatActivity activity) {
        this.activity = activity;
        this.databaseHelper = new JangkaDatabaseHelper(this.activity);
    }

    public AuthController(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        this.databaseHelper = new JangkaDatabaseHelper(this.fragmentActivity);
    }

    public void login(String username, String password) {
        String url = App.generateUrl("login");
        LoginListener listener = new LoginListener();
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("username", username + "");
        request.addParams("password", password + "");
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private void addToDatabaseAfterLogin(User user) {
        UserOffline userOffline = new UserOffline(user);
        if(userOffline.save(this.databaseHelper)) {
            Toast.makeText(this.activity, "Berhasil menyimpan !", Toast.LENGTH_SHORT).show();
            UserOffline.initLoggedInUser(this.databaseHelper);
            Intent intent = new Intent();
            intent.putExtra("success", true);
            this.activity.setResult(200, intent);
            this.activity.finish();
        }
        else {
            Toast.makeText(this.activity, "Gagal menyimpan !", Toast.LENGTH_SHORT).show();
        }
    }

    private class LoginListener implements Response.Listener<String>, Response.ErrorListener {

        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    Toast.makeText(AuthController.this.activity, "Berhasil Login !", Toast.LENGTH_SHORT).show();
                    User user = new User();
                    user.setPropetyFromJsonObject(jsonResponse.getJSONObject("user"));
                    AuthController.this.addToDatabaseAfterLogin(user);

                } else {
                    Toast.makeText(AuthController.this.activity, "Gagal Login !", Toast.LENGTH_SHORT).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(AuthController.this.activity, "Terjadi kesalahan !", Toast.LENGTH_SHORT).show();
        }

    }

    public void register(String username, String email, String password, String passwordConfirm) {
        String url = App.generateUrl("register");
        RegisterListener listener = new RegisterListener();
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("username", username);
        request.addParams("email", email);
        request.addParams("password", password);
        request.addParams("password_confirmation", passwordConfirm);
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private class RegisterListener implements Response.Listener<String>, Response.ErrorListener {

        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    Toast.makeText(AuthController.this.activity, "Anda Berhasil Terdaftar !", Toast.LENGTH_SHORT).show();
                    User user = new User();
                    user.setPropetyFromJsonObject(jsonResponse.getJSONArray("user").getJSONObject(0));
                    // Login setelah register
                    AuthController.this.addToDatabaseAfterLogin(user);
                } else {
                    Toast.makeText(AuthController.this.activity, "Gagal  !" + jsonResponse.getInt("errorCode"), Toast.LENGTH_SHORT).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(AuthController.this.activity, "Terjadi kesalahan !", Toast.LENGTH_SHORT).show();
        }

    }

    public void logout(Fragment fragment) {
        if(UserOffline.activeUser.delete(this.databaseHelper)) {
            Toast.makeText(AuthController.this.fragmentActivity, "Logged Out Successfully", Toast.LENGTH_LONG).show();
            UserOffline.activeUser = null;

            FragmentTransaction transaction = fragment.getActivity().getSupportFragmentManager().beginTransaction();
            transaction.detach(fragment);
            transaction.attach(fragment);
            transaction.commit();
        }
        else {
            Toast.makeText(AuthController.this.fragmentActivity, "Logged Out failed", Toast.LENGTH_LONG).show();
        }
    }

}
