package com.danieleloy.danieleloyproyectofirebasexd.Clases;

import java.io.Serializable;
import java.util.Objects;

public class Comida implements Serializable {
    private int idComida;
    private String NombreComida;
    private int Precio;

    public Comida(int idComida, String nombreComida, int precio) {
        this.idComida = idComida;
        NombreComida = nombreComida;
        Precio = precio;
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

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comida)) return false;
        Comida comida = (Comida) o;
        return idComida == comida.idComida && Precio == comida.Precio && Objects.equals(NombreComida, comida.NombreComida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComida, NombreComida, Precio);
    }
}
