package com.gmail.brunodiazmartin5.inventario.controlador;

import com.gmail.brunodiazmartin5.inventario.modelo.Cliente;
import com.gmail.brunodiazmartin5.inventario.modelo.Conexion;
import com.gmail.brunodiazmartin5.io.AsyncDataWriter;
import com.gmail.brunodiazmartin5.mysql.MySQL;
import com.gmail.brunodiazmartin5.util.Query;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
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
public class addClientController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtMovil;
    @FXML
    private TextField txtDNI;
    @FXML
    private Label lblInfo;
    @FXML
    private ImageView imgGuardar;

    private AsyncDataWriter asyncWriter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        asyncWriter = new AsyncDataWriter();
        asyncWriter.addObserver(() -> System.out.println("[Cliente] END TASK"));
        
        Tooltip.install(imgGuardar, new Tooltip("Guardar"));
    }

    @FXML
    private void saveClient(MouseEvent event) {
        lblInfo.setText("");
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String movil = txtMovil.getText();
        String dni = txtDNI.getText();

        if (anyFieldEmpty(nombre, apellidos, direccion, telefono, movil, dni)) {
            lblInfo.setText("Rellene todos los datos");
        } else {
            if (!isDNIValid(dni)) {
                lblInfo.setText("DNI no valido");
            } else if (!isNumberValid(movil) || !isNumberValid(telefono)) {
                lblInfo.setText("Móvil o fijo incorrectos");
            } else {
                Cliente c = new Cliente(nombre, apellidos, dni, direccion, movil, telefono);
                asyncWriter.accept(c);
                asyncWriter.run();
                clearFields();
            }
        }
    }

    private boolean anyFieldEmpty(String... fields) {
        for (String s : fields) {
            if (s.equals("")) {
                return true;
            }
        }

        return false;
    }

    private boolean isDNIValid(String dni) {
        return dni.matches("[0-9]{1,8}[a-zA-Z]");
    }

    private boolean isNumberValid(String number) {
        return number.length() == 9;
    }
    
    private void clearFields(){
        txtNombre.clear();
        txtApellidos.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        txtMovil.clear();
        txtDNI.clear();
    }
}
