package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.clases.AdapterDatos;
import com.example.myapplication.clases.Comida;
import com.example.myapplication.clases.ConfiguracionDB;
import com.example.myapplication.clases.comidaBD;
import com.example.myapplication.controladores.ComidaControler;

import java.sql.Connection;
import java.util.ArrayList;

public class BorrarActivity extends AppCompatActivity {

    private RecyclerView rv = null;
    private EditText idElegido = null;
    private ArrayList<Comida> COMIDA = null;
    private comidaBD BaseDeDatos = null;
    private AdapterDatos Adapter;
    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        idElegido = (EditText) findViewById(R.id.editTextIdComida);
    /*
        rv = (RecyclerView) findViewById(R.id.recyclerViewComida);

        COMIDA = new ArrayList<Comida>(ComidaControler.obtenerTodasLasCarnes());
        Adapter = new AdapterDatos(this,COMIDA);

        rv.setAdapter(Adapter);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            rv.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            // In portrait
            rv.setLayoutManager(new LinearLayoutManager(this));
        }


        Connection c =  ConfiguracionDB.conectarConBaseDeDatos();
        if(c == null)
        {
            Log.i("sql", "error al conectar");
        }
        else{
            Log.i("sql", "conectado correctamente");
        }




*/

    }

    public void BotonBorrar(View view) {
        String id_aBorrar = String.valueOf(idElegido.getText());
        if(id_aBorrar.isEmpty()){
            idElegido.setError("Pon un ID a borrar");
        }

        //--------------------------------------------------------
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("¿Seguro que quieres borrar? (SI/NO)");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                boolean guardadoOK = comidaBD.borrarComida(id_aBorrar);
                if (guardadoOK) {
                    mostrarToast("Se ha borrado correctamente");
                } else {
                    mostrarToast("no se ha podido borrar");
                }
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