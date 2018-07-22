package com.gmail.brunodiazmartin5.inventario.controlador;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Bruno
 */
public class Transitions {
    public static TranslateTransition createTransition(Duration duration, Pane root){
        TranslateTransition t = new TranslateTransition(duration, root);
        t.setToX(root.getTranslateX()-root.getWidth());
        
        return t;
    }
    
    public static void hoverScene(AnchorPane pane, Parent prt, TranslateTransition trans){
        if (!pane.getChildren().contains(prt)) 
            pane.getChildren().add(prt);

        //trans.setFromY(-400);
        //trans.setToY(0);
        trans.setFromX(-400);
        trans.setToX(0);
        trans.play();
    }
    
    public static void hideScene(TranslateTransition trans){
        //trans.setFromY(0);
        //trans.setToY(-400);
        trans.setFromX(0);
        trans.setToX(-400);
        trans.play();
    }
    
    public static void hover(TranslateTransition trans, VBox prt){
        if ((prt.getTranslateX()) == -(prt.getWidth())) {
            trans.play();
        }
    }
    
    public static void hide(TranslateTransition trans, VBox prt){
        trans.setToX(-(prt.getWidth()));
        trans.play();
    }
}
