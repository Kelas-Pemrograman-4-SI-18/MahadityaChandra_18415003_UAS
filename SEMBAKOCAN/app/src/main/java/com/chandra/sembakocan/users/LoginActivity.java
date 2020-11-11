package com.chandra.sembakocan.users;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chandra.sembakocan.R;
import com.chandra.sembakocan.admin.homeAdmin;
import com.chandra.sembakocan.pembeli.Homepembeli;
import com.chandra.sembakocan.server.baseUrl;
import com.chandra.sembakocan.session.prefsetting;
import com.chandra.sembakocan.session.sessionmanager;
import com.ornach.nobobutton.NoboButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    Button btnregis;
    NoboButton btnlogin;
    EditText edtusername, edtpassword;
    ProgressDialog pDialog;

    sessionmanager session;
    SharedPreferences prefs;
    prefsetting prefsetting;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);


        btnregis = (Button) findViewById(R.id.btntoregis);
        btnlogin = (NoboButton) findViewById(R.id.login);
        edtusername = (EditText) findViewById(R.id.edtusername);
        edtpassword = (EditText) findViewById(R.id.edtpassword);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        prefsetting = new prefsetting(this);
        prefs = prefsetting.getSharePreferances();

        session = new sessionmanager(this);
        prefsetting.checklogin(session, prefs);

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,regisActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strusername = edtusername.getText().toString();
                String strpassword = edtpassword.getText().toString();

                if (strusername.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if (strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else{
                    Login(strusername,strpassword);
                }
            }
        });

    }


    public void Login (String username, String password){

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);

        pDialog.setMessage( "Mohon Tunggu " );
        showDialog();


        JsonObjectRequest req = new JsonObjectRequest(baseUrl.login, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();

                        try {
                            String strMsg = response.getString("msg");
                            boolean status = response.getBoolean("error");

                            if(status == false){
                                Toast.makeText(getApplicationContext(),strMsg,Toast.LENGTH_LONG).show();
                                String data = response.getString("data");
                                JSONObject jsonObject = new JSONObject(data);
                                String role = jsonObject.getString("role");
                                String _id = jsonObject.getString("_id");
                                String username = jsonObject.getString("username");
                                String namaLengkap = jsonObject.getString("namaLengkap");
                                String email = jsonObject.getString("email");
                                String noTelp = jsonObject.getString("noTelp");
                                session.setLogin(true);
                                prefsetting.storeRegIdSharePreferences(LoginActivity.this, _id,username,namaLengkap,email,noTelp,role, prefs);

                                if(role.equals("1")) {
                                    Intent i = new Intent(LoginActivity.this, homeAdmin.class);
                                    startActivity(i);
                                    finish();
                                }else{
                                    Intent i = new Intent(LoginActivity.this, Homepembeli.class);
                                    startActivity(i);
                                    finish();
                                }
                            }else{


                                Toast.makeText(getApplicationContext(),strMsg,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }

        });
        mRequestQueue.add(req);
    }
    private void showDialog(){

        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }
    private void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }

    }
}
