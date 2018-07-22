package com.gmail.brunodiazmartin5.inventario.controlador;

import com.gmail.brunodiazmartin5.exceptions.ConnectioNotOpenedException;
import com.gmail.brunodiazmartin5.exceptions.NoPropertiesSettedException;
import com.gmail.brunodiazmartin5.io.AsyncDataReader;
import com.gmail.brunodiazmartin5.io.AsyncDataWriter;
import com.gmail.brunodiazmartin5.mysql.MySQL;
import com.gmail.brunodiazmartin5.util.Query;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Bruno
 */
public class editClientController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtDNI;
    @FXML
    private TextField txtMovil;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtDNIBuscar;
    @FXML
    private Label lblInfo;
    @FXML
    private ImageView imgBuscar;
    @FXML
    private ImageView imgEditar;

    private AsyncDataWriter asyncWriter;
    private MySQL sql;

    @FXML
    private void loadFieldsData(MouseEvent event) {
        String dniBuscar = txtDNIBuscar.getText();

        if (dniBuscar.equals("")) {
            lblInfo.setText("Introduzca el DNI del cliente a editar");
        } else {
            PreparedStatement ps=null;
            try {
                ResultSet rs = sql.query("SELECT * FROM clientes WHERE dni_cli=?", dniBuscar);
                
                if(rs.next()){
                    txtDNI.setText(rs.getString(1));
                    txtNombre.setText(rs.getString(2));
                    txtApellidos.setText(rs.getString(3));
                    txtMovil.setText(rs.getString(4));
                    txtTelefono.setText(rs.getString(5));
                    txtDireccion.setText(rs.getString(6));
                }
            } catch (SQLException ex) {
                Logger.getLogger(editClientController.class.getName()).log(Level.SEVERE, "Error SQL", ex);
            }catch (ConnectioNotOpenedException ex) {
                Logger.getLogger(editClientController.class.getName()).log(Level.SEVERE, "Conexión no abierta", ex);
            }
        }
    }

    @FXML
    private void editClient(MouseEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        asyncWriter = new AsyncDataWriter();
        try {
            sql = new MySQL("com.mysql.jdbc.Driver");
            sql.setProperty("bd.server", "jdbc:mysql://localhost:3306/inventario");
            sql.setProperty("bd.user", "root");
            sql.setProperty("bd.password", "dayamon15");
            sql.setProperty("bd.ssl", "false");

            sql.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(editClientController.class.getName()).log(Level.SEVERE, "Driver no encontrado", ex);
        } catch (SQLException ex) {
            Logger.getLogger(editClientController.class.getName()).log(Level.SEVERE, "Error SQL", ex);
        } catch (NoPropertiesSettedException ex) {
            Logger.getLogger(editClientController.class.getName()).log(Level.SEVERE, "Propiedades no seteadas", ex);
        }

        Tooltip.install(imgBuscar, new Tooltip("Buscar cliente"));
        Tooltip.install(imgEditar, new Tooltip("Guardar"));
    }

}
