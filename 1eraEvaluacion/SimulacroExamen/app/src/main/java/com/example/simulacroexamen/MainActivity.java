package com.example.simulacroexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText  edt_cantidadEntradas;
    private Spinner spZona;

    String CantidadEntradas = null;
    String Zona = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_cantidadEntradas = (EditText) findViewById(R.id.editTextNentradas);
        spZona = (Spinner) findViewById(R.id.spinner);


        if(spZona != null)
        {
            // obtener lista de Zonas de la base datos
            String TipoEntrada [] = {"ZONA GENERAL", "ZONA GOLES", "ZONA ANFITEATRO"};
            spZona.setOnItemSelectedListener(this);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, TipoEntrada);
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            spZona.setAdapter(adapter);
        }
    }

    public  void siguiente(View view){
        CantidadEntradas = String.valueOf(edt_cantidadEntradas.getText());
        if(CantidadEntradas.isEmpty()){
            edt_cantidadEntradas.setError("Debes poner algo");
            return;
        }
        int cantidad = Integer.valueOf(String.valueOf(edt_cantidadEntradas));
        boolean validado = validarCantidad(cantidad);
        if(validado == false){
            edt_cantidadEntradas.setError("Debes poner una cantidad de entradas entre el 1 al 10");
            return;
        }else{
            //Primero se declara el intent
            Intent intent = new Intent(this, Activity2.class);
            //Luego se pone las variables que queremos que se pasen a la siguiente actividad
            intent.putExtra("CantidadEntradas", CantidadEntradas);
            intent.putExtra("TipoEntrada", Zona);
            startActivity(intent);
        }


    }
    private boolean validarCantidad(int cant) {
        if(cant >0 && cant <= 10)
        {
            return true;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Zona = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        Zona = adapterView.getItemAtPosition(0).toString();

    }
}