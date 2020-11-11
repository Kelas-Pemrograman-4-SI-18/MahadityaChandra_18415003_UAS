package com.chandra.sembakocan.pembeli;

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
import com.chandra.sembakocan.users.LoginActivity;
import com.chandra.sembakocan.users.regisActivity;
import com.ornach.nobobutton.NoboButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.chandra.sembakocan.session.prefsetting.username;

public class transaksi extends AppCompatActivity {

    Button btnbackhome;
    NoboButton btntransaksi;
    EditText edtusernametransaksi, edtkodesembako1,edtkodesembako2,edtkodesembako3, edtjumlahbeli1,edtjumlahbeli2,edtjumlahbeli3, edtalamat, edtnoTelppenerima;
    ProgressDialog pDialog;

    private RequestQueue mRequestQueue;


    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        edtusernametransaksi = (EditText) findViewById(R.id.edtusernametransaksi);
        edtkodesembako1 = (EditText) findViewById(R.id.edtkodesembako1);
        edtjumlahbeli1 = (EditText) findViewById(R.id.jumlahbeli1);
        edtkodesembako2 = (EditText) findViewById(R.id.kodesembako2);
        edtjumlahbeli2 = (EditText) findViewById(R.id.jumlahbeli2);
        edtkodesembako3 = (EditText) findViewById(R.id.kodesembako3);
        edtjumlahbeli3 = (EditText) findViewById(R.id.jumlahbeli3);
        edtalamat = (EditText) findViewById(R.id.alamat);
        edtnoTelppenerima = (EditText) findViewById(R.id.noTelppenerima);

        btnbackhome = (Button) findViewById(R.id.btnbackhome);
        btntransaksi = (NoboButton) findViewById(R.id.btntransaksi);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(transaksi.this, Homepembeli.class);
                startActivity(i);
                finish();
            }
        });

        btntransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strusernametransaksi      = edtusernametransaksi.getText().toString();
                String strkodesembako1   = edtkodesembako1.getText().toString();
                String strjumlahbeli1         = edtjumlahbeli1.getText().toString();
                String strkodesembako2   = edtkodesembako2.getText().toString();
                String strjumlahbeli2         = edtjumlahbeli2.getText().toString();
                String strkodesembako3   = edtkodesembako3.getText().toString();
                String strjumlahbeli3         = edtjumlahbeli3.getText().toString();
                String stralamat        = edtalamat.getText().toString();
                String strnoTelppenerima      = edtnoTelppenerima.getText().toString();


                if(strusernametransaksi.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                } else if(strkodesembako1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Kode Sembako Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                }else if(strjumlahbeli1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Jumlah Beli Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                } else if(strkodesembako2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Kode Sembako Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                }else if(strjumlahbeli2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Jumlah Beli Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                } else if(strkodesembako3.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Kode Sembako Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                }else if(strjumlahbeli3.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Jumlah Beli Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                }else if(stralamat.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Alamat Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                }else if(strnoTelppenerima.isEmpty()){
                    Toast.makeText(getApplicationContext(),"no Telp Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                }else{
                    transaksi(strusernametransaksi, strkodesembako1, strjumlahbeli1,strkodesembako2,strjumlahbeli2,strkodesembako3,strjumlahbeli3,stralamat,strnoTelppenerima);

                }


            }
        });
    }

    public void transaksi (String usernametransaksi, String kodesembako1, String jumlahbeli1 , String kodesembako2, String jumlahbeli2 ,String kodesembako3, String jumlahbeli3 ,String alamat, String noTelppenerima){

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", usernametransaksi);
        params.put("kodesembako1", kodesembako1);
        params.put("jumlahbeli1", jumlahbeli1);
        params.put("kodesembako2", kodesembako2);
        params.put("jumlahbeli2", jumlahbeli2);
        params.put("kodesembako3", kodesembako3);
        params.put("jumlahbeli3", jumlahbeli3);
        params.put("alamat", alamat);
        params.put("noTelppenerima", noTelppenerima);

        pDialog.setMessage( "Mohon Tunggu " );
        showDialog();


        JsonObjectRequest req = new JsonObjectRequest(baseUrl.transaksi, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status = response.getBoolean("error");

                            if(status == false){
                                Toast.makeText(getApplicationContext(),strMsg,Toast.LENGTH_LONG).show();
                                Intent i = new Intent(transaksi.this, Homepembeli.class);
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