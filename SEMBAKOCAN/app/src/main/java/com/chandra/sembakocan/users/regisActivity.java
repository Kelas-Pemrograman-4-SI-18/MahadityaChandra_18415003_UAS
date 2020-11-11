package com.chandra.sembakocan.users;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.chandra.sembakocan.server.baseUrl;
import com.ornach.nobobutton.NoboButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class regisActivity extends AppCompatActivity {

    Button btnbacklogin;
    NoboButton btnregis;
    EditText edtusername, edtnamaLengkap, edtemail, edtnoTelp, edtpassword;
    ProgressDialog pDialog;

    private RequestQueue mRequestQueue;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        edtusername = (EditText) findViewById(R.id.edtusername);
        edtnamaLengkap = (EditText) findViewById(R.id.edtnamaLengkap);
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtnoTelp = (EditText) findViewById(R.id.edtnotelp);
        edtpassword = (EditText) findViewById(R.id.edtpassword);

        btnbacklogin = (Button) findViewById(R.id.btnbacklogin);
        btnregis = (NoboButton) findViewById(R.id.btnregis);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(regisActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strusername      = edtusername.getText().toString();
                String strnamaLengkap   = edtnamaLengkap.getText().toString();
                String stremail         = edtemail.getText().toString();
                String strnoTelp        = edtnoTelp.getText().toString();
                String strpassword      = edtpassword.getText().toString();

                if(strusername.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                } else if(strnamaLengkap.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Nama Lengkap Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                }else if(stremail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"email Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                }else if(strnoTelp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"No Telp Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                }else if(strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                }else{
                    registrasi(strusername,strnamaLengkap,stremail,strnoTelp,strpassword);

                }


            }
        });
    }

    public void registrasi (String username, String namaLengkap, String email, String noTelp, String password){

    // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("namaLengkap", namaLengkap);
        params.put("email", email);
        params.put("noTelp", noTelp);
        params.put("role", "2");
        params.put("password", password);

        pDialog.setMessage( "Mohon Tunggu " );
        showDialog();


        JsonObjectRequest req = new JsonObjectRequest(baseUrl.Registrasi, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status = response.getBoolean("error");

                            if(status == false){
                                Toast.makeText(getApplicationContext(),strMsg,Toast.LENGTH_LONG).show();
                                Intent i = new Intent(regisActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
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