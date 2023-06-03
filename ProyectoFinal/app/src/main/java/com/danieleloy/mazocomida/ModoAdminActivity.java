package com.danieleloy.mazocomida;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danieleloy.mazocomida.clases.AdapterDatos;
import com.danieleloy.mazocomida.clases.Comida;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ModoAdminActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    private RecyclerView rv = null;
    private AdapterDatos Adapter = null;
    private ArrayList<Comida> COMIDA = null;
    private Object view;

    private DatabaseReference myRefComida = null;
    private DatabaseReference myRefCOmida1 = null;

    private EditText edtBuscar = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_admin);

        rv = (RecyclerView) findViewById(R.id.RecyclerView);
        edtBuscar = (EditText) findViewById(R.id.editTextTextBuscar3);
        COMIDA = new ArrayList<Comida>();
        Adapter = new AdapterDatos(this, COMIDA);
        rv.setAdapter(Adapter);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            rv.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            // In portrait
            rv.setLayoutManager(new LinearLayoutManager(this));
        }

        //FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);


        myRefComida = FirebaseDatabase.getInstance().getReference();
        myRefComida.child("bufe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Adapter.getListaComida().clear();
                if(snapshot.exists()){

                    for(DataSnapshot dataSnapshot : snapshot.getChildren())
                    {
                        Comida a = (Comida) dataSnapshot.getValue(Comida.class);

                       /*String NombreComida = dataSnapshot.child("Nombre").getValue().toString();
                       int idComida = (int) dataSnapshot.child("Id").getValue();*/


                        COMIDA.add(a);
                        Adapter.setListaComida(COMIDA);
                        Adapter.notifyDataSetChanged();
                    }


                }
                Log.d("MainActivity", "Tamaño de COMIDA: " + COMIDA.size());
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i( "firebase1", String.valueOf(error.toException()));
            }
        });
    }

    public void BotonInsertar(View view) {
        Intent intent = new Intent(this,AnadirActivity.class);
        startActivity(intent);
    }

    public void buscarComida(View view) {

        String texto = String.valueOf(edtBuscar.getText());
        myRefCOmida1 = FirebaseDatabase.getInstance().getReference("bufe");
        myRefCOmida1.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> keys1 = new ArrayList<String>();
                ArrayList<Comida> Comida1 = new ArrayList<Comida>();
                for (DataSnapshot keynode : snapshot.getChildren()) {
                    keys1.add(keynode.getKey());
                    Comida c = keynode.getValue(Comida.class);
                    if(c.getNombreComida().contains(texto)) {
                        Comida1.add(keynode.getValue(Comida.class));
                    }
                }
                Adapter.setListaComida(Comida1);
                Adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "firebase1", String.valueOf(error.toException()));
            }
        });

    }

    //Boton para regresar al modo cliente
    public void BotonModoCliente(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}