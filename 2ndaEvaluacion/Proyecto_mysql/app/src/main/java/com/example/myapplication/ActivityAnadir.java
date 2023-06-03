package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.clases.Comida;
import com.example.myapplication.controladores.ComidaControler;

public class ActivityAnadir extends AppCompatActivity {

    private EditText nuevoIdComida = null;
    private EditText nuevoNombre = null;
    private EditText nuevoPrecio = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);

        nuevoIdComida = (EditText) findViewById(R.id.editTextIdComida);
        nuevoNombre = (EditText) findViewById(R.id.editTextNombre);
        nuevoPrecio = (EditText) findViewById(R.id.editTextPrecio);


    }

    public void InsertarCarne(View view){

        String CodCarne = String.valueOf(nuevoIdComida.getText());
        String NombreCarne = String.valueOf(nuevoNombre.getText());
        String TextoPrecio = String.valueOf(nuevoPrecio.getText());

        //Ahora pongo los errores si no pones nada
        if (CodCarne.isEmpty()){
            nuevoIdComida.setError("Escribe algo");
        }
        if (NombreCarne.isEmpty()){
            nuevoNombre.setError("Pon algo");
        }
        if (TextoPrecio.isEmpty()){
            nuevoPrecio.setError("Tienes que poner un precio");
        }

        int idComida = Integer.valueOf(CodCarne);
        double precio = Double.valueOf(TextoPrecio);

        //--------------------------------------------------------
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("son correctos los datos? (SI/NO)");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Comida c = new Comida(idComida,NombreCarne,precio);
                boolean guardadoOK = ComidaControler.guardarComida(c);
                if(guardadoOK)
                {
                    mostrarToast("guardado realizado correctamente");
                }
                else{
                    mostrarToast("no se pudo guardar el dato");
                }
                Log.i("sql", c.toString());

            }
        });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mostrarToast("cancelado");
            }
        });
        alerta1.show();

    }

    //Insertar fruta
    public void InsertarFruta(View view){

        String CodFruta = String.valueOf(nuevoIdComida.getText());
        String NombreFruta = String.valueOf(nuevoNombre.getText());
        String TextoPrecio = String.valueOf(nuevoPrecio.getText());

        //Ahora pongo los errores si no pones nada
        if (CodFruta.isEmpty()){
            nuevoIdComida.setError("Escribe algo");
        }
        if (NombreFruta.isEmpty()){
            nuevoNombre.setError("Pon algo");
        }
        if (TextoPrecio.isEmpty()){
            nuevoPrecio.setError("Tienes que poner un precio");
        }

        int idComida = Integer.valueOf(CodFruta);
        double precio = Double.valueOf(TextoPrecio);

        //--------------------------------------------------------
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("son correctos los datos? (SI/NO)");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Comida c = new Comida(idComida,NombreFruta,precio);
                boolean guardadoOK = ComidaControler.guardarFruta(c);
                if(guardadoOK)
                {
                    mostrarToast("guardado realizado correctamente");
                }
                else{
                    mostrarToast("no se pudo guardar el dato");
                }
                Log.i("sql", c.toString());

            }
        });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mostrarToast("cancelado");
            }
        });
        alerta1.show();

    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

}
