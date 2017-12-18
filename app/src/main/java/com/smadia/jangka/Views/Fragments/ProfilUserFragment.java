package com.smadia.jangka.Views.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smadia.jangka.Controllers.AuthController;
import com.smadia.jangka.Models.Offline.UserOffline;
import com.smadia.jangka.R;
import com.smadia.jangka.Views.LoginActivity;
import com.smadia.jangka.Views.RegisterActivity;

public class ProfilUserFragment extends Fragment {

    private TextView usernameTV;

    private Button logoutB;

    private Button loginB;

    private Button registerB;

    private AuthController controller;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profil_user, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.controller = new AuthController(this.getActivity());

        this.usernameTV = (TextView) this.getActivity().findViewById(R.id.username);
        this.loginB = (Button) this.getActivity().findViewById(R.id.loginButton);
        this.logoutB = (Button) this.getActivity().findViewById(R.id.logoutButton);
        this.registerB = (Button) this.getActivity().findViewById(R.id.registerButton);

        if(UserOffline.activeUser != null) {
            this.usernameTV.setText(UserOffline.activeUser.getUsername());
            this.loginB.setVisibility(View.INVISIBLE);
            this.registerB.setVisibility(View.INVISIBLE);
        }
        else{
            this.usernameTV.setText("Belum Login");
            this.logoutB.setVisibility(View.INVISIBLE);
        }

        logoutB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ProfilUserFragment.this.controller.logout(ProfilUserFragment.this);
            }

        });

        loginB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilUserFragment.this.getContext(), LoginActivity.class);
                ProfilUserFragment.this.getActivity().startActivityForResult(intent, 1);
            }

        });

        registerB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilUserFragment.this.getContext(), RegisterActivity.class);
                ProfilUserFragment.this.getActivity().startActivityForResult(intent, 2);
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Jika login berhasil
        if(requestCode == 1 && resultCode == 200) {
            if(data.getBooleanExtra("success", false)) {
                FragmentTransaction transaction = this.getActivity().getSupportFragmentManager().beginTransaction();
                transaction.detach(ProfilUserFragment.this);
                transaction.attach(ProfilUserFragment.this);
                transaction.commit();
            }
        }
    }

}
