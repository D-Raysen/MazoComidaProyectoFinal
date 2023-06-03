package com.example.formularioproductos.clases;

import java.io.Serializable;
import java.util.Objects;

public class Producto implements Serializable {
    private  String id_producto;
    private  String nombre_cliente;
    private  String direccion_envio;
    private  String telefono;
    private  String metodo_envio;

    public Producto(String id_producto, String nombre_cliente, String direccion_envio, String telefono, String metodo_envio) {
        this.id_producto = id_producto;
        this.nombre_cliente = nombre_cliente;
        this.direccion_envio = direccion_envio;
        this.telefono = telefono;
        this.metodo_envio = metodo_envio;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getDireccion_envio() {
        return direccion_envio;
    }

    public void setDireccion_envio(String direccion_envio) {
        this.direccion_envio = direccion_envio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMetodo_envio() {
        return metodo_envio;
    }

    public void setMetodo_envio(String metodo_envio) {
        this.metodo_envio = metodo_envio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id_producto.equals(producto.id_producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_producto);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id_producto='" + id_producto + '\'' +
                ", nombre_cliente='" + nombre_cliente + '\'' +
                ", direccion_envio='" + direccion_envio + '\'' +
                ", telefono='" + telefono + '\'' +
                ", metodo_envio='" + metodo_envio + '\'' +
                '}';
    }
}
