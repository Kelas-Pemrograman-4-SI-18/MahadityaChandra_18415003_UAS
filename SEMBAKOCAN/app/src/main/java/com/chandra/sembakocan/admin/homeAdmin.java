package com.chandra.sembakocan.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.chandra.sembakocan.R;
import com.chandra.sembakocan.session.prefsetting;
import com.chandra.sembakocan.session.sessionmanager;
import com.chandra.sembakocan.users.LoginActivity;

public class homeAdmin extends AppCompatActivity {

    sessionmanager session;
    SharedPreferences prefs;
    prefsetting prefsetting;
    CardView cardexit, carddatasembako,cardinput,cardprofile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        prefsetting = new prefsetting(this);
        prefs = prefsetting.getSharePreferances();

        session = new sessionmanager(homeAdmin.this);
        prefsetting.islogin(session, prefs);

        cardexit = (CardView) findViewById(R.id.cardexit);
        carddatasembako = (CardView) findViewById(R.id.carddatasembako);
        cardinput = (CardView) findViewById(R.id.cardinput);
        cardprofile = (CardView) findViewById(R.id.cardprofile);

        cardexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(homeAdmin.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        carddatasembako.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homeAdmin.this, Activitydatasembako.class);
                startActivity(i);
                finish();
            }
        });
        cardinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homeAdmin.this, inputsembako.class);
                startActivity(i);
                finish();
            }
        });
        cardprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homeAdmin.this, profile.class);
                startActivity(i);
                finish();
            }
        });


    }
}