package com.smadia.jangka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister;
    EditText editUname, editEmail, editPswrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegis);
        editUname = (EditText) findViewById(R.id.editUsname);
        editEmail = (EditText) findViewById(R.id.editMail);
        editPswrd = (EditText) findViewById(R.id.editPsw);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnRegis:

                break;
        }
    }
}
