package com.gmail.brunodiazmartin5.inventario.controlador;

import com.gmail.brunodiazmartin5.exceptions.ConnectioNotOpenedException;
import com.gmail.brunodiazmartin5.exceptions.NoPropertiesSettedException;
import com.gmail.brunodiazmartin5.inventario.Inventario;
import com.gmail.brunodiazmartin5.mysql.MySQL;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Bruno
 */
public class loginController implements Initializable {

    @FXML
    private Label lblInfo;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    
    private MySQL sql;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       txtUser.setOnKeyPressed(event ->{ if(event.getCode()==KeyCode.ENTER) txtPass.requestFocus();});
       txtPass.setOnKeyPressed(event ->{ if(event.getCode()==KeyCode.ENTER)login(null);});
        
        try {
            sql = new MySQL("com.mysql.jdbc.Driver");
            sql.setProperty("bd.server", "jdbc:mysql://localhost:3306/inventario");
            sql.setProperty("bd.user", "root");
            sql.setProperty("bd.password", "dayamon15");
            sql.setProperty("bd.ssl", "false");
            
            sql.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, "Driver no encontrado", ex);
        } catch (SQLException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, "Error SQL", ex);
        } catch (NoPropertiesSettedException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, "Propiedades no seteadas", ex);
        }finally{
           try {
               sql.close();
           } catch (SQLException ex) {
               Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }

    @FXML
    private void login(MouseEvent event) {
        lblInfo.setText("");
        String username = txtUser.getText();
        String password = txtPass.getText();

        if (username.equals("") || password.equals("")) {
            lblInfo.setText("Introduce tu usario y contraseña");
        } else {
            try {
                ResultSet rs = sql.query("SELECT pass FROM usuarios WHERE username=?", username);
                
                if(rs.next()){
                    if(password.equals(rs.getString(1))){
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(Inventario.class.getResource("vista/mainFXML.fxml"));
                        
                        Stage stage = new Stage();
                        Scene scene=null;
                        try {
                            scene = new Scene(fxmlLoader.load());
                        } catch (IOException ex) {
                            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        stage.setScene(scene);
                        stage.setTitle("Inventario Java - Bienvenido "+username);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setResizable(false);
                        stage.show();
                        
                        ((Stage)(txtUser.getScene().getWindow())).close();
                        
                    }
                    else lblInfo.setText("Usuario o contraseña incorrecto");
                } else lblInfo.setText("Usuario o contraseña incorrecto");
            } catch (SQLException ex) {
                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, "Error sql", ex);
            } catch (ConnectioNotOpenedException ex) {
                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, "Conexión MySQL no abierta", ex);
            }
        }
    }

    @FXML
    private void exit(MouseEvent event) {
        Platform.exit();
    }

}
