package com.example.myapplication.tareas;

import com.example.myapplication.clases.Comida;
import com.example.myapplication.clases.comidaBD;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerFruta implements Callable<ArrayList<Comida>> {



    @Override
    public ArrayList<Comida> call() throws Exception {
        ArrayList<Comida> comidas = comidaBD.obtenerFrutas();
        return comidas;
    }
}
