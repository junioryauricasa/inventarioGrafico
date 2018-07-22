package com.gmail.brunodiazmartin5.inventario.modelo;

import com.gmail.brunodiazmartin5.interfaces.EnqueueableData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class Cliente implements EnqueueableData {

    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String movil;
    private String telefono;

    public Cliente(String nombre, String apellidos, String dni, String direccion, String movil, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.movil = movil;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void execute(int sqlType) {
        Connection conn = Conexion.getInstance().getConnection();
        switch (sqlType) {
            case 1:
                try {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO clientes VALUES(?, ?, ?, ?, ?, ?)");
                    ps.setString(1, dni);
                    ps.setString(2, nombre);
                    ps.setString(3, apellidos);
                    ps.setString(4, movil);
                    ps.setString(5, telefono);
                    ps.setString(6, direccion);
                    
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            case 2:
                try {
                    PreparedStatement ps = conn.prepareStatement("UPDATE clientes SET nombre_cli=?, apellidos_cli=?, movil_cli=?, telefono_cli=?, direccion_cli=? WHERE dni_cli=?");
                    ps.setString(1, nombre);
                    ps.setString(2, apellidos);
                    ps.setString(3, movil);
                    ps.setString(4, telefono);
                    ps.setString(5, direccion);
                    ps.setString(6, dni);
                    
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            case 3:
                try {
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM clientes WHERE dni_cli=?");
                    ps.setString(1, dni);;
                    
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            default:
                break;
        }

    }

}
