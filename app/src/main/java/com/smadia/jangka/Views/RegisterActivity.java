package com.smadia.jangka.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smadia.jangka.Controllers.AuthController;
import com.smadia.jangka.R;

public class RegisterActivity extends AppCompatActivity {

    private AuthController controller;

    private Button btnRegister;

    private EditText editUname, editEmail, editPswrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegis);
        editUname = (EditText) findViewById(R.id.editUsname);
        editEmail = (EditText) findViewById(R.id.editMail);
        editPswrd = (EditText) findViewById(R.id.editPsw);

        this.controller = new AuthController(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username = editUname.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPswrd.getText().toString();

                RegisterActivity.this.controller.register(username, email, password, password);
            }

        });

    }

}
