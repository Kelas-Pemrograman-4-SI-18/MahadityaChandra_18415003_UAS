package com.chandra.sembakocan.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.chandra.sembakocan.R;
import com.chandra.sembakocan.server.baseUrl;
import com.squareup.picasso.Picasso;

public class detailpembeli extends AppCompatActivity {

    EditText edtkodesembako, edtnamaSembako, edtmerkSembako,  edtsatuanSembako, edthargaSembako;
    ImageView imgGambarSembako;

    String strkodeSembako, strnamaSembako, strmerkSembako,strsatuanSembako, strhargaSembako, strGambar, _id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpembeli);

        edtkodesembako = (EditText) findViewById(R.id.edtkodesembako);
        edtnamaSembako = (EditText) findViewById(R.id.edtnamaSembako);
        edtmerkSembako = (EditText) findViewById(R.id.edtmerkSembako);
        edtsatuanSembako = (EditText) findViewById(R.id.edtsatuanSembako);
        edthargaSembako = (EditText) findViewById(R.id.edthargaSembako);

        imgGambarSembako = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strkodeSembako = i.getStringExtra("kodesembako");
        strnamaSembako = i.getStringExtra("namaSembako");
        strmerkSembako = i.getStringExtra("merkSembako");
        strsatuanSembako = i.getStringExtra("satuanSembako");
        strhargaSembako = i.getStringExtra("hargaSembako");
        strGambar = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtkodesembako.setText(strkodeSembako);
        edtnamaSembako.setText(strnamaSembako);
        edtmerkSembako.setText(strmerkSembako);
        edtsatuanSembako.setText(strsatuanSembako);
        edthargaSembako.setText(strhargaSembako);
        Picasso.get().load(baseUrl.baseUrl + "gambar/" + strGambar)
                .into(imgGambarSembako);
    }
}