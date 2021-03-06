package com.gmail.brunodiazmartin5.inventario.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gmail.brunodiazmartin5.interfaces.EnqueueableData;

/**
 *
 * @author Bruno
 */
public class Producto implements EnqueueableData {

    private int id;
    private String descripcion;
    private double precio;

    public Producto() {
    }

    public Producto(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto(int id, String descripcion, double precio) {
        this(descripcion, precio);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return id + " " + descripcion;
    }

    @Override
    public void execute(int sqlType) {
        Connection conn = Conexion.getInstance().getConnection();

        switch (sqlType) {
            case 1:
                try {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO productos VALUES(?, ?, ?)");
                    ps.setInt(1, id);
                    ps.setString(2, descripcion);
                    ps.setDouble(3, precio);

                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    PreparedStatement ps = conn.prepareStatement("UPDATE productos SET descripcion_pro=?, precio_pro=? WHERE id_por=?");
                    ps.setString(1, descripcion);
                    ps.setDouble(2, precio);
                    ps.setInt(3, id);

                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 3:
                try {
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM WHERE id_por=?");
                    ps.setInt(1, id);

                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                break;
        }

    }

}
