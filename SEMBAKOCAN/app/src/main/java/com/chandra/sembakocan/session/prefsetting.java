package com.chandra.sembakocan.session;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.chandra.sembakocan.admin.homeAdmin;
import com.chandra.sembakocan.pembeli.Homepembeli;

public class prefsetting {
    public static String _id;
    public static String username;
    public static String namaLengkap;
    public static String email;
    public static String noTelp;
    public static String role;
    Activity activity;

    public prefsetting(Activity activity){
        this.activity = activity;

    }

    public SharedPreferences getSharePreferances(){
        SharedPreferences preferences = activity.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        return preferences;
    }
    public void islogin(sessionmanager session, SharedPreferences pref){
        session = new sessionmanager(activity);
        if(session.isLoggedIn()){
            pref = getSharePreferances();
            _id = pref.getString("_id", "");
            username = pref.getString("username", "");
            namaLengkap = pref.getString("namaLengkap", "");
            email = pref.getString("email", "");
            noTelp = pref.getString("noTelp", "");
            role = pref.getString("role", "");

        }else {
            session.setLogin(false);
            session.setSessid(0);
            Intent i = new Intent(activity,activity.getClass());
            activity.startActivity(i);
            activity.finish();

        }
    }
    public void checklogin(sessionmanager session, SharedPreferences pref){
        session = new sessionmanager(activity);
        _id = pref.getString("_id", "");
        username = pref.getString("username", "");
        namaLengkap = pref.getString("namaLengkap", "");
        email = pref.getString("email", "");
        noTelp = pref.getString("noTelp", "");
        role = pref.getString("role", "");
        if (session.isLoggedIn()) {
            if(role.equals("1")){
                Intent i = new Intent(activity, homeAdmin.class);
                activity.startActivity(i);
                activity.finish();
            }else{
                Intent i = new Intent(activity, Homepembeli.class);
                activity.startActivity(i);
                activity.finish();

            }

        }
    }
    public void storeRegIdSharePreferences(Context context, String _id, String username, String namaLengkap, String email, String noTelp, String role, SharedPreferences prefs){

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("_id", _id);
        editor.putString("username", username);
        editor.putString("namaLengkap", namaLengkap);
        editor.putString("email", email);
        editor.putString("noTelp", noTelp);
        editor.putString("role", role);
        editor.commit();
    }



}
