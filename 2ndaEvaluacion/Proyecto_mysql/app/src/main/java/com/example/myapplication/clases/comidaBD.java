package com.example.myapplication.clases;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


    public class comidaBD {

        public static ArrayList<Comida> obtenerComida() {
            Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
            if(conexion == null)
            {
                return null;
            }
            ArrayList<Comida> comidas = new ArrayList<Comida>();
            try {
                Statement sentencia = conexion.createStatement();
                String ordenSQL = "SELECT * FROM comidabd.carnes;";
                ResultSet resultado = sentencia.executeQuery(ordenSQL);
                while(resultado.next())
                {
                    int idComida = resultado.getInt("idcarnes");
                    String nombre = resultado.getString("nombre");

                    double precioComida = resultado.getDouble("precio");
                    //-----------------------------------------------------------
                    Comida LaComida = new Comida(idComida,nombre,precioComida);
                    //------------------------------------------------------------
                    comidas.add(LaComida);
                }
                resultado.close();
                sentencia.close();
                conexion.close();
                return comidas;
            } catch (SQLException e) {
                Log.i("sql", "error sql");
                return null;
            }
        }
        //CONTINUAR POR AQUI!!!!!!!!
        //-----------------------------------------------------------------------------------------------------
        public static boolean borrarComida(String cod_Comida) {
            Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
            if(conexion == null)
            {
                return false;
            }
            try {
                String ordensql = "DELETE FROM carnes WHERE (idcarnes = ?);";
                PreparedStatement sentencia = conexion.prepareStatement(ordensql);
                sentencia.setString(1, cod_Comida);
                int filasafectadas = sentencia.executeUpdate();
                sentencia.close();
                conexion.close();
                if(filasafectadas > 0)
                {
                    return true;
                }
                else {
                    return false;
                }

            } catch (SQLException e) {
                return false;
            }
        }
        //------------------------------------------------------------------------------------------------
        public static boolean guardarComida(Comida c) {
            Connection conexion = ConfiguracionDB.conectarConBaseDeDatos(); //primero conectas con la base
            if(conexion == null)
            {
                return false;
            }
            try { // ostras,mi interfaces es esto
                String ordensql = "INSERT INTO carnes (idcarnes, nombre, precio) VALUES (?,?,?);";
                PreparedStatement sentencia = conexion.prepareStatement(ordensql);
                sentencia.setInt(1, c.getIdComida());
                sentencia.setString(2, c.getNombreComida());
                sentencia.setDouble(3, c.getPrecio());

                int filasafectadas = sentencia.executeUpdate();
                sentencia.close();
                conexion.close();
                if(filasafectadas > 0)
                {
                    return true;
                }
                else {
                    return false;
                }
            } catch (SQLException e) {
                return false;
            }
        }
        //------------------------------------------------------------------------------------------------
        public  boolean actualizarComida(Comida c, int codComidaAntiguo) {
            Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
            if(conexion == null)
            {
                return false;
            }
            try {
                String ordensql = "UPDATE carnes SET idcarnes = ?, nombre = ?, precio = ? WHERE idcarnes = ?";
                PreparedStatement pst = conexion.prepareStatement(ordensql);
                pst.setInt(1, c.getIdComida());
                pst.setString(2, c.getNombreComida());
                pst.setDouble(3, c.getPrecio());

                int filasAfectadas = pst.executeUpdate();
                pst.close();
                conexion.close();
                if(filasAfectadas > 0)
                {
                    return true;
                }
                else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        //METODOS FRUTAS
        public static ArrayList<Comida> obtenerFrutas() {
            Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
            if(conexion == null)
            {
                return null;
            }
            ArrayList<Comida> comidas = new ArrayList<Comida>();
            try {
                Statement sentencia = conexion.createStatement();
                String ordenSQL = "SELECT * FROM comidabd.frutas;";
                ResultSet resultado = sentencia.executeQuery(ordenSQL);
                while(resultado.next())
                {
                    int idComida = resultado.getInt("idFrutas");
                    String nombre = resultado.getString("nombre");

                    double precioComida = resultado.getDouble("precio");
                    //-----------------------------------------------------------
                    Comida LaComida = new Comida(idComida,nombre,precioComida);
                    //------------------------------------------------------------
                    comidas.add(LaComida);
                }
                resultado.close();
                sentencia.close();
                conexion.close();
                return comidas;
            } catch (SQLException e) {
                Log.i("sql", "error sql");
                return null;
            }
        }
        //--------------------------------------------
        public static boolean borrarFruta(String cod_Comida) {
            Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
            if(conexion == null)
            {
                return false;
            }
            try {
                String ordensql = "DELETE FROM frutas WHERE (idFrutas = ?);";
                PreparedStatement sentencia = conexion.prepareStatement(ordensql);
                sentencia.setString(1, cod_Comida);
                int filasafectadas = sentencia.executeUpdate();
                sentencia.close();
                conexion.close();
                if(filasafectadas > 0)
                {
                    return true;
                }
                else {
                    return false;
                }

            } catch (SQLException e) {
                return false;
            }
        }public static boolean guardarFrutas(Comida c) {
            Connection conexion = ConfiguracionDB.conectarConBaseDeDatos(); //primero conectas con la base
            if(conexion == null)
            {
                return false;
            }
            try {
                String ordensql = "INSERT INTO frutas (idFrutas, nombre, precio) VALUES (?,?,?);";
                PreparedStatement sentencia = conexion.prepareStatement(ordensql);
                sentencia.setInt(1, c.getIdComida());
                sentencia.setString(2, c.getNombreComida());
                sentencia.setDouble(3, c.getPrecio());

                int filasafectadas = sentencia.executeUpdate();
                sentencia.close();
                conexion.close();
                if(filasafectadas > 0)
                {
                    return true;
                }
                else {
                    return false;
                }
            } catch (SQLException e) {
                return false;
            }
        }
        //------------------------------------------------------------

    }

