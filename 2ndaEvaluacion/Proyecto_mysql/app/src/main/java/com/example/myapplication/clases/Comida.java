package com.example.myapplication.clases;

import java.io.Serializable;
import java.util.Objects;

public class Comida implements Serializable {
    private int idComida;
    private String NombreComida;
    private Double Precio;

    public Comida(int idComida, String nombreComida, double precio) {
        this.idComida = idComida;
        this.NombreComida = nombreComida;
        this.Precio = precio;
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

    public void setPrecio(double precio) {
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
