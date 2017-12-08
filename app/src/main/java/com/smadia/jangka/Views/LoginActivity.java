package com.smadia.jangka.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smadia.jangka.R;
import com.smadia.jangka.Request.Request;
import com.smadia.jangka.Util.App;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button btnLog;
    EditText editUname, editPswrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLog =(Button) findViewById(R.id.login);
        editUname = (EditText) findViewById(R.id.username);
        editPswrd = (EditText) findViewById(R.id.password);

        btnLog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LoginListener listener = new LoginListener();
                Request request = new Request(com.android.volley.Request.Method.POST, App.generateUrl("login"), listener, listener);
                request.addParams("username", editUname.getText().toString());
                request.addParams("password", editPswrd.getText().toString());
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(request);
            }

        });
    }

    private class LoginListener implements Response.Listener<String>, Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {

        }

        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                boolean success = jsonObject.getBoolean("success");
                if(success) {
                    int idUser = jsonObject.getInt("id");
                    String username = jsonObject.getString("username");
                    String avatar = jsonObject.getString("avatar");
                    String email = jsonObject.getString("email");
                    Toast.makeText(LoginActivity.this, "avatar : " + avatar, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Gagal login", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
