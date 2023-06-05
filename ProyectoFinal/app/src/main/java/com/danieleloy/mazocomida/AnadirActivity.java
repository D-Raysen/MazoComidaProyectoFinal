package com.danieleloy.mazocomida;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.danieleloy.mazocomida.clases.Comida;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnadirActivity extends AppCompatActivity {

    private EditText nuevoIdComida = null;
    private EditText nuevoNombre = null;
    private EditText nuevoPrecio = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);

        nuevoIdComida = (EditText) findViewById(R.id.editTextIdComida);
        nuevoNombre = (EditText) findViewById(R.id.editTextNombre);
        


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
}