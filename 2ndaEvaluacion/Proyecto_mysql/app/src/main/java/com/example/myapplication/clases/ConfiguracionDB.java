package com.example.myapplication.clases;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracionDB {

    public static final String HOSTDB = "10.0.2.2";
    public static final String NOMBREDB = "comidabd";
    public static final String USUARIODB = "root";
    public static final String CLAVEDB = "dam1";
    private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String OPCIONES2 = "?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    // las opciones de hora tambien las puedes poner en mysql
    // SET GLOBAL time_zone = '+1:00';
    public static final String PUERTOMYSQL = "3306";
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB +":"+PUERTOMYSQL + "/" + NOMBREDB + OPCIONES;

    //----------------------------------------------------------....
    public static Connection conectarConBaseDeDatos() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection conexion = DriverManager.getConnection(URLMYSQL, USUARIODB, CLAVEDB);
            System.out.println("Se ha realizado la conexion CORRECTAMENTE");
            return conexion;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no se pudo establecer la conexion con la base de datos");
            return null;
        }
    }
    //-----------------------------------------------------------
}
