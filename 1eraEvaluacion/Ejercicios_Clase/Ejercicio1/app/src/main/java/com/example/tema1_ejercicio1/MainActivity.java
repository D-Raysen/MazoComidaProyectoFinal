package com.example.tema1_ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edit_Numero = null;
    TextView txt_resultado = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    edit_Numero = findViewById(R.id.Numero);
    txt_resultado = findViewById(R.id.txt_resultado);




    }

    public void CalcularCuadrado(View view){
            //Primero hay que coger el numero del editText
        String texto = String.valueOf(edit_Numero.getText());
        int numero = Integer.valueOf(texto);
        int operacion = numero*numero;
        txt_resultado.setText(String.valueOf(operacion));


    }
}