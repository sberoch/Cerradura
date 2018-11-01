package com.eriochrome.cerradura;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.widget.Toast;



class BluetoothComunicacion {

    private int codigoIngresado;
    private Context context;
    private BluetoothAdapter myBluetooth = null;

    public BluetoothComunicacion(int codigoIngresado, Context context) {
        this.codigoIngresado = codigoIngresado;
        this.context = context;
    }

    public void setUp() {
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (myBluetooth == null) {
            Toast.makeText(context, "Dispositivo Bluetooth no disponible.", Toast.LENGTH_LONG).show();
        }
        else {

        }
    }


}
