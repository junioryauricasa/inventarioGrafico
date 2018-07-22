package com.gmail.brunodiazmartin5.inventario.controlador;

import com.gmail.brunodiazmartin5.inventario.Inventario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Bruno
 */
public class Scenes {
    
    public static Parent createScene(final Parent root, final String fxml){
        Parent r=null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Inventario.class.getResource(fxml));
        
        try {
            r = fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }
    
}
