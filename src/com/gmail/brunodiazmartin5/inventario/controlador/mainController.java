package com.gmail.brunodiazmartin5.inventario.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Bruno
 */
public class mainController implements Initializable {

    ////////////////////////Transiciones para mostrar los menus de opciones//////////////////////////////
    private TranslateTransition openNav, openNav2, openNav3, openNav4;
    private TranslateTransition closeNav, closeNav2, closeNav3, closeNav4;
    private TranslateTransition closeFastNav, closeFastNav2, closeFastNav3, closeFastNav4;
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////Transiciones para mostrar las scenes de los menus/////////////////////////////
    private TranslateTransition showSceneAniadirClient;
    private TranslateTransition showSceneAniadirBill;
    private TranslateTransition showSceneAniadirProduct;
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private ImageView imgAniadir;
    @FXML
    private ImageView imgAddClient;
    @FXML
    private ImageView imgAddBill;
    @FXML
    private ImageView imgAddProduct;
    @FXML
    private VBox vbAniadirSidebar;
    @FXML
    private ImageView imgEdit;
    @FXML
    private ImageView imgEditClient;
    @FXML
    private ImageView editBill;
    @FXML
    private ImageView imgEditProduct;
    @FXML
    private VBox vbEditSidebar;
    @FXML
    private ImageView imgDelete;
    @FXML
    private ImageView imgDeleteClient;
    @FXML
    private ImageView imgDeleteBill;
    @FXML
    private ImageView imgDeleteProduct;
    @FXML
    private VBox vbDeleteSidebar;
    @FXML
    private ImageView imgSearch;
    @FXML
    private ImageView imgSearchClient;
    @FXML
    private ImageView imgSearchBill;
    @FXML
    private ImageView imgSearchProduct;
    @FXML
    private VBox vbSearchSidebar;
    @FXML
    private ImageView imgSettings;
    @FXML
    private AnchorPane dynamicPane;

    private Parent rootAniadirClient;
    private Parent rooEditClient;
    
    @FXML
    private ImageView imgExit;
    @FXML
    private AnchorPane root;
    
    private double xOffset, yOffset;
    @FXML
    private ScrollPane scrollPane;

    private void createSidebarTransitions() {
        openNav = Transitions.createTransition(Duration.millis(100), vbAniadirSidebar);
        closeNav = Transitions.createTransition(Duration.millis(100), vbAniadirSidebar);
        closeFastNav = Transitions.createTransition(Duration.millis(100), vbAniadirSidebar);

        openNav2 = Transitions.createTransition(Duration.millis(100), vbEditSidebar);
        closeNav2 = Transitions.createTransition(Duration.millis(100), vbEditSidebar);
        closeFastNav2 = Transitions.createTransition(Duration.millis(100), vbEditSidebar);

        openNav3 = Transitions.createTransition(Duration.millis(100), vbDeleteSidebar);
        closeNav3 = Transitions.createTransition(Duration.millis(100), vbDeleteSidebar);
        closeFastNav3 = Transitions.createTransition(Duration.millis(100), vbDeleteSidebar);

        openNav4 = Transitions.createTransition(Duration.millis(100), vbSearchSidebar);
        closeNav4 = Transitions.createTransition(Duration.millis(100), vbSearchSidebar);
        closeFastNav4 = Transitions.createTransition(Duration.millis(100), vbSearchSidebar);
    }

    private void createMenuTransitions() {
        showSceneAniadirClient = new TranslateTransition(Duration.millis(300), dynamicPane);
        showSceneAniadirClient.setFromY(400);
        showSceneAniadirClient.setInterpolator(Interpolator.LINEAR);
        showSceneAniadirClient.setToY(0);
        
        showSceneAniadirBill = Transitions.createTransition(Duration.millis(300), dynamicPane);
        showSceneAniadirBill.setFromY(400);
        showSceneAniadirBill.setInterpolator(Interpolator.LINEAR);
        showSceneAniadirBill.setToY(0);
    }

    private void setEventTransitions() {
        imgAniadir.setOnMouseEntered(event -> {
            hideAllSliderMenus();
            Transitions.hover(openNav, vbAniadirSidebar);
        });
        vbAniadirSidebar.setOnMouseExited(event -> Transitions.hide(closeNav, vbAniadirSidebar));
        
        imgEdit.setOnMouseEntered(event -> {
            hideAllSliderMenus();
            Transitions.hover(openNav2, vbEditSidebar);
        });
        vbEditSidebar.setOnMouseExited(event -> Transitions.hide(closeNav2, vbEditSidebar));

        imgDelete.setOnMouseEntered(event -> {
            hideAllSliderMenus();
            Transitions.hover(openNav3, vbDeleteSidebar);
        });
        vbDeleteSidebar.setOnMouseExited(event -> Transitions.hide(closeNav3, vbDeleteSidebar));

        imgSearch.setOnMouseEntered(event -> {
            hideAllSliderMenus();
            Transitions.hover(openNav4, vbSearchSidebar);
        });
        vbSearchSidebar.setOnMouseExited(event -> Transitions.hide(closeNav4, vbSearchSidebar));

        
        imgAddClient.setOnMouseEntered(event -> {
            dynamicPane.getChildren().clear();
            Transitions.hoverScene(dynamicPane, rootAniadirClient, showSceneAniadirClient);
        });
        
        imgAddBill.setOnMouseEntered(event -> {
                dynamicPane.getChildren().clear();
                Transitions.hoverScene(dynamicPane, rooEditClient, showSceneAniadirBill);
        });
    }
    
    private void createToolTips(){
        Tooltip.install(imgAniadir, new Tooltip("Nuevo"));
        Tooltip.install(imgAddClient, new Tooltip("Nuevo cliente"));
        Tooltip.install(imgAddBill, new Tooltip("Nueva factura"));
        Tooltip.install(imgAddProduct, new Tooltip("Nuevo producto"));
        Tooltip.install(imgEdit, new Tooltip("Editar"));
        Tooltip.install(imgEditClient, new Tooltip("Editar cliente"));
        Tooltip.install(editBill, new Tooltip("Editar factura"));
        Tooltip.install(imgEditProduct, new Tooltip("Editar producto"));
        Tooltip.install(imgDelete, new Tooltip("Eliminar"));
        Tooltip.install(imgDeleteClient, new Tooltip("Eliminar clietne"));
        Tooltip.install(imgDeleteBill, new Tooltip("Eliminar factura"));
        Tooltip.install(imgDeleteProduct, new Tooltip("Eliminar producto"));
        Tooltip.install(imgSearch, new Tooltip("Buscar"));
        Tooltip.install(imgSettings, new Tooltip("Opciones"));
        Tooltip.install(imgExit, new Tooltip("Salir"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createSidebarTransitions(); //Crear las transiciones de los sidebar
        createMenuTransitions(); //Crear las transiciones de los menus
        setEventTransitions(); //Setear los eventos MouseEntered y MouseExited de los ImageView
        createToolTips(); //Crea los toolTips para cada menu
        
        imgExit.setOnMouseClicked(event -> Platform.exit());
        
        rootAniadirClient = Scenes.createScene(rootAniadirClient, "vista/addClientFXML.fxml");
        rooEditClient = Scenes.createScene(rooEditClient, "vista/editClientFXML.fxml");
        
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        
        Platform.runLater(() -> {
            closeFastNav.setToX(-(vbAniadirSidebar.getWidth()));
            closeFastNav.play();
            closeFastNav2.setToX(-(vbEditSidebar.getWidth()));
            closeFastNav2.play();
            closeFastNav3.setToX(-(vbDeleteSidebar.getWidth()));
            closeFastNav3.play();
            closeFastNav4.setToX(-(vbSearchSidebar.getWidth()));
            closeFastNav4.play();
        });
        
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        
        root.setOnMouseDragged(event ->{
            ((Stage)(root.getScene().getWindow())).setX(event.getScreenX() - xOffset);
            ((Stage)(root.getScene().getWindow())).setY(event.getScreenY() - yOffset);
        });
        
    }
    
    private void hideAllSliderMenus() {
        Transitions.hide(closeNav, vbAniadirSidebar);
        Transitions.hide(closeNav2, vbEditSidebar);
        Transitions.hide(closeNav3, vbDeleteSidebar);
        Transitions.hide(closeNav4, vbSearchSidebar);
    }

    @FXML
    private void settings(MouseEvent event) {
    }

}
