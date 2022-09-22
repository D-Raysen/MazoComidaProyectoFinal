package es.joseljg.contador2223;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
int Contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    public void mostrarMensaje(View view) {
        Toast.makeText(this, "Esto es un contador", Toast.LENGTH_SHORT).show();
    }

    public void contarNumeros(View view) {
        Contador ++;
        TextView txt_contador = findViewById(R.id.txt_contador);
        txt_contador.setText(String.valueOf(Contador));

    }


    public void resetearContador(View view) {
        String reseteo = "0";
        TextView txt_contador = findViewById(R.id.txt_contador);
        txt_contador.setText(reseteo);
    }

}