package com.chandra.sembakocan.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chandra.sembakocan.R;
import com.chandra.sembakocan.adapter.adaptersembako;
import com.chandra.sembakocan.admin.Activitydatasembako;
import com.chandra.sembakocan.admin.editdatasembako;
import com.chandra.sembakocan.admin.homeAdmin;
import com.chandra.sembakocan.model.modelsembako;
import com.chandra.sembakocan.server.baseUrl;
import com.chandra.sembakocan.session.prefsetting;
import com.chandra.sembakocan.session.sessionmanager;
import com.chandra.sembakocan.users.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Homepembeli extends AppCompatActivity {

    ProgressDialog pDialog;

    adaptersembako adapter;
    ListView list;

    ArrayList<modelsembako> newsList = new ArrayList<modelsembako>();
    private RequestQueue mRequestQueue;

    FloatingActionButton floatingExit, floatingtransaksi;
    sessionmanager session;
    SharedPreferences prefs;
    prefsetting prefsetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepembeli);
        prefsetting = new prefsetting(this);
        prefs = prefsetting.getSharePreferances();

        session = new sessionmanager(Homepembeli.this);

        prefsetting.islogin(session, prefs);

        getSupportActionBar().setTitle("Data Sembako");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        floatingExit = (FloatingActionButton) findViewById(R.id.exit);
        floatingtransaksi = (FloatingActionButton) findViewById(R.id.transaksi);

        floatingtransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Homepembeli.this, transaksi.class);
                startActivity(i);
                finish();
            }
        });

        floatingExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(Homepembeli.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        newsList.clear();
        adapter = new adaptersembako(Homepembeli.this, newsList);
        list.setAdapter(adapter);
        getAllBuku();



    }



    @Override
    public void onBackPressed() {
        Intent i = new Intent(Homepembeli.this, homeAdmin.class);
        startActivity(i);
        finish();
    }

    private void getAllBuku() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, baseUrl.datasembako, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data sembako = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final modelsembako sembako = new modelsembako();
                                    final String _id = jsonObject.getString("_id");
                                    final String namaSembako = jsonObject.getString("namaSembako");
                                    final String kodesembako = jsonObject.getString("kodesembako");
                                    final String merkSembako = jsonObject.getString("merkSembako");
                                    final String hargaSembako = jsonObject.getString("hargaSembako");
                                    final String satuanSembako = jsonObject.getString("satuanSembako");
                                    final String gambar = jsonObject.getString("gambar");
                                    sembako.setKodesembako(kodesembako);
                                    sembako.setNamaSembako(namaSembako);
                                    sembako.setMerkSembako(merkSembako);
                                    sembako.setHargaSembako(hargaSembako);
                                    sembako.setSatuanSembako(satuanSembako);
                                    sembako.setGambar(gambar);
                                    sembako.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(Homepembeli.this, detailpembeli .class);
                                            a.putExtra("kodesembako", newsList.get(position).getKodesembako());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("namaSembako", newsList.get(position).getNamaSembako());
                                            a.putExtra("merkSembako", newsList.get(position).getMerkSembako());
                                            a.putExtra("hargaSembako", newsList.get(position).getHargaSembako());
                                            a.putExtra("satuanSembako", newsList.get(position).getSatuanSembako());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(sembako);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}