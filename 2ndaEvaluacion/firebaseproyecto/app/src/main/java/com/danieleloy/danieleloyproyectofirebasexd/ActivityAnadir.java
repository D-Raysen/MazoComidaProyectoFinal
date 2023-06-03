package com.danieleloy.danieleloyproyectofirebasexd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.danieleloy.danieleloyproyectofirebasexd.Clases.Comida;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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

    public void InsertarComida(View view){

        String CodComida = String.valueOf(nuevoIdComida.getText());
        String Nombre = String.valueOf(nuevoNombre.getText());
        String TextoPrecio = String.valueOf(nuevoPrecio.getText());

        //Ahora pongo los errores si no pones nada
        if (CodComida.isEmpty()){
            nuevoIdComida.setError("Escribe algo");
        }
        if (Nombre.isEmpty()){
            nuevoNombre.setError("Pon algo");
        }
        if (TextoPrecio.isEmpty()){
            nuevoPrecio.setError("Tienes que poner un precio");
        }

        int idComida = Integer.valueOf(CodComida);
        int precio = Integer.valueOf(TextoPrecio);

        //--------------------------------------------------------
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("son correctos los datos? (SI/NO)");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Comida c = new Comida(idComida,Nombre,precio);
                //una vez creada la clase creamos el vinculo con firebase
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                myRef.child("menus").child(c.getNombreComida()).setValue(c);
                Toast.makeText(ActivityAnadir.this, "Comida añadida correctamente ", Toast.LENGTH_SHORT).show();
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