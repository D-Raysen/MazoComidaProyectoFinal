package com.example.formularioproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.formularioproductos.clases.Producto;

public class Ventana2Activity extends AppCompatActivity {

    private TextView txt_mostrarProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana2);

        txt_mostrarProducto = (TextView) findViewById(R.id.MostrarProducto);

        Intent intent = getIntent();
        if(intent != null){
            Producto p = (Producto) intent.getSerializableExtra(MainActivity.EXTRA_PRODUCTO);
        }

    }
}