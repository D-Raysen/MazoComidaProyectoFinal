package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.clases.AdapterDatos;
import com.example.myapplication.clases.ConfiguracionDB;
import com.example.myapplication.clases.Comida;
import com.example.myapplication.clases.comidaBD;
import com.example.myapplication.controladores.ComidaControler;

import java.sql.Connection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv = null;
    private RecyclerView rvFRUTAS = null;
    private ArrayList<Comida> COMIDA = null;
    private comidaBD BaseDeDatos = null;
    private AdapterDatos Adapter;
    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.recyclerViewComida);
        rvFRUTAS = (RecyclerView) findViewById(R.id.RecycleViewfruta);
        COMIDA = new ArrayList<Comida>(ComidaControler.obtenerTodasLasCarnes());
        Adapter = new AdapterDatos(this,COMIDA);

        rv.setAdapter(Adapter);
        rvFRUTAS.setAdapter(Adapter);
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

        




    }

    public void BotonInsertar(View view) {
        Intent intent = new Intent(this,ActivityAnadir.class);
        startActivity(intent);
    }

    public void BotonEliminar(View view) {
        Intent intent = new Intent(this,BorrarActivity.class);
        startActivity(intent);
    }

    public void BotonActualizar(View view){
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
            //voy a cotillear otro proyecto de ejemplo


        Connection c =  ConfiguracionDB.conectarConBaseDeDatos();
        if(c == null)
        {
            Log.i("sql", "error al conectar");
        }
        else{
            Log.i("sql", "conectado correctamente");
        }
    }
}