package com.chandra.sembakocan.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.chandra.sembakocan.R;
import com.chandra.sembakocan.session.prefsetting;

public class profile extends AppCompatActivity {

    TextView txtUsername, txtNamaLengkap, txtEmail, txtNotelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile User");

        txtUsername = (TextView) findViewById(R.id.username);
        txtNamaLengkap = (TextView) findViewById(R.id.namaLengkap);
        txtEmail = (TextView) findViewById(R.id.email);
        txtNotelp = (TextView) findViewById(R.id.noTelp);

        txtUsername.setText(prefsetting.username);
        txtNamaLengkap.setText(prefsetting.namaLengkap);
        txtEmail.setText(prefsetting.email);
        txtNotelp.setText(prefsetting.noTelp);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(profile.this, homeAdmin.class);
        startActivity(i);
        finish();
    }
}