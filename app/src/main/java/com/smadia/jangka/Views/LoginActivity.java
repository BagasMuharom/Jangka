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
import com.smadia.jangka.Controllers.AuthController;
import com.smadia.jangka.R;
import com.smadia.jangka.Request.Request;
import com.smadia.jangka.Util.App;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button btnLog;

    EditText editUname, editPswrd;

    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authController = new AuthController(this);
        btnLog =(Button) findViewById(R.id.login);
        editUname = (EditText) findViewById(R.id.username);
        editPswrd = (EditText) findViewById(R.id.password);

        btnLog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                authController.login(
                        editUname.getText().toString(),
                        editPswrd.getText().toString()
                );
            }

        });
    }
}
