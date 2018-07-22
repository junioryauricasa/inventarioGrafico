package com.gmail.brunodiazmartin5.inventario.modelo;

import com.gmail.brunodiazmartin5.interfaces.EnqueueableData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class ListaFactura implements EnqueueableData {

    private ArrayList<Factura> facturas;

    public ListaFactura() {
        facturas = new ArrayList<>();
    }

    public void addFactura(Factura f) {
        facturas.add(f);
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public void execute(int sqlType) {
        Connection conn = Conexion.getInstance().getConnection();

        switch (sqlType) {
            case 1:
                facturas.forEach(f -> {
                    try {
                        PreparedStatement ps = conn.prepareStatement("INSERT INTO facturas VALUES(?, ?, ?, ?, ?, ?)");
                        ps.setInt(1, f.getId());
                        ps.setString(2, f.getCliente().getDni());
                        ps.setDouble(3, f.getSubtotal());
                        ps.setDouble(4, f.getDescuento());
                        ps.setDouble(5, f.getImpuesto());
                        ps.setDouble(6, f.getTotal());

                        ps.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    f.getDetalles().forEach(df -> df.save(f.getId()));
                });
                break;
            case 2:
                facturas.forEach(f -> {
                    try {
                        PreparedStatement ps = conn.prepareStatement("UPDATE facturas SET dni_cli_fac=?, subtotal_fac=?, descuento_fac=?, impuesto_fac=?, total_fac=? WHERE id_fac=?");
                        ps.setString(1, f.getCliente().getDni());
                        ps.setDouble(2, f.getSubtotal());
                        ps.setDouble(3, f.getDescuento());
                        ps.setDouble(4, f.getImpuesto());
                        ps.setDouble(5, f.getTotal());
                        ps.setInt(6, f.getId());

                        ps.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    f.getDetalles().forEach(df -> df.save(f.getId()));
                });
                break;
            case 3:
                facturas.forEach(f -> {
                    try {
                        PreparedStatement ps = conn.prepareStatement("DELETE FROM facturas WHERE id_fac=?");
                        ps.setInt(1, f.getId());

                        ps.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    f.getDetalles().forEach(df -> df.save(f.getId()));
                });
                break;
            default:
                break;
        }

    }

}
