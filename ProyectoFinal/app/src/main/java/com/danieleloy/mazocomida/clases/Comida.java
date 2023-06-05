package com.danieleloy.mazocomida.clases;

import java.io.Serializable;
import java.util.Objects;

public class Comida implements Serializable {
    private int idComida;
    private String NombreComida;

    //private String urlImagen; // Nuevo atributo para la URL de la imagen

    public Comida(int idComida, String nombreComida) {
        this.idComida = idComida;
        NombreComida = nombreComida;

        //this.urlImagen = urlImagen;
    }

    public Comida() {
    }

    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    public String getNombreComida() {
        return NombreComida;
    }

    public void setNombreComida(String nombreComida) {
        NombreComida = nombreComida;
    }


    //public String getUrlImagen(){return  urlImagen; }

    //public  void setUrlImagen(String RutaImagen){urlImagen = RutaImagen; }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comida)) return false;
        Comida comida = (Comida) o;
        return idComida == comida.idComida && Objects.equals(NombreComida, comida.NombreComida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComida, NombreComida);
    }
}
