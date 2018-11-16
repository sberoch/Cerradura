package com.eriochrome.cerradura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.eriochrome.cerradura.R.drawable.fondo_correcto;

public class Teclado extends AppCompatActivity implements Button.OnClickListener{

    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private Button buttonOK, buttonDel;
    TextView codigoDesbloqueoTextView;

    private static final int CODIGO_DESBLOQUEO = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado);

        codigoDesbloqueoTextView = findViewById(R.id.codigo_ingresado);
        setupButtons();

    }


    private void agregarACodigoDeDesbloqueo(String s) {
        if (codigoDesbloqueoTextView.getText().length() < 4) {
            codigoDesbloqueoTextView.setText(String.format("%s%s", codigoDesbloqueoTextView.getText(), s));
        }
    }


    private void eliminarCodigo() {
        codigoDesbloqueoTextView.setText("");
    }


    private void enviarAArduino() {

        if (codigoDesbloqueoTextView.getText().toString().length() <= 0)
        {
            Toast.makeText(this, "Ingrese un codigo", Toast.LENGTH_SHORT).show();
        }
         else
        {
            int ingresado = Integer.valueOf(codigoDesbloqueoTextView.getText().toString());

            if (esCodigoValido(ingresado))
            {
                codigoDesbloqueoTextView.setBackgroundResource(R.drawable.fondo_correcto);
                //TODO: enviar a arduino.
                Intent i = new Intent(Teclado.this, ComunicacionBT.class);
                startActivity(i);
            }
            else
            {
                codigoDesbloqueoTextView.setBackgroundResource(R.drawable.fondo_error);
            }
        }

    }


    private boolean esCodigoValido(int ingresado) {
        return ingresado == CODIGO_DESBLOQUEO;
    }


    private void setupButtons() {

        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2=  findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        buttonOK = findViewById(R.id.button_ok);
        buttonDel = findViewById(R.id.button_delete);

        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        buttonOK.setOnClickListener(this);
        buttonDel.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.button0:
                agregarACodigoDeDesbloqueo("0");
                break;
            case R.id.button1:
                agregarACodigoDeDesbloqueo("1");
                break;
            case R.id.button2:
                agregarACodigoDeDesbloqueo("2");
                break;
            case R.id.button3:
                agregarACodigoDeDesbloqueo("3");
                break;
            case R.id.button4:
                agregarACodigoDeDesbloqueo("4");
                break;
            case R.id.button5:
                agregarACodigoDeDesbloqueo("5");
                break;
            case R.id.button6:
                agregarACodigoDeDesbloqueo("6");
                break;
            case R.id.button7:
                agregarACodigoDeDesbloqueo("7");
                break;
            case R.id.button8:
                agregarACodigoDeDesbloqueo("8");
                break;
            case R.id.button9:
                agregarACodigoDeDesbloqueo("9");
                break;
            case R.id.button_ok:
                enviarAArduino();
                break;
            case R.id.button_delete:
                eliminarCodigo();
                break;
        }
    }
}


