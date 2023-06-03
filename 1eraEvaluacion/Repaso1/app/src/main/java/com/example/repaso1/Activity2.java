package com.example.repaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private TextView txt_nombre;
    private  TextView textView_direccion;
    private  TextView textView_estado;
    private String Nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        txt_nombre = (TextView) findViewById(R.id.textNombreRecibido);
        Intent intent = getIntent();
        if(intent != null){
            Nombre = intent.getStringExtra("NombrePersona");
            txt_nombre.setText(Nombre);
        }

    }
}