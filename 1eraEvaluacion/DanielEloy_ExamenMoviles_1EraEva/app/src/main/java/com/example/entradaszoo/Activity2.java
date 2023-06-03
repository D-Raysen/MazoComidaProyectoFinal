package com.example.entradaszoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    private TextView PrecioTotal = null;
    private String txtPrecio = null;
    private int precioTotal = 0;

    private RadioButton Tarjeta = null;
    private  RadioButton Paypal = null;
    private  Boolean PaypalOK = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        PrecioTotal = (TextView) findViewById(R.id.txtTOTAL) ;
        //Recibimos el intent del anterior activity
        Intent intent = getIntent();
      txtPrecio = intent.getStringExtra("PrecioTotal");
        PrecioTotal.setText(txtPrecio);

        Tarjeta = (RadioButton) findViewById(R.id.RB_Tarjeta);
        Paypal = (RadioButton) findViewById(R.id.RB_Paypal);



    }
    public void MetodoDePago(View view) {
        RadioButton boton = (RadioButton) view;
        if(boton.isChecked()) {
            switch (boton.getId()) {
                case R.id.RB_Paypal:
                    PaypalOK = true;
                    

                    break;
                case R.id.RB_Tarjeta:
                    PaypalOK = false;
                    break;
            }
        }
    }

}