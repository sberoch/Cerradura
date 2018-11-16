package com.eriochrome.cerradura;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class ComunicacionBT extends AppCompatActivity {

    private BluetoothAdapter btAdapter;

    private ArrayList<String> dispositivos;
    private DevicesAdapter adapter;

    private ListView listaDispositivosView;

    private Button refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicacion_bt);

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        listaDispositivosView = findViewById(R.id.listview);

        dispositivos = new ArrayList<>();
        adapter = new DevicesAdapter(dispositivos, this);
        listaDispositivosView.setAdapter(adapter);

        refresh = findViewById(R.id.refresh);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPairedDevices();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!btAdapter.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
        }
        showPairedDevices();
    }


    public void showPairedDevices() {
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                dispositivos.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            Toast.makeText(this, "No hay dispositivos vinculados", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }
}