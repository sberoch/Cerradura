package com.eriochrome.cerradura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Teclado extends AppCompatActivity {

    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private Button buttonOK, buttonDel;
    TextView codigoDesbloqueo;

    private Button.OnClickListener clickListener = new Button.OnClickListener() {
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
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado);

        codigoDesbloqueo = findViewById(R.id.codigo_ingresado);
        setupButtons();


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

        b0.setOnClickListener(clickListener);
        b1.setOnClickListener(clickListener);
        b2.setOnClickListener(clickListener);
        b3.setOnClickListener(clickListener);
        b4.setOnClickListener(clickListener);
        b5.setOnClickListener(clickListener);
        b6.setOnClickListener(clickListener);
        b7.setOnClickListener(clickListener);
        b8.setOnClickListener(clickListener);
        b9.setOnClickListener(clickListener);
        buttonOK.setOnClickListener(clickListener);
        buttonDel.setOnClickListener(clickListener);

    }



    private void agregarACodigoDeDesbloqueo(String s) {
        if (codigoDesbloqueo.getText().length() < 4) {
            codigoDesbloqueo.setText(String.format("%s%s", codigoDesbloqueo.getText(), s));
        }
    }

    private void eliminarCodigo() {
        codigoDesbloqueo.setText("");
    }


    private void enviarAArduino() {
    }
}


