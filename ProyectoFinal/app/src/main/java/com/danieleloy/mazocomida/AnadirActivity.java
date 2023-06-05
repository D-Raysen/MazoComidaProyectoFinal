package com.danieleloy.mazocomida;

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
import com.danieleloy.mazocomida.clases.ImagenesFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class AnadirActivity extends AppCompatActivity {

    ImageView fotoComida = null;
    private static final int NUEVA_IMAGEN = 1;

    Uri imagen_seleccionada = null;
    private EditText nuevoIdComida = null;
    private EditText nuevoNombre = null;
    private EditText nuevoPrecio = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);

        nuevoIdComida = (EditText) findViewById(R.id.editTextIdComida);
        nuevoNombre = (EditText) findViewById(R.id.editTextNombre);

        fotoComida = (ImageView) findViewById(R.id.ImgComida);


    }

    public void InsertarComida(View view){

        String CodComida = String.valueOf(nuevoIdComida.getText());
        String Nombre = String.valueOf(nuevoNombre.getText());

        //Ahora pongo los errores si no pones nada
        if (CodComida.isEmpty()){
            nuevoIdComida.setError("Escribe algo");
        }
        if (Nombre.isEmpty()){
            nuevoNombre.setError("Pon algo");
        }

        int idComida = Integer.valueOf(CodComida);


        //--------------------------------------------------------
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("son correctos los datos? (SI/NO)");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Comida c = new Comida(idComida,Nombre);
                //una vez creada la clase creamos el vinculo con firebase
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                myRef.child("bufe").child(String.valueOf(c.getIdComida())).setValue(c);
                if(imagen_seleccionada != null) {
                    String carpeta = "Comida";
                    ImagenesFirebase.subirFoto(carpeta,c.getNombreComida(), fotoComida);
                }
                Toast.makeText(AnadirActivity.this, "Comida añadida correctamente ", Toast.LENGTH_SHORT).show();
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
}