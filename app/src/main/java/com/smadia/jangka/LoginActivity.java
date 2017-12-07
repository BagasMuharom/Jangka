package com.smadia.jangka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLog;
    EditText editUname, editPswrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLog =(Button) findViewById(R.id.btnLogin);
        editUname = (EditText) findViewById(R.id.editUsename);
        editPswrd = (EditText) findViewById(R.id.editPsw);

        btnLog.setOnClickListener((View.OnClickListener) btnLog);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnLogin:

            break;
        }
    }
}
