package com.example.myapplication.controladores;

import com.example.myapplication.clases.Comida;
import com.example.myapplication.tareas.TareaGuardarComida;
import com.example.myapplication.tareas.TareaGuardarFruta;
import com.example.myapplication.tareas.TareaObtenerComida;
import com.example.myapplication.tareas.TareaObtenerFruta;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ComidaControler {

    public static boolean guardarComida(Comida c)
    {
        FutureTask t = new FutureTask(new TareaGuardarComida(c)); //y a su vez, este llama a la tarea
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            return insercionOK;
        }
    }

    public static ArrayList<Comida> obtenerTodasLasCarnes() {
        FutureTask t = new FutureTask(new TareaObtenerComida());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        ArrayList<Comida> comidas = new ArrayList<Comida>();
        try {
            comidas = (ArrayList<Comida>) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            return comidas;
        }
    }

    //FRUTAS
    public static boolean guardarFruta(Comida c)
    {
        FutureTask t = new FutureTask(new TareaGuardarFruta(c)); //y a su vez, este llama a la tarea
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            return insercionOK;
        }
    }

    public static ArrayList<Comida> obtenerTodasLasFrutas() {
        FutureTask t = new FutureTask(new TareaObtenerFruta());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        ArrayList<Comida> comidas = new ArrayList<Comida>();
        try {
            comidas = (ArrayList<Comida>) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            return comidas;
        }
    }
}
