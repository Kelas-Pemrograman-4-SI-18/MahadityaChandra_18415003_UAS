package com.chandra.sembakocan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chandra.sembakocan.R;
import com.chandra.sembakocan.model.modelsembako;
import com.chandra.sembakocan.server.baseUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adaptersembako extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<modelsembako> item;

    public adaptersembako(Activity activity, List<modelsembako> item) {
        this.activity = activity;
        this.item = item;
    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_sembako, null);


        TextView namaSembako = (TextView) convertView.findViewById(R.id.txtnamaSembako);
        TextView kodesembako = (TextView) convertView.findViewById(R.id.txtkodeSembako);
        TextView merkSembako     = (TextView) convertView.findViewById(R.id.txtmerkSembako);
        TextView hargaSembako         = (TextView) convertView.findViewById(R.id.txtharga);
        TextView satuan         = (TextView) convertView.findViewById(R.id.txtsatuanSembako);
        ImageView gambar         = (ImageView) convertView.findViewById(R.id.gambar);

        kodesembako.setText(item.get(position).getKodesembako());
        namaSembako.setText(item.get(position).getNamaSembako());
        merkSembako.setText(item.get(position).getMerkSembako());
        satuan.setText(item.get(position).getSatuanSembako());
        hargaSembako.setText("Rp." + item.get(position).getHargaSembako());
        Picasso.get().load(baseUrl.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambar);
        return convertView;
    }
}
