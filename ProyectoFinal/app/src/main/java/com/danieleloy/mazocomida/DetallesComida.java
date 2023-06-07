package com.danieleloy.mazocomida;

import static com.danieleloy.mazocomida.clases.ComidaViewHolder.EXTRA_PRODUCTO_ITEM;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.danieleloy.mazocomida.clases.Comida;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DetallesComida extends AppCompatActivity {

    private EditText DetalleNombre = null;
    private EditText DetalleId = null;
    private EditText DetallePrecio = null;


    ImageView fotoComida = null;
    private static final int NUEVA_IMAGEN = 1;

    Uri imagen_seleccionada = null;
    String nombreAntiguo = "";
    int IdAntiguo = 0;

    int posicion = -1;
    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_comida);

        DetalleId = (EditText) findViewById(R.id.edt_detalles_id);
        DetalleNombre = (EditText) findViewById(R.id.edt_detalles_nombre);



        Intent intent = getIntent();
        if(intent != null){
            Comida c = (Comida) intent.getSerializableExtra(EXTRA_PRODUCTO_ITEM);
            DetalleId.setText(String.valueOf(c.getIdComida()));
            DetalleNombre.setText(c.getNombreComida());

            IdAntiguo = c.getIdComida();
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
        myRef.child("bufe").child(String.valueOf(IdAntiguo)).removeValue();

        Toast.makeText(this,"Comida borrada correctamente",Toast.LENGTH_LONG).show();




    }
    public void detalles_editar_Comida(View view) {
        String nombreMod = String.valueOf(DetalleNombre.getText());
        int idMod = Integer.valueOf(String.valueOf(DetalleId.getText()));



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

        AlertDialog.Builder alerta1 = new AlertDialog.Builder(DetallesComida.this);
        alerta1.setTitle("¿Desea actualizar el manual?");
        alerta1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Comida c = new Comida(idMod, nombreMod);
                // Realizo un mapeo del objeto para que este lo pueda identificar en la base de datos y asi poder actualizarlo
                Map<String, Object> nuevoComida = new HashMap<String,Object>();
                nuevoComida.put(nombreMod,c);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                //Le digo que actualice el child pasandole un nuevo objeto
                //myRef.child("menus").updateChildren(nuevoComida);
                myRef.child("bufe").child(String.valueOf(IdAntiguo)).removeValue();

                myRef.child("bufe").child(c.getNombreComida()).child("idComida").setValue(c.getIdComida());
                myRef.child("bufe").child(c.getNombreComida()).child("NombreComida").setValue(c.getNombreComida());




                Toast.makeText(DetallesComida.this,"Actualizacion correcta",Toast.LENGTH_LONG).show();

            }
        });
        alerta1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alerta1.show();


    }

    //--------CODIGO PARA CAMBIAR LA IMAGEN----------------
    public void cambiar_imagen(View view) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Selecciona una imagen");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, NUEVA_IMAGEN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NUEVA_IMAGEN && resultCode == Activity.RESULT_OK) {
            imagen_seleccionada = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagen_seleccionada);
                fotoComida.setImageBitmap(bitmap);

                //---------------------------------------------

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Esto es para que al darle 1 al boton de volver,vuelva

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}