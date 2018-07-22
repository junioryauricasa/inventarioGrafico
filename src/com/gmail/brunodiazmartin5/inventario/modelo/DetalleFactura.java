package com.gmail.brunodiazmartin5.inventario.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class DetalleFactura{
    private int id_pro;
    private String descripcion;
    private int cantidad;
    private double precio;
    private double total;

    public DetalleFactura() {
    }
    
    public DetalleFactura(int id_pro, String descripcion, int cantidad, double precioUnitario) {
        this.id_pro = id_pro;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precioUnitario;
        this.total = cantidad * precio;
    }

    public Producto getProducto(){
        return new Producto(id_pro, descripcion, precio);
    }
    
    public int getId_pro() {
        return id_pro;
    }

    public void setId_pro(int id_pro) {
        this.id_pro = id_pro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void save(int idFact) {
        Connection conn = Conexion.getInstance().getConnection();
        
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO facturas_productos VALUES(?, ?, ?, ?)");
            ps.setInt(1, idFact);
            ps.setInt(2, id_pro);
            ps.setInt(3, cantidad);
            ps.setDouble(4, total);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
