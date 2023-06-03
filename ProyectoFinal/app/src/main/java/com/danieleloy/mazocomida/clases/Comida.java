package com.danieleloy.mazocomida.clases;

import java.io.Serializable;
import java.util.Objects;

public class Comida implements Serializable {
    private int idComida;
    private String NombreComida;


    public Comida(int idComida, String nombreComida) {
        this.idComida = idComida;
        NombreComida = nombreComida;

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
