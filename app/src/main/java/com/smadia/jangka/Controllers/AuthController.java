package com.smadia.jangka.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smadia.jangka.Request.Request;
import com.smadia.jangka.Util.App;
import com.smadia.jangka.Views.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Blob;

public class AuthController {

    private AppCompatActivity activity;

    public AuthController(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void login(String username, String password)
    {
        String url = App.generateUrl("login");
        LoginListener listener = new LoginListener();
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("username", username + "");
        request.addParams("password", password + "");
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private class LoginListener implements Response.Listener<String>, Response.ErrorListener{

        @Override
        public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success)
                    {
                        Toast.makeText(AuthController.this.activity, "Login Berhasil !", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(AuthController.this.activity, "Gagal Login !", Toast.LENGTH_SHORT).show();


                    }

                } catch (JSONException e) {

                }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(AuthController.this.activity, "Terjadi kesalahan !", Toast.LENGTH_SHORT).show();
        }

    }

    public void register(String username, String email, String password, Blob avatar, boolean login)
    {
        String url = App.generateUrl("register");
        RegisterListener listener = new RegisterListener();
        Request request = new Request(com.android.volley.Request.Method.POST, url, listener, listener);
        request.addParams("username", username + "");
        request.addParams("email", password + "");
        request.addParams("password", password + "");
        request.addParams("password", avatar + "");
        request.addParams("login", login + "");
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        queue.add(request);
    }

    private class RegisterListener implements Response.Listener<String>, Response.ErrorListener{

        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success)
                {
                    Toast.makeText(AuthController.this.activity, "Anda Berhasil Terdaftar !", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(AuthController.this.activity, "Gagal  !", Toast.LENGTH_SHORT).show();


                }


            } catch (JSONException e) {

            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(AuthController.this.activity, "Terjadi kesalahan !", Toast.LENGTH_SHORT).show();
        }

    }

    public void logout(View view)
    {
        Toast.makeText(AuthController.this.activity, "Logged Out Successfully", Toast.LENGTH_LONG).show();

        // Closing the current activity.
        activity.finish();

        // Redirect to Main Login activity after log out.
        Intent intent = new Intent(AuthController.this.activity, MainActivity.class);

        activity.startActivity(intent);
    }


}
