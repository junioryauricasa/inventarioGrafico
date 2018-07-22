package com.gmail.brunodiazmartin5.inventario.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gmail.brunodiazmartin5.interfaces.EnqueueableData;

/**
 *
 * @author Bruno
 */
public class Factura implements EnqueueableData {

    private int id;
    private Cliente cliente;
    private double subtotal;
    private double descuento;
    private double impuesto;
    private double total;
    private ArrayList<DetalleFactura> detalles;

    public Factura() {
        detalles = new ArrayList<>();
    }

    public Factura(Cliente cliente, double subtotal, double descuento, double impuesto, double total) {
        this.cliente = cliente;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.impuesto = impuesto;
        this.total = total;
        detalles = new ArrayList<>();
    }

    public Factura(int id, Cliente cliente, double subtotal, double descuento, double impuesto, double total) {
        this(cliente, subtotal, descuento, impuesto, total);
        this.id = id;
        detalles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente c) {
        this.cliente = c;
    }

    public double getSubtotal() {
        if (!detalles.isEmpty() && subtotal == 0) {
            detalles.forEach(p -> subtotal += p.getCantidad() * p.getPrecio());
        }

        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        if (!detalles.isEmpty() && total == 0) {
            detalles.forEach(p -> total += p.getCantidad() * p.getPrecio());
            total -= total * (descuento / 100);
            total += total * (impuesto / 100);

        }

        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    public void addDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
    }

    public static double generarTotal(double cantidad, double precio, double descuento, double impuesto) {
        double subtotal = precio * cantidad;
        double total = subtotal - (subtotal * (descuento / 100));
        total += subtotal * (impuesto / 100);

        return total;
    }

    @Override
    public void execute(int sqlType) {
        Connection conn = Conexion.getInstance().getConnection();

        switch (sqlType) {
            case 1:
                try {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO facturas VALUES(?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, id);
                    ps.setString(2, cliente.getDni());
                    ps.setDouble(3, getSubtotal());
                    ps.setDouble(4, descuento);
                    ps.setDouble(5, impuesto);
                    ps.setDouble(6, getTotal());
                    
                    ps.executeUpdate();
                    
                    detalles.forEach(df -> df.save(id));
                } catch (SQLException ex) {
                    Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            case 2:
                try {
                    PreparedStatement ps = conn.prepareStatement("UPDATE facturas SET dni_cli_fac=?, subtotal_fac=?, descuento_fac=?, impuesto_fac=?, total_fac=? WHERE id_fac=?");
                    ps.setString(1, cliente.getDni());
                    ps.setDouble(2, getSubtotal());
                    ps.setDouble(3, descuento);
                    ps.setDouble(4, impuesto);
                    ps.setDouble(5, getTotal());
                    ps.setInt(6, id);
                    
                    ps.executeUpdate();
                    
                    detalles.forEach(df -> df.save(id));
                } catch (SQLException ex) {
                    Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            case 3:
                try {
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM facturas WHERE id_fac=?");
                    ps.setInt(1, id);
                    
                    ps.executeUpdate();
                    
                    detalles.forEach(df -> df.save(id));
                } catch (SQLException ex) {
                    Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            default:
                break;
        }
    }
}
