package com.example.myapplication.tareas;

import com.example.myapplication.clases.Comida;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import com.example.myapplication.clases.Comida;
import com.example.myapplication.clases.comidaBD;

public class TareaObtenerComida implements Callable<ArrayList<Comida>> {



    @Override
    public ArrayList<Comida> call() throws Exception {
        ArrayList<Comida> comidas = comidaBD.obtenerComida();
        return comidas;
    }
}
