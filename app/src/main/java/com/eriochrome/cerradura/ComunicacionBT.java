package com.eriochrome.cerradura;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class ComunicacionBT extends AppCompatActivity {

    private int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;

    private BluetoothAdapter btAdapter;

    private ArrayList<String> dispositivos;
    private DevicesAdapter adapter;

    private ListView listaDispositivosView;

    private Button refrescar;
    private Button buscar;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Toast.makeText(ComunicacionBT.this, "Se encontro un dispositivo.", Toast.LENGTH_SHORT).show();
                BluetoothDevice dispositivo = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String nombreDisp = dispositivo.getName();
                String addressDisp = dispositivo.getAddress();
                dispositivos.add(nombreDisp + "\n" + addressDisp);
                adapter.notifyDataSetChanged();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicacion_bt);

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        listaDispositivosView = findViewById(R.id.listview);
        dispositivos = new ArrayList<>();
        adapter = new DevicesAdapter(dispositivos, this);
        listaDispositivosView.setAdapter(adapter);

        refrescar = findViewById(R.id.refresh);
        buscar = findViewById(R.id.buscar);

        refrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispositivos.clear();
                showPairedDevices();
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btAdapter.isDiscovering()) {
                    Toast.makeText(ComunicacionBT.this ,"Ya estabas buscando dispositivos", Toast.LENGTH_SHORT).show();
                    btAdapter.cancelDiscovery();
                }
                btAdapter.startDiscovery();
                Toast.makeText(ComunicacionBT.this, "Buscando dispositivos..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (btAdapter.isDiscovering()) {
            btAdapter.cancelDiscovery();
        }
        unregisterReceiver(mReceiver);
    }
}