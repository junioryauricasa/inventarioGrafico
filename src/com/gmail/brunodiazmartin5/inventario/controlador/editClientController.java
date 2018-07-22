package com.gmail.brunodiazmartin5.inventario.controlador;

import java.net.URL;
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
public class editClientController implements Initializable{

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

    @FXML
    private void loadFieldsData(MouseEvent event) {
    }

    @FXML
    private void editClient(MouseEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tooltip.install(imgBuscar, new Tooltip("Buscar cliente"));
        Tooltip.install(imgEditar, new Tooltip("Guardar"));
    }
    
}
