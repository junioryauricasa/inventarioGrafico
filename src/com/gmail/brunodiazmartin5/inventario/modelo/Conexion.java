package com.gmail.brunodiazmartin5.inventario.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class Conexion {
    private static Conexion con;
    private static Connection connection;
    
    private Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario", "root", "dayamon15");
        }catch(ClassNotFoundException e){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Driver no encontrado", e);
        }catch(SQLException e2){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Problema SQL", e2);
        }
    }
    
    public static Conexion getInstance(){
        if(con == null) con = new Conexion();
        
        return con;
    }
    
    public Connection getConnection(){
        return connection;
    }
}
