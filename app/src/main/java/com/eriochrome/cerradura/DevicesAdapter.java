package com.eriochrome.cerradura;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DevicesAdapter extends BaseAdapter {

    ArrayList<String> dispositivos;
    Context context;

    public DevicesAdapter(ArrayList<String> dispositivos, Context context) {
        this.dispositivos = dispositivos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dispositivos.size();
    }

    @Override
    public Object getItem(int position) {
        return dispositivos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_device, parent, false);
        }

        TextView device = convertView.findViewById(R.id.device);
        device.setText((String) getItem(position));
        return convertView;
    }
}
