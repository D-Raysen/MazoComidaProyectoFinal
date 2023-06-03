package com.danieleloy.danieleloyproyectofirebasexd;

import static com.danieleloy.danieleloyproyectofirebasexd.Clases.ComidaViewHolder.EXTRA_PRODUCTO_ITEM;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.danieleloy.danieleloyproyectofirebasexd.Clases.Comida;
import com.danieleloy.danieleloyproyectofirebasexd.Clases.ComidaViewHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DetallesComidaActivity extends AppCompatActivity {

    private EditText DetalleNombre = null;
    private EditText DetalleId = null;
    private EditText DetallePrecio = null;

    String nombreAntiguo = "";

    int posicion = -1;
    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_comida);

        DetalleId = (EditText) findViewById(R.id.edt_detalles_id);
        DetalleNombre = (EditText) findViewById(R.id.edt_detalles_nombre);
        DetallePrecio = (EditText) findViewById(R.id.edt_detalles_precio);


        Intent intent = getIntent();
        if(intent != null){
            Comida c = (Comida) intent.getSerializableExtra(EXTRA_PRODUCTO_ITEM);
            DetalleId.setText(String.valueOf(c.getIdComida()));
            DetalleNombre.setText(c.getNombreComida());
            DetallePrecio.setText(String.valueOf(Integer.valueOf((int) c.getPrecio())));
            nombreAntiguo = c.getNombreComida();

        }

    }
    //-----------------------------------------------------------------------
    public void detalles_borrar_comida(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        /*int idComidaBorrar = Integer.valueOf(String.valueOf(DetalleId.getText()));
        String nombre = String.valueOf(DetalleNombre.getText());
        int precio = Integer.valueOf(String.valueOf(DetallePrecio.getText()));
        Comida c = new Comida(idComidaBorrar, nombre, precio);
        //--------------------------------------------
        if(nombreAntiguo.equalsIgnoreCase(nombre))
        {
            myRef.child("menus").child(c.getNombreComida()).removeValue();
            Toast.makeText(this,"Comida borrada correctamente",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"no se pudo borrar la comida",Toast.LENGTH_LONG).show();
        }*/

        //-------------------------------------------
        Intent intent = getIntent();
        Comida c = (Comida) intent.getSerializableExtra(EXTRA_PRODUCTO_ITEM);
        myRef.child("menus").child(c.getNombreComida()).removeValue();

        Toast.makeText(this,"Comida borrada correctamente",Toast.LENGTH_LONG).show();




    }
    public void detalles_editar_Comida(View view) {
        String nombreMod = String.valueOf(DetalleNombre.getText());
        int idMod = Integer.valueOf(String.valueOf(DetalleId.getText()));
        //al parecer si no modifico el precio no se guarda. y da fallo.
        int PrecioMod = Integer.valueOf(String.valueOf(DetallePrecio.getText()));


        /*Comida c = new Comida(Integer.valueOf(idMod), nombreMod, Integer.valueOf(PrecioMod));
        //--------------------------------------------
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        */
        //.child("menus").child(idMod)

       /* myRef.child("menus").child(nombreAntiguo).removeValue();
        myRef.child("menus").child(c.getNombreComida()).setValue(c);

            */


        //--------------------------------------------------

        AlertDialog.Builder alerta1 = new AlertDialog.Builder(DetallesComidaActivity.this);
        alerta1.setTitle("¿Desea actualizar el manual?");
        alerta1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Comida c = new Comida(idMod, nombreMod, PrecioMod);
                // Realizo un mapeo del objeto para que este lo pueda identificar en la base de datos y asi poder actualizarlo
                Map<String, Object> nuevoComida = new HashMap<String,Object>();
                nuevoComida.put(nombreMod,c);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                //Le digo que actualice el child pasandole un nuevo objeto
                //myRef.child("menus").updateChildren(nuevoComida);
                myRef.child("menus").child(nombreAntiguo).removeValue();

                myRef.child("menus").child(c.getNombreComida()).child("idComida").setValue(c.getIdComida());
                myRef.child("menus").child(c.getNombreComida()).child("NombreComida").setValue(c.getNombreComida());
                myRef.child("menus").child(c.getNombreComida()).child("Precio").setValue(c.getPrecio());



                Toast.makeText(DetallesComidaActivity.this,"Actualizacion correcta",Toast.LENGTH_LONG).show();

            }
        });
        alerta1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dial og, int which) {
            }
        });
        alerta1.show();


    }

    //Esto es para que al darle 1 al boton de volver,vuelva
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}