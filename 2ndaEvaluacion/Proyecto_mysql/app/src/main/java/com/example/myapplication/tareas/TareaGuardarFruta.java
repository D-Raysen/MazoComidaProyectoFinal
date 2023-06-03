package com.example.myapplication.tareas;

import com.example.myapplication.clases.Comida;
import com.example.myapplication.clases.comidaBD;

import java.util.concurrent.Callable;

public class TareaGuardarFruta implements Callable<Boolean> {
    private Comida c;
    private comidaBD cBD;

    public TareaGuardarFruta(Comida c) {
        this.c = c;
    }

    @Override
    public Boolean call() throws Exception {
        boolean guardadoOK = cBD.guardarFrutas(c);
        return guardadoOK;
    }
}
