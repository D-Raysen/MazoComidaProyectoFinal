package com.example.simulacroexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    

    //Variables del intent
    private String NumeroEntradas = null;
    private String TipoDeEntrada = null;

    //Variables originales del activity
    private TextView PrecioZona = null;
    private TextView Cantidad = null;
    private RadioGroup GrupoBotones = null;
    private RadioButton BotonSI, BotonNO = null;
    private  TextView Total = null;



    //Variables de precio para usarlas en el metodo total mas tarde
    private  int PrecioDeZona = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        BotonSI = (RadioButton) findViewById(R.id.RBsi);
        BotonNO = (RadioButton) findViewById(R.id.RBno);

        PrecioZona = (TextView) findViewById(R.id.txtZona);
        Cantidad = (TextView) findViewById(R.id.txtCantidad);
        Total = (TextView) findViewById(R.id.txtTotal);


        //Cosas del intent
        Intent intent = getIntent();
        if(intent != null){
            NumeroEntradas = intent.getStringExtra(MainActivity.CantidadEntradas);
            TipoDeEntrada = intent.getStringExtra("TipoEntrada");
        }

        //Aqui le ponemos en el text view el precio de la zona llamando al metodo
        PrecioZona.setText(ObtenerPrecioZona(TipoDeEntrada));
        Cantidad.setText(NumeroEntradas);

        Total.setText(ObtenerPrecioTotal(NumeroEntradas, TipoDeEntrada));



    }

    public String ObtenerPrecioZona(String tipoDeEntrada){
        String PrecioZona = "5€";
        if(tipoDeEntrada.equalsIgnoreCase("ZONA GENERAL")){

            PrecioZona = "5€";

        }else if(tipoDeEntrada.equalsIgnoreCase("ZONA GOLES")){
            PrecioZona = "3€";
        }else if(tipoDeEntrada.equalsIgnoreCase("ZONA ANFITEATRO")){
            PrecioZona = "4€";
        }
        return PrecioZona;
    }

    public double ObtenerPrecioTotal(String CantidadEntradas, String TipoEntrada){
        int CantEntradas = Integer.valueOf(CantidadEntradas);
        int Precio = 0;
        if(TipoEntrada.equalsIgnoreCase("ZONA GENERAL")){

            Precio = 5 * CantEntradas;

        }else if(TipoEntrada.equalsIgnoreCase("ZONA GOLES")){
            Precio = 3 * CantEntradas;
        }else if(TipoEntrada.equalsIgnoreCase("ZONA ANFITEATRO")){
            Precio = 4 * CantEntradas;
        }
        return Precio;
    }

    public void SelectSI(View view){
        if(BotonSI.isChecked()== true){

        }
    }
    public void SelectNO(View view){
        if(BotonNO.isChecked()== true){

        }
    }
}