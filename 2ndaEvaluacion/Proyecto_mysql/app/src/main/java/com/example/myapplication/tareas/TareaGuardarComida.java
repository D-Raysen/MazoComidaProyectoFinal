package com.example.myapplication.tareas;

import com.example.myapplication.clases.Comida;
import com.example.myapplication.clases.comidaBD;

import java.util.concurrent.Callable;

public class TareaGuardarComida implements Callable<Boolean> {
    private Comida c;
    private  comidaBD cBD;

    public TareaGuardarComida(Comida c) {
        this.c = c;
    }

    @Override
    public Boolean call() throws Exception {
        boolean guardadoOK = cBD.guardarComida(c);
        return guardadoOK;
    }
}


