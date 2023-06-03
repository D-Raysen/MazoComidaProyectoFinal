package com.danieleloy.mazocomida;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private TextView FechaMenu;
    private RecyclerView rv = null;
    private AdapterDatos Adapter = null;
    private ArrayList<Comida> COMIDA = null;
    private Object view;

    private DatabaseReference myRefComida = null;
    private DatabaseReference myRefCOmida1 = null;

    private EditText edtBuscar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("SaludoDesdeAndroid").setValue("AAAAaaugh");*/

        FechaMenu = (TextView) findViewById(R.id.txtFecha);

        // Obtener la hora actual
        Calendar calendar = Calendar.getInstance();
        int diaActual = calendar.get(Calendar.DAY_OF_MONTH);

        FechaMenu.setText(String.valueOf(diaActual));

        rv = (RecyclerView) findViewById(R.id.recyclerView);
        edtBuscar = (EditText) findViewById(R.id.editTextTextBuscar);
        COMIDA = new ArrayList<Comida>();
        Adapter = new AdapterDatos(this, COMIDA);
        Adapter.setClickable(false); // Desactivar la capacidad de clickear

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

    public void BotonModoAdmin(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Contraseña de administrador");
        final EditText editTextContraseña = new EditText(this);
        editTextContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(editTextContraseña);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String contraseña = editTextContraseña.getText().toString();
                if (contraseña.equals("ModoAdmin123")) {
                    Intent intent = new Intent(MainActivity.this, ModoAdminActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
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

    public void Reservar (View view) {
    Intent intent = new Intent(MainActivity.this, ReservarActivity.class);
    startActivity(intent);
}
}
