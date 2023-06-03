package com.example.hiloreloj.Clases;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class HiloReloj  implements Runnable
{
    private Thread hilo = null;
    private String hora = null;
    private TimeZone zona = null;
    private SimpleDateFormat sdf = null;
    private TextView TxtView;
    private Activity a;


    public HiloReloj(TextView txtreloj, ThreadGroup tg, String nombre, int prioridad, String ciudad) {
        //super(texto);
        hilo = new Thread(tg,this, nombre);
        hilo.setPriority(prioridad);

        this.TxtView = txtreloj;
        this.a = a;
        //-----------------------------------------
        // hilo.start();
        zona = TimeZone.getTimeZone(ciudad);
        //-----------------------------------------
        sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(zona);
        hora = "";
    }

    public Thread getHilo() {
        return hilo;
    }

    public void setHilo(Thread hilo) {
        this.hilo = hilo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public TimeZone getZona() {
        return zona;
    }

    public void setZona(TimeZone zona) {
        this.zona = zona;
    }

    @Override
    public void run() {
        while(true)
        {
            actualizarHora();
            mostrarHora();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void mostrarHora() {
        System.out.println(hora);
        Log.i(tag: "hora", hora);

    }

    private void actualizarHora() {
        Calendar calendar = Calendar.getInstance(zona);
        hora = sdf.format(calendar.getTime());
    }
}
